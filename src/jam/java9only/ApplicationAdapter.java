package jam.java9only;

import jam.framework.*;

import javax.swing.*;
import java.awt.*;
import java.awt.desktop.*;
import java.io.File;

public class ApplicationAdapter implements
        AboutHandler,
        PreferencesHandler,
        OpenFilesHandler,
        PrintFilesHandler,
        QuitHandler {

    private static ApplicationAdapter theAdapter;

    // reference to the app where the existing quit, about, prefs code is
    private jam.framework.Application application;

    private ApplicationAdapter(jam.framework.Application application) {
        this.application = application;
    }

    // The main entry-point for this functionality.  This is the only method
    // that needs to be called at runtime, and it can easily be done using
    // reflection.
    public static void registerApplication(jam.framework.Application application) {
//        if (theApplication == null) {
//            theApplication = Application.getApplication();
//        }

        if (theAdapter == null) {
            theAdapter = new ApplicationAdapter(application);
        }
        Desktop.getDesktop().setAboutHandler(theAdapter);
        Desktop.getDesktop().setOpenFileHandler(theAdapter);
        Desktop.getDesktop().setPreferencesHandler(theAdapter);
        Desktop.getDesktop().setPrintFileHandler(theAdapter);
        Desktop.getDesktop().setQuitHandler(theAdapter);

        // Create a default menu bar that is shown when all windows are closed
        JMenuBar defaultMenuBar = new JMenuBar();
        if(jam.framework.Application.getMenuBarFactory() != null) {
            jam.framework.Application.getMenuBarFactory().populateMenuBar(defaultMenuBar, null);
            Desktop.getDesktop().setDefaultMenuBar(defaultMenuBar);
        }

    }

    @Override
    public void handleAbout(AboutEvent e) {
        if (application != null) {
            application.doAbout();
        } else {
            throw new IllegalStateException("handleAbout: Application instance detached from listener");
        }
    }

    @Override
    public void openFiles(OpenFilesEvent openFilesEvent) {
        for (File file : openFilesEvent.getFiles()) {
            application.doOpenFile(file);
        }
    }

    @Override
    public void handlePreferences(PreferencesEvent e) {
        if (application != null) {
            application.doPreferences();
        } else {
            throw new IllegalStateException("handlePreferences: Application instance detached from listener");
        }
    }

    @Override
    public void printFiles(PrintFilesEvent printFilesEvent) {
        for (File file : printFilesEvent.getFiles()) {
            DocumentFrame frame = application.doOpenFile(file);
            if (frame != null) {
                frame.doPrint();
            }
        }
    }

    @Override
    public void handleQuitRequestWith(QuitEvent e, QuitResponse response) {
        if (application != null) {
            application.doQuit();
        } else {
            throw new IllegalStateException("handleQuit: Application instance detached from listener");
        }

    }
}