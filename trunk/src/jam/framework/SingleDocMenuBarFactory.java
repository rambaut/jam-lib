/*
 * SingleDocMenuBarFactory.java
 *
 * Copyright (c) 2009 JAM Development Team
 *
 * This package is distributed under the Lesser Gnu Public Licence (LGPL)
 *
 */

package jam.framework;

import jam.mac.MacFileMenuFactory;
import jam.mac.MacHelpMenuFactory;
import jam.mac.MacWindowMenuFactory;


public class SingleDocMenuBarFactory extends DefaultMenuBarFactory {

	public SingleDocMenuBarFactory() {
		if (jam.mac.Utils.isMacOSX()) {
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