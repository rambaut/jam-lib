/*
 * MultiDocMenuBarFactory.java
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


public class MultiDocMenuBarFactory extends DefaultMenuBarFactory {


	public MultiDocMenuBarFactory() {
		if (jam.mac.Utils.isMacOSX()) {
			registerMenuFactory(new MacFileMenuFactory(true));
			registerMenuFactory(new DefaultEditMenuFactory());
			registerMenuFactory(new MacHelpMenuFactory());
			registerMenuFactory(new MacWindowMenuFactory());
		} else {
			registerMenuFactory(new DefaultFileMenuFactory(true));
			registerMenuFactory(new DefaultEditMenuFactory());
			registerMenuFactory(new DefaultHelpMenuFactory());
		}
	}
}