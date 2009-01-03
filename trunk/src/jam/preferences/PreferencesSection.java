/*
 * PreferencesSection.java
 *
 * Copyright (c) 2009 JAM Development Team
 *
 * This package is distributed under the Lesser Gnu Public Licence (LGPL)
 *
 */

package jam.preferences;

import javax.swing.*;

/**
 * @author Andrew Rambaut
 * @author Alexei Drummond
 * @version $Id: PreferencesSection.java 275 2006-03-22 16:58:56Z rambaut $
 */
public interface PreferencesSection {
    String getTitle();

    Icon getIcon();

    JPanel getPanel();

    void retrievePreferences();

    void storePreferences();
}
