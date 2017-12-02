/*
 * ConsoleApplication.java
 *
 * Copyright (c) 2009 JAM Development Team
 *
 * This package is distributed under the Lesser Gnu Public Licence (LGPL)
 *
 */

package jam.console;

import jam.framework.Application;
import jam.framework.MenuBarFactory;
import jam.framework.DocumentFrame;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class ConsoleApplication extends Application {

	private ConsoleFrame consoleFrame = null;
	private boolean dontAskSave;

	public ConsoleApplication(String nameString, String aboutString, Icon icon, boolean dontAskSave) throws IOException {
		this(new ConsoleMenuBarFactory(), nameString, aboutString, icon, dontAskSave);
	}

	public ConsoleApplication(String nameString, String titleString, String aboutString, Icon icon, boolean dontAskSave) throws IOException {
		this(new ConsoleMenuBarFactory(), nameString, titleString, aboutString, icon, dontAskSave);
	}

	public ConsoleApplication(MenuBarFactory menuBarFactory, String nameString, String aboutString, Icon icon, boolean dontAskSave) throws IOException {
		this(menuBarFactory, nameString, nameString, aboutString, icon, dontAskSave);
	}

	public ConsoleApplication(MenuBarFactory menuBarFactory, String nameString, String titleString, String aboutString, Icon icon, boolean dontAskSave) throws IOException {

		super(menuBarFactory, nameString, titleString, aboutString, icon);

		this.dontAskSave = dontAskSave;

		consoleFrame = new ConsoleFrame();
		consoleFrame.initialize();
		consoleFrame.setVisible(true);

		// event handling
		consoleFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				thisWindowClosing(e);
			}
		});

		getOpenAction().setEnabled(false);
	}

	public void initialize() {
		// Register the application with the OK. Prior to Java 1.9 this was just
		// for Mac OS X. Now it uses java.desktop.Desktop to be cross platform
		// This will result in any events such as open file being executed
		// due to files being double-clicked or dragged on to the application.
		jam.mac.Utils.registerDesktopApplication(this);
	}

	protected JFrame getDefaultFrame() { return consoleFrame; }

	public DocumentFrame doNew() {
		throw new RuntimeException("A ConsoleApplication cannot do a New command");
	}

	public DocumentFrame doOpenFile(File file) {
		throw new RuntimeException("A ConsoleApplication cannot do an Open command");
	}

	public void doCloseWindow() {
		doQuit();
	}

	public void doQuit() {
		if (dontAskSave || consoleFrame.requestClose()) {

			consoleFrame.setVisible(false);
			consoleFrame.dispose();
			System.exit(0);
		}
	}

	public void doPreferences() {
	}

	public void doStop() {
		doQuit();
	}

	// Close the window when the close box is clicked
	private void thisWindowClosing(java.awt.event.WindowEvent e) {
		doQuit();
	}

}