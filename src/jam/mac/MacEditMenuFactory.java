/*
 * MacEditMenuFactory.java
 *
 * Copyright (c) 2009 JAM Development Team
 *
 * This package is distributed under the Lesser Gnu Public Licence (LGPL)
 *
 */

/*
 * MacEditMenuFactory.java.
 */
package jam.mac;

import jam.framework.*;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * @author rambaut
 *         Date: Dec 26, 2004
 *         Time: 11:01:40 AM
 *
 * @version $Id: MacEditMenuFactory.java 964 2009-01-02 21:15:28Z rambaut $
 */
public class MacEditMenuFactory implements MenuFactory {
	public String getMenuName() {
		return "Edit";
	}

	public void populateMenu(JMenu menu, AbstractFrame frame) {

		JMenuItem item;

        menu.setMnemonic('E');

		item = new JMenuItem(frame.getCutAction());
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, MenuBarFactory.MENU_MASK));
		menu.add(item);

		item = new JMenuItem(frame.getCopyAction());
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, MenuBarFactory.MENU_MASK));
		menu.add(item);

		item = new JMenuItem(frame.getPasteAction());
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, MenuBarFactory.MENU_MASK));
		menu.add(item);

		item = new JMenuItem(frame.getDeleteAction());
		menu.add(item);

		item = new JMenuItem(frame.getSelectAllAction());
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, MenuBarFactory.MENU_MASK));
		menu.add(item);

		menu.addSeparator();

		item = new JMenuItem(frame.getFindAction());
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, MenuBarFactory.MENU_MASK));
		menu.add(item);

	}

	public int getPreferredAlignment() {
		return LEFT;
	}
}