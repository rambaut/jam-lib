/*
 * ControlPalette.java
 *
 * Copyright (c) 2009 JAM Development Team
 *
 * This package is distributed under the Lesser Gnu Public Licence (LGPL)
 *
 */

package jam.controlpanels;

import javax.swing.*;

/**
 * Date: 20/03/2006
 * Time: 10:23:21
 *
 * @author Joseph Heled
 * @version $Id: ControlPalette.java 482 2006-10-25 06:30:57Z twobeers $
 */
public interface ControlPalette {

    JPanel getPanel();

    void addControlsProvider(ControlsProvider provider, boolean addAtStart);

    void fireControlsChanged();

    void addControlPanelListener(ControlPaletteListener listener);

    void removeControlPanelListener(ControlPaletteListener listener);

    void setupControls();
}
