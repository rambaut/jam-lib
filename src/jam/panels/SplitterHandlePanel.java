/*
 * ActionPanel.java
 *
 * Copyright (c) 2009 JAM Development Team
 *
 * This package is distributed under the Lesser Gnu Public Licence (LGPL)
 *
 */

package jam.panels;

import jam.util.IconUtils;

import javax.swing.*;
import javax.swing.plaf.SplitPaneUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * Creates a panel with a drag button on the right that will move the provided SplitPane
 * @author rambaut
 */
public class SplitterHandlePanel extends JPanel {

    private final JButton handleButton;
    private final JSplitPane splitPane;

    public SplitterHandlePanel(final JSplitPane splitPane) {
        this(splitPane, true);
    }

    public SplitterHandlePanel(final JSplitPane splitPane, boolean useActionButton) {
        this.splitPane = splitPane;

        setLayout(new BorderLayout());
        setOpaque(false);

        JButton addButton = new JButton("+");
        addButton.putClientProperty("JButton.buttonType", "toolbar");

        if (addIcon != null) {
            addButton.setIcon(addIcon);
            addButton.setText(null);
            addButton.setPreferredSize(new Dimension(addIcon.getIconWidth(), addIcon.getIconHeight()));
        }
        addButton.setBorderPainted(false);
        addButton.setOpaque(false);
        // this is required on Windows XP platform -- untested on Macintosh
        addButton.setContentAreaFilled(false);

        JPanel blankPanel = new JPanel(new BorderLayout()) {
            public void paint(Graphics graphics) {
                graphics.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
                super.paint(graphics);
            }
        };
        blankPanel.setOpaque(false);

        handleButton = new JButton("|||");
        handleButton.putClientProperty("JButton.buttonType", "toolbar");

        if (handleIcon != null) {
            handleButton.setIcon(handleIcon);
            handleButton.setText(null);
            handleButton.setPreferredSize(new Dimension(handleIcon.getIconWidth(), handleIcon.getIconHeight()));
        }
        handleButton.setBorderPainted(false);
        handleButton.setOpaque(false);
        // this is required on Windows XP platform -- untested on Macintosh
        handleButton.setContentAreaFilled(false);

        new DragHandler(handleButton);

        add(addButton, BorderLayout.WEST);
        add(blankPanel, BorderLayout.CENTER);
        add(handleButton, BorderLayout.EAST);
    }


    public class DragHandler extends MouseAdapter {

        private JComponent component;
        private int dividerLocation, pressedLocation;

        public DragHandler(JComponent component) {
            this.component = component;
            component.addMouseListener(this);
            component.addMouseMotionListener(this);
        }

        public void dispose() {
            component.removeMouseListener(this);
            component.removeMouseMotionListener(this);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            dividerLocation = splitPane.getDividerLocation();
            pressedLocation = e.getX();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            int x = dividerLocation + (e.getX() - pressedLocation);
            int min = splitPane.getMinimumDividerLocation();
            int max = splitPane.getMaximumDividerLocation();
            final int newLocation =  (x <  min ? min : (x > max ? max : x));

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    splitPane.setDividerLocation(newLocation);
                }
            });
        }
    }

    private static BufferedImage backgroundImage = null;
    private static Icon addIcon;
    private static Icon handleIcon;

    static {
        addIcon = IconUtils.getIcon(SplitterHandlePanel.class, "images/splitter/add.png");
        handleIcon = IconUtils.getIcon(SplitterHandlePanel.class, "images/splitter/handle.png");
        try {
            backgroundImage = IconUtils.getBufferedImage(SplitterHandlePanel.class, "images/splitter/background.png");

        } catch (Exception e) {
            // no icons...
        }
    }

}
