/*
 * DisclosureListener.java
 *
 * Copyright (c) 2009 JAM Development Team
 *
 * This package is distributed under the Lesser Gnu Public Licence (LGPL)
 *
 */

package jam.disclosure;

import java.awt.*;

/**
 * @author rambaut
 *         Date: May 25, 2005
 *         Time: 11:17:04 PM
 */
public interface DisclosureListener {
	void opening(Component component);
	void opened(Component component);

	void closing(Component component);
	void closed(Component component);
}
