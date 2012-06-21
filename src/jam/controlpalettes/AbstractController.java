/*
 * AbstractController.java
 *
 * Copyright (c) 2009 JAM Development Team
 *
 * This package is distributed under the Lesser Gnu Public Licence (LGPL)
 *
 */

package jam.controlpalettes;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrew Rambaut
 * @author Alexei Drummond
 * @version $Id: AbstractController.java 919 2008-06-09 23:54:44Z rambaut $
 */
public abstract class AbstractController implements Controller {
	protected static final String IS_SHOWN = "isShown";

    protected void addComponent(JComponent component) {
        componentList.add(component);
    }

    protected void enableComponents(boolean isSelected) {
        for (JComponent component : componentList) {
            component.setEnabled(isSelected);
        }
    }

    /**
     * Add a ControllerListener to this controllers list of listeners
     * The main listener will be the ControlPalette itself which will use
     * this to resize the panels if the components changed
     *
     * @param listener
     */
    public void addControllerListener(ControllerListener listener) {
        listeners.add(listener);
    }

    /**
     * Remove a listener
     *
     * @param listener
     */
    public void removeControllerListener(ControllerListener listener) {
        listeners.remove(listener);
    }

    public void fireControllerChanged() {
        for (ControllerListener listener : listeners) {
            listener.controlsChanged();
        }
    }

    List<JComponent> componentList = new ArrayList<JComponent>();
    private final List<ControllerListener> listeners = new ArrayList<ControllerListener>();
}
