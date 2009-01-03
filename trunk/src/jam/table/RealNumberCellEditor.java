/*
 * RealNumberCellEditor.java
 *
 * Copyright (c) 2009 JAM Development Team
 *
 * This package is distributed under the Lesser Gnu Public Licence (LGPL)
 *
 */

package jam.table;

import jam.components.RealNumberField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RealNumberCellEditor extends DefaultCellEditor {

    private RealNumberField editor;

    public RealNumberCellEditor(double minValue, double maxValue) {
        super(new RealNumberField(minValue, maxValue));

        editor = (RealNumberField) getComponent();

        setClickCountToStart(2); //This is usually 1 or 2.

        // Must do this so that editing stops when appropriate.
        editor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }

    public Object getCellEditorValue() {
        return editor.getValue();
    }

    public Component getTableCellEditorComponent(JTable table,
                                                 Object value,
                                                 boolean isSelected,
                                                 int row,
                                                 int column) {
        editor.setValue(((Double) value).doubleValue());
        return editor;
    }
}