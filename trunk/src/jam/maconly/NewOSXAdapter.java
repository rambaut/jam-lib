/*
 * OSXAdapter.java
 *
 * Copyright (c) 2009 JAM Development Team
 *
 * This package is distributed under the Lesser Gnu Public Licence (LGPL)
 *
 */

/*	OSXAdapter.java */

package jam.maconly;

import com.apple.eawt.*;
import com.apple.eawt.Application;
import jam.framework.*;

import javax.swing.*;
import java.io.File;

public class NewOSXAdapter implements
        AboutHandler,
        PreferencesHandler,
        AppReOpenedListener,
        OpenFilesHandler,
        PrintFilesHandler,
        QuitHandler  {

    // pseudo-singleton model; no point in making multiple instances
    // of the EAWT application or our adapter
    private static NewOSXAdapter theAdapter;
    private static com.apple.eawt.Application theApplication;

    // reference to the app where the existing quit, about, prefs code is
    private jam.framework.Application application;

    private NewOSXAdapter(jam.framework.Application application) {
        this.application = application;
    }

    // The main entry-point for this functionality.  This is the only method
    // that needs to be called at runtime, and it can easily be done using
    // reflection.
    public static void registerMacOSXApplication(jam.framework.Application application) {
        if (theApplication == null) {
            theApplication = Application.getApplication();
        }

        if (theAdapter == null) {
            theAdapter = new NewOSXAdapter(application);
        }
        theApplication.setAboutHandler(theAdapter);
        theApplication.setOpenFileHandler(theAdapter);
        theApplication.setPreferencesHandler(theAdapter);
        theApplication.setPrintFileHandler(theAdapter);
        theApplication.setQuitHandler(theAdapter);

        // Create a default menu bar that is shown when all windows are closed
        JMenuBar defaultMenuBar = new JMenuBar();
        if(jam.framework.Application.getMenuBarFactory() != null) {
            jam.framework.Application.getMenuBarFactory().populateMenuBar(defaultMenuBar, null);
            theApplication.setDefaultMenuBar(defaultMenuBar);
        }

    }

    public void handleAbout(AppEvent.AboutEvent aboutEvent) {
        if (application != null) {
            application.doAbout();
        } else {
            throw new IllegalStateException("handleAbout: Application instance detached from listener");
        }
    }

    public void appReOpened(AppEvent.AppReOpenedEvent appReOpenedEvent) {

    }

    public void openFiles(AppEvent.OpenFilesEvent openFilesEvent) {
        for (File file : openFilesEvent.getFiles()) {
            application.doOpenFile(file);
        }
    }

    public void handlePreferences(AppEvent.PreferencesEvent preferencesEvent) {
        if (application != null) {
            application.doPreferences();
        } else {
            throw new IllegalStateException("handlePreferences: Application instance detached from listener");
        }
    }

    public void printFiles(AppEvent.PrintFilesEvent printFilesEvent) {
        for (File file : printFilesEvent.getFiles()) {
            DocumentFrame frame = application.doOpenFile(file);
            if (frame != null) {
                frame.doPrint();
            }
        }
    }

    public void handleQuitRequestWith(AppEvent.QuitEvent quitEvent, QuitResponse quitResponse) {
        if (application != null) {
            application.doQuit();
        } else {
            throw new IllegalStateException("handleQuit: Application instance detached from listener");
        }

    }
}