/*
 * MacWindowMenuFactory.java
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
 *         Time: 11:03:39 AM
 */
public class MacWindowMenuFactory implements MenuFactory {
	public String getMenuName() {
		return "Window";
	}

	public void populateMenu(JMenu menu, AbstractFrame frame) {

		Application application = Application.getApplication();

		JMenuItem item;

		item = new JMenuItem(frame.getZoomWindowAction());
		menu.add(item);

		item = new JMenuItem(frame.getMinimizeWindowAction());
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, MenuBarFactory.MENU_MASK));
		menu.add(item);

	}

	public int getPreferredAlignment() {
		return RIGHT;
	}
}
