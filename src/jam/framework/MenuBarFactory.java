/*
 * MenuBarFactory.java
 *
 * Copyright (c) 2009 JAM Development Team
 *
 * This package is distributed under the Lesser Gnu Public Licence (LGPL)
 *
 */

/**
 * MenuBarFactory.java
 */

package jam.framework;

import javax.swing.*;
import java.awt.*;

public interface MenuBarFactory {

    public final static int MENU_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

    void populateMenuBar(JMenuBar menuBar, AbstractFrame frame);
    void deregisterMenuFactories();
    void registerPermanentMenuFactory(MenuFactory menuFactory);
    void registerMenuFactory(MenuFactory menuFactory);
}