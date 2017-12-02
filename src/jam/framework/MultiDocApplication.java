/*
 * MultiDocApplication.java
 *
 * Copyright (c) 2009 JAM Development Team
 *
 * This package is distributed under the Lesser Gnu Public Licence (LGPL)
 *
 */

/**
 * MultiDocApplication.java
 */

package jam.framework;

import jam.mac.Utils;

import javax.swing.*;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MultiDocApplication extends Application {
	private DocumentFrameFactory documentFrameFactory = null;

	private AbstractFrame invisibleFrame = null;
	private DocumentFrame upperDocumentFrame = null;

	private ArrayList<DocumentFrame> documents = new ArrayList<DocumentFrame>();

	public MultiDocApplication(String nameString, String aboutString, Icon icon) {

		super(new MultiDocMenuBarFactory(), nameString, aboutString, icon);
	}

	public MultiDocApplication(String nameString, String aboutString, Icon icon,
							   String websiteURLString, String helpURLString) {

		super(new MultiDocMenuBarFactory(), nameString, aboutString, icon, websiteURLString, helpURLString);
	}

	public MultiDocApplication(MenuBarFactory menuBarFactory, String nameString, String aboutString, Icon icon) {

		super(menuBarFactory, nameString, aboutString, icon);
	}

	public MultiDocApplication(MenuBarFactory menuBarFactory, String nameString, String aboutString, Icon icon,
							   String websiteURLString, String helpURLString) {

		super(menuBarFactory, nameString, aboutString, icon, websiteURLString, helpURLString);
	}

	public MultiDocApplication(MenuBarFactory menuBarFactory, String nameString, String titleString, String aboutString, Icon icon,
							   String websiteURLString, String helpURLString) {

		super(menuBarFactory, nameString, titleString, aboutString, icon, websiteURLString, helpURLString);
	}

	public final void initialize() {
		// Register the application with the OK. Prior to Java 1.9 this was just
		// for Mac OS X. Now it uses java.desktop.Desktop to be cross platform
		// This will result in any events such as open file being executed
		// due to files being double-clicked or dragged on to the application.
		jam.mac.Utils.registerDesktopApplication(this);
	}

	public void setDocumentFrameFactory(DocumentFrameFactory documentFrameFactory) {
		this.documentFrameFactory = documentFrameFactory;
	}

	protected JFrame getDefaultFrame() {
		JFrame frame = getUpperDocumentFrame();
		if (frame == null) return invisibleFrame;
		return frame;
	}

	protected DocumentFrame createDocumentFrame() {
		return documentFrameFactory.createDocumentFrame(this, getMenuBarFactory());
	}

	public void destroyDocumentFrame(DocumentFrame documentFrame) {
		closeDocumentFrame(documentFrame);
	}

	public DocumentFrame doNew() {
		DocumentFrame doc = createDocumentFrame();
		addDocumentFrame(doc);
		return doc;
	}

	public DocumentFrame doOpenFile(File file) {

		DocumentFrame documentFrame = getDocumentFrame(file);

		if (documentFrame == null) {
			documentFrame = createDocumentFrame();
			if (documentFrame.openFile(file)) {
				addDocumentFrame(documentFrame);
			}
		}

		documentFrame.toFront();

		return documentFrame;
	}

	public void doQuit() {

		boolean ok = true;

		while (documents.size() > 0) {
			DocumentFrame documentFrame = documents.get(0);
			if (!documentFrame.requestClose()) {
				return;
			} else {
				documents.remove(documentFrame);
				if (documentFrame.getWindowListeners().length > 0) {
					documentFrame.removeWindowListener(documentFrame.getWindowListeners()[0]);
				}
				documentFrame.setVisible(false);
				documentFrame.dispose();
			}
		}

		if (ok) {
			System.exit(0);
		}
	}

	public DocumentFrame getUpperDocumentFrame() {
		return upperDocumentFrame;
	}

	public DocumentFrame getDocumentFrame(File file) {
		if (documents != null && documents.size() > 0) {
			for (DocumentFrame doc : documents) {
				if (doc != null && doc.getFile() == file) {
					return doc;
				}
			}
		}

		return null;
	}

	private void documentFrameActivated(DocumentFrame documentFrame) {
		upperDocumentFrame = documentFrame;
	}

	// Close the window when the close box is clicked
	private void documentFrameClosing(DocumentFrame documentFrame) {
		if (documentFrame.requestClose()) {
			closeDocumentFrame(documentFrame);
		}
	}

	// Close the documentFrame without further discussion
	private void closeDocumentFrame(DocumentFrame documentFrame) {
		documents.remove(documentFrame);
		if (documentFrame.getWindowListeners().length > 0) {
			documentFrame.removeWindowListener(documentFrame.getWindowListeners()[0]);
		}
		documentFrame.setVisible(false);
		documentFrame.dispose();
		if (documents.size() == 0) {
			doQuit();
		}
	}

	private void addDocumentFrame(DocumentFrame documentFrame) {
		documentFrame.initialize();
		documentFrame.setVisible(true);

		// event handling
		documentFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowActivated(java.awt.event.WindowEvent e) {
				documentFrameActivated((DocumentFrame) e.getWindow());
			}

			public void windowClosing(java.awt.event.WindowEvent e) {
				documentFrameClosing((DocumentFrame) e.getWindow());
			}
		});

		documents.add(documentFrame);
		upperDocumentFrame = documentFrame;
	}

	/**
	 * Create an invisible dummy frame to host the default menu bar expected by the Mac OS X application
	 * This is now done in the OSXAdapter so this method is deprecated.
	 */
	@Deprecated
	private void setupFramelessMenuBar() {
		if (Utils.isMacOSX() &&
				System.getProperty("apple.laf.useScreenMenuBar") != null &&
				System.getProperty("apple.laf.useScreenMenuBar").equalsIgnoreCase("true")) {
			if (invisibleFrame == null) {
				// We use reflection here because the setUndecorated() method
				// only exists in Java 1.4 and up
				invisibleFrame = new AbstractFrame() {

					protected void initializeComponents() {
						getSaveAction().setEnabled(false);
						getSaveAsAction().setEnabled(false);
						if (getImportAction() != null) getImportAction().setEnabled(false);
						if (getExportAction() != null) getExportAction().setEnabled(false);
						getPrintAction().setEnabled(false);

						getCutAction().setEnabled(false);
						getCopyAction().setEnabled(false);
						getPasteAction().setEnabled(false);
						getDeleteAction().setEnabled(false);
						getSelectAllAction().setEnabled(false);
						getFindAction().setEnabled(false);

						getZoomWindowAction().setEnabled(false);
						getMinimizeWindowAction().setEnabled(false);
						getCloseWindowAction().setEnabled(false);

					}

					public boolean requestClose() {
						return false;
					}

					public JComponent getExportableComponent() {
						return null;
					}
				};
				invisibleFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				try {
					Method mthd = invisibleFrame.getClass().getMethod("setUndecorated",
							new Class[]{Boolean.TYPE});
					mthd.invoke(invisibleFrame, new Object[]{Boolean.TRUE});
				} catch (Exception ex) {
					// Shouldn't happen
				}
				invisibleFrame.setSize(0, 0);
				invisibleFrame.pack();
			}
			invisibleFrame.initialize();

			if (!invisibleFrame.isVisible())
				invisibleFrame.setVisible(true);

			invisibleFrame.pack();
		}

	}

}