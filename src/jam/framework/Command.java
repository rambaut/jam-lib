/*
 * Command.java
 *
 * Copyright (c) 2009 JAM Development Team
 *
 * This package is distributed under the Lesser Gnu Public Licence (LGPL)
 *
 */

package jam.framework;

import javax.swing.*;

/**
 * @author rambaut
 *         Date: Dec 26, 2004
 *         Time: 10:29:38 AM
 */
public interface Command {

	/**
	 * Returns the swing Action for this command
	 * @return the action object
	 */
	Action getAction();

	String getPreferredMenu();

	String getPreferredPosition();
}
