/*
 * DemoMenuFactory.java
 *
 * Copyright (c) 2009 JAM Development Team
 *
 * This package is distributed under the Lesser Gnu Public Licence (LGPL)
 *
 */

package jam.demo.menus;

import jam.framework.AbstractFrame;
import jam.framework.MenuFactory;

import javax.swing.*;

/**
 * @author Andrew Rambaut
 * @version $Id: DemoMenuFactory.java 840 2007-11-09 04:52:39Z twobeers $
 *
 * Created on 24 February 2005, 17:12:11
 */
public class DemoMenuFactory implements MenuFactory {

    public static final String FIRST = "First";
    public static final String SECOND = "Second";

    public String getMenuName() {
        return "Demo";
    }

    public void populateMenu(JMenu menu, AbstractFrame frame) {
        JMenuItem item;

        if (frame instanceof DemoMenuHandler) {
            item = new JMenuItem(((DemoMenuHandler)frame).getFirstAction());
            menu.add(item);

            item = new JMenuItem(((DemoMenuHandler)frame).getSecondAction());
            menu.add(item);
        } else {
            item = new JMenuItem(FIRST);
            item.setEnabled(false);
            menu.add(item);

            item = new JMenuItem(SECOND);
            item.setEnabled(false);
            menu.add(item);
        }

    }

    public int getPreferredAlignment() {
        return LEFT;
    }
}
