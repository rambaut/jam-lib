/*
 * SingleDocApplication.java
 *
 * Copyright (c) 2009 JAM Development Team
 *
 * This package is distributed under the Lesser Gnu Public Licence (LGPL)
 *
 */

/**
 * SingleDocApplication.java
 */

package jam.framework;

import javax.swing.*;
import java.io.File;

public class SingleDocApplication extends Application {

    private DocumentFrame documentFrame = null;

    public SingleDocApplication(String nameString, String aboutString, Icon icon) {

        super(new SingleDocMenuBarFactory(), nameString, aboutString, icon);
    }

    public SingleDocApplication(String nameString, String aboutString, Icon icon,
                                String websiteURLString, String helpURLString) {

        super(new SingleDocMenuBarFactory(), nameString, aboutString, icon, websiteURLString, helpURLString);
    }

    public SingleDocApplication(MenuBarFactory menuBarFactory, String nameString, String aboutString, Icon icon) {

        super(menuBarFactory, nameString, aboutString, icon);
    }

    public SingleDocApplication(MenuBarFactory menuBarFactory, String nameString, String aboutString, Icon icon,
                                String websiteURLString, String helpURLString) {

        super(menuBarFactory, nameString, aboutString, icon, websiteURLString, helpURLString);
    }

    public final void initialize() {
        // Register the application with the OK. Prior to Java 1.9 this was just
        // for Mac OS X. Now it uses java.desktop.Desktop to be cross platform
        // This will result in any events such as open file being executed
        // due to files being double-clicked or dragged on to the application.
        jam.mac.Utils.registerDesktopApplication(this);
    }

    public void setDocumentFrame(DocumentFrame documentFrame) {

        this.documentFrame = documentFrame;

        documentFrame.initialize();
        documentFrame.setVisible(true);

        // event handling
        documentFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                thisWindowClosing(e);
            }
        });
    }

    protected JFrame getDefaultFrame() { return documentFrame; }

    protected String getDocumentExtension() { return ""; }

    public DocumentFrame doNew() {
        throw new RuntimeException("A SingleDocApplication cannot do a New command");
    }

    public DocumentFrame doOpenFile(File file) {
        documentFrame.openFile(file);
        return documentFrame;
    }

    public void doCloseWindow() {
        doQuit();
    }

    public void doQuit() {
        if (documentFrame == null) {
            return;
        }
        if (documentFrame.requestClose()) {

            documentFrame.setVisible(false);
            documentFrame.dispose();
            System.exit(0);
        }
    }

    // Close the window when the close box is clicked
    private void thisWindowClosing(java.awt.event.WindowEvent e) {
        doQuit();
    }
}