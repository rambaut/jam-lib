/*
 * DemoMenuBarFactory.java
 *
 * Copyright (c) 2009 JAM Development Team
 *
 * This package is distributed under the Lesser Gnu Public Licence (LGPL)
 *
 */

package jam.demo;

import jam.framework.*;
import jam.mac.*;
import jam.demo.menus.DemoMenuFactory;


public class DemoMenuBarFactory extends DefaultMenuBarFactory {

	public DemoMenuBarFactory() {
		if (jam.mac.Utils.isMacOSX()) {
			registerMenuFactory(new MacFileMenuFactory(true));
			registerMenuFactory(new DefaultEditMenuFactory());
			registerMenuFactory(new DemoMenuFactory());
			registerMenuFactory(new MacWindowMenuFactory());
			registerMenuFactory(new MacHelpMenuFactory());
		} else {
			registerMenuFactory(new DefaultFileMenuFactory(true));
			registerMenuFactory(new DefaultEditMenuFactory());
			registerMenuFactory(new DemoMenuFactory());
			registerMenuFactory(new DefaultHelpMenuFactory());
		}
	}

}