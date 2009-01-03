/*
 * DocumentFrameFactory.java
 *
 * Copyright (c) 2009 JAM Development Team
 *
 * This package is distributed under the Lesser Gnu Public Licence (LGPL)
 *
 */

/**
 * DocumentFrameFactory.java
 */

package jam.framework;



public interface DocumentFrameFactory {

    DocumentFrame createDocumentFrame(Application app, MenuBarFactory menuBarFactory);
}