/*
 * StatusBar.java
 *
 * Copyright (c) 2009 JAM Development Team
 *
 * This package is distributed under the Lesser Gnu Public Licence (LGPL)
 *
 */

package jam.panels;

import javax.swing.*;
import java.awt.*;

/**
 * @author rambaut
 *         Date: Oct 12, 2004
 *         Time: 12:18:09 AM
 */
public class StatusBar extends StatusPanel {
	public StatusBar(String initialText) {
		super(initialText);

		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(1, 0, 1, 0, Color.gray),
				BorderFactory.createEmptyBorder(2, 12, 2, 12)));

	}

	public void paintComponent(Graphics g) {
		if (isOpaque()) {
			super.paintComponent(g);
			g.setColor(new Color(0.0F, 0.0F, 0.0F, 0.05F));
			g.fillRect(0, 0, getWidth(), getHeight());
		}
	}

}
