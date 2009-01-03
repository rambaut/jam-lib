/*
 * StatusListener.java
 *
 * Copyright (c) 2009 JAM Development Team
 *
 * This package is distributed under the Lesser Gnu Public Licence (LGPL)
 *
 */

package jam.panels;

/**
 * @author rambaut
 *         Date: Jul 27, 2004
 *         Time: 10:04:04 AM
 */
public interface StatusListener {

    /**
     * Called when the status is to be changed.
     * @param status the status constant
     * @param statusText the status text
     */
    void statusChanged(int status, String statusText);

}
