/*
 * MacHelpMenuFactory.java
 *
 * Copyright (c) 2009 JAM Development Team
 *
 * This package is distributed under the Lesser Gnu Public Licence (LGPL)
 *
 */

package jam.mac;

import jam.framework.MenuFactory;
import jam.framework.AbstractFrame;
import jam.framework.Application;
import jam.framework.MenuBarFactory;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * @author rambaut
 *         Date: Dec 26, 2004
 *         Time: 11:04:02 AM
 */
public class MacHelpMenuFactory implements MenuFactory {
	public String getMenuName() {
		return "Help";
	}

	public void populateMenu(JMenu menu, AbstractFrame frame) {

		JMenuItem item;

		Application application = Application.getApplication();

        if (frame != null && frame.getHelpAction() != null) {
			item = new JMenuItem(frame.getHelpAction());
			item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_SLASH, MenuBarFactory.MENU_MASK));
			menu.add(item);

			menu.addSeparator();
        }

		if (application.getHelpAction() != null) {
			item = new JMenuItem(application.getHelpAction());
			menu.add(item);

			menu.addSeparator();
		}

		if (application.getWebsiteAction() != null) {
            item = new JMenuItem(application.getWebsiteAction());
            menu.add(item);
		}
	}

	public int getPreferredAlignment() {
		return RIGHT;
	}

}
