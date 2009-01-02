/*
 * ControlsProvider.java
 *
 * Copyright (c) 2009 JAM Development Team
 *
 * This package is distributed under the Lesser Gnu Public Licence (LGPL)
 *
 */

package jam.controlpanels;

import java.util.List;

/**
 * @author Andrew Rambaut
 * @version $Id: ControlsProvider.java 548 2006-11-30 01:43:31Z pepster $
 */
public interface ControlsProvider {
    /**
     * Give the controls provider with a handle for the controlPalette object.
     *
     * @param controlPalette
     */
    void setControlPalette(ControlPalette controlPalette);

    /**
     * Get a list of Controls handled by this provider.
     *
     * @param detachPrimaryCheckbox When false, do nothing. When true, if controls have a "main" on/off switch
     *                              (implemented as a JCheckBox), that checkbox should not be included in the controls
     *                               panel but returned in {@link Controls#getPrimaryCheckbox}
     * @return A list of Controls
     */
    List<Controls> getControls(boolean detachPrimaryCheckbox);

    /**
     * Give the provider some settings.
     *
     * @param settings
     */
    void setSettings(ControlsSettings settings);

    /**
     * Get the settings for a given Controls object.
     *
     * @param settings
     */
    void getSettings(ControlsSettings settings);
}
