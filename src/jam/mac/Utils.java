/*
 * Utils.java
 *
 * Copyright (c) 2009 JAM Development Team
 *
 * This package is distributed under the Lesser Gnu Public Licence (LGPL)
 *
 */

/*	Utils.java */

package jam.mac;

import java.lang.reflect.Method;

public class Utils {

    private static boolean MAC_OS_X;
    private static String MAC_OS_X_VERSION;

    public static boolean isMacOSX() {
        return MAC_OS_X;
    }

    public static String getMacOSXVersion() {
        return MAC_OS_X_VERSION;
    }

    public static int getMacOSXMajorVersionNumber() {
        String[] bits = Utils.getMacOSXVersion().split("\\.");
        if (bits.length >= 2 && bits[0].equals("10")) {
            return Integer.parseInt(bits[1]);
        }
        return -1;
    }


    public static void registerDesktopApplication(jam.framework.Application application) {
        Class adapter = null;

        try {
            adapter = Class.forName("jam.java9only.ApplicationAdapter");
        } catch (Exception e) {
            // do nothing...
        }

        if (adapter == null && MAC_OS_X) {
            try {
                // test if com.apple.eawt is present...
                Class.forName("com.apple.eawt.AboutHandler");
                adapter = Class.forName("jam.maconly.NewOSXAdapter");
            } catch (Exception e) {
                // do nothing...
            }
        }

        if (adapter != null) {
            try {
                // Invoke this by reflection to avoid linking errors on other platforms...

                Class[] defArgs = {jam.framework.Application.class};
                Method registerMethod = adapter.getDeclaredMethod("registerApplication", defArgs);

                if (registerMethod != null) {
                    Object[] args = {application};
                    registerMethod.invoke(adapter, args);
                }


            } catch (Exception e) {
                System.err.println("Exception while loading the ApplicationAdapter:");
                e.printStackTrace();
            }
        } // if there is no adapter then just continue without registering...
    }

    static {
        MAC_OS_X = System.getProperty("os.name").toLowerCase().startsWith("mac os x");
        MAC_OS_X_VERSION = MAC_OS_X ? System.getProperty("os.version") : null;
    }
}