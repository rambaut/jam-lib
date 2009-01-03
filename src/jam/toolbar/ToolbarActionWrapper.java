/*
 * ToolbarActionWrapper.java
 *
 * Copyright (c) 2009 JAM Development Team
 *
 * This package is distributed under the Lesser Gnu Public Licence (LGPL)
 *
 */

package jam.toolbar;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionEvent;

/**
 * @author Andrew Rambaut
 * @version $Id$
 */
public class ToolbarActionWrapper extends ToolbarAction {
    public ToolbarActionWrapper(AbstractAction action, String toolTipText, Icon icon) {
        super((String)action.getValue(Action.NAME), toolTipText, icon);
        this.action = action;

        action.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent event) {
                if (event.getPropertyName().equals("enabled")) {
                    setEnabled((Boolean)event.getNewValue());
                }
            }
        });
    }

    public void actionPerformed(ActionEvent ae) {
        action.actionPerformed(ae);
    }


    private final AbstractAction action;
}
