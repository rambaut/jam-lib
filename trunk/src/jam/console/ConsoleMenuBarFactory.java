/*
 * ConsoleMenuBarFactory.java
 *
 * Copyright (c) 2009 JAM Development Team
 *
 * This package is distributed under the Lesser Gnu Public Licence (LGPL)
 *
 */

/**
* ConsoleMenuBarFactory.java
*/

package jam.console;

import jam.framework.*;
import jam.mac.MacFileMenuFactory;
import jam.mac.MacHelpMenuFactory;
import jam.mac.MacWindowMenuFactory;

public class ConsoleMenuBarFactory extends DefaultMenuBarFactory {

	public ConsoleMenuBarFactory() {
        // org.virion stuff shouldn't be called from here - it's a separate project!

		// no its not. This class is part of JAM.
        if (jam.mac.Utils.isMacOSX()) {
        //if (System.getProperty("mrj.version") != null) {
            registerMenuFactory(new MacFileMenuFactory(false));
			registerMenuFactory(new DefaultEditMenuFactory());
			registerMenuFactory(new MacWindowMenuFactory());
			registerMenuFactory(new MacHelpMenuFactory());
		} else {
			registerMenuFactory(new DefaultFileMenuFactory(false));
			registerMenuFactory(new DefaultEditMenuFactory());
			registerMenuFactory(new DefaultHelpMenuFactory());
		}
	}
}