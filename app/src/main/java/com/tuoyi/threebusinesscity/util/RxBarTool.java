package com.tuoyi.threebusinesscity.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.TypedValue;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by md
 * on 2018/4/17 0017.
 */

public class RxBarTool {
    public RxBarTool() {
    }

    public static void hideStatusBar(Activity activity) {
        noTitle(activity);
        FLAG_FULLSCREEN(activity);
    }

    public static void setTransparentStatusBar(Activity activity) {
        if(Build.VERSION.SDK_INT >= 19) {
            activity.getWindow().addFlags(67108864);
            activity.getWindow().addFlags(134217728);
        }

    }

    public static void setNoTitle(Activity activity) {
        activity.requestWindowFeature(1);
    }

    public static void noTitle(Activity activity) {
        setNoTitle(activity);
    }

    public static void FLAG_FULLSCREEN(Activity activity) {
        activity.getWindow().setFlags(1024, 1024);
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if(resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }

    public static boolean isStatusBarExists(Activity activity) {
        WindowManager.LayoutParams params = activity.getWindow().getAttributes();
        return (params.flags & 1024) != 1024;
    }

    public static int getActionBarHeight(Activity activity) {
        TypedValue tv = new TypedValue();
        return activity.getTheme().resolveAttribute(16843499, tv, true)?TypedValue.complexToDimensionPixelSize(tv.data, activity.getResources().getDisplayMetrics()):0;
    }

    public static void showNotificationBar(Context context, boolean isSettingPanel) {
        String methodName = Build.VERSION.SDK_INT <= 16?"expand":(isSettingPanel?"expandSettingsPanel":"expandNotificationsPanel");
        invokePanels(context, methodName);
    }

    public static void hideNotificationBar(Context context) {
        String methodName = Build.VERSION.SDK_INT <= 16?"collapse":"collapsePanels";
        invokePanels(context, methodName);
    }

    private static void invokePanels(Context context, String methodName) {
        try {
            Object e = context.getSystemService("statusbar");
            Class statusBarManager = Class.forName("android.app.StatusBarManager");
            Method expand = statusBarManager.getMethod(methodName, new Class[0]);
            expand.invoke(e, new Object[0]);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    @TargetApi(19)
    public static void transparencyBar(Activity activity) {
        Window window;
        if(Build.VERSION.SDK_INT >= 21) {
            window = activity.getWindow();
            window.clearFlags(201326592);
            window.getDecorView().setSystemUiVisibility(1792);
            window.addFlags(-2147483648);
            window.setStatusBarColor(0);
            window.setNavigationBarColor(0);
        } else if(Build.VERSION.SDK_INT >= 19) {
            window = activity.getWindow();
            window.setFlags(67108864, 67108864);
        }

    }

//    public static void setStatusBarColor(Activity activity, int colorId) {
//        if(Build.VERSION.SDK_INT >= 21) {
//            Window tintManager = activity.getWindow();
//            tintManager.setStatusBarColor(activity.getResources().getColor(colorId));
//        } else if(Build.VERSION.SDK_INT >= 19) {
//            transparencyBar(activity);
//            SystemBarTintManager tintManager1 = new SystemBarTintManager(activity);
//            tintManager1.setStatusBarTintEnabled(true);
//            tintManager1.setStatusBarTintResource(colorId);
//        }
//
//    }

    public static int StatusBarLightMode(Activity activity) {
        byte result = 0;
        if(Build.VERSION.SDK_INT >= 19) {
            if(MIUISetStatusBarLightMode(activity.getWindow(), true)) {
                result = 1;
            } else if(FlymeSetStatusBarLightMode(activity.getWindow(), true)) {
                result = 2;
            } else if(Build.VERSION.SDK_INT >= 23) {
                activity.getWindow().getDecorView().setSystemUiVisibility(9216);
                result = 3;
            }
        }

        return result;
    }

    public static void StatusBarLightMode(Activity activity, int type) {
        if(type == 1) {
            MIUISetStatusBarLightMode(activity.getWindow(), true);
        } else if(type == 2) {
            FlymeSetStatusBarLightMode(activity.getWindow(), true);
        } else if(type == 3) {
            activity.getWindow().getDecorView().setSystemUiVisibility(9216);
        }

    }

    public static void StatusBarDarkMode(Activity activity, int type) {
        if(type == 1) {
            MIUISetStatusBarLightMode(activity.getWindow(), false);
        } else if(type == 2) {
            FlymeSetStatusBarLightMode(activity.getWindow(), false);
        } else if(type == 3) {
            activity.getWindow().getDecorView().setSystemUiVisibility(0);
        }

    }

    public static boolean FlymeSetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if(window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt((Object)null);
                int value = meizuFlags.getInt(lp);
                if(dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }

                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception var8) {
                ;
            }
        }

        return result;
    }

    public static boolean MIUISetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if(window != null) {
            Class clazz = window.getClass();

            try {
                boolean darkModeFlag = false;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                int darkModeFlag1 = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", new Class[]{Integer.TYPE, Integer.TYPE});
                if(dark) {
                    extraFlagField.invoke(window, new Object[]{Integer.valueOf(darkModeFlag1), Integer.valueOf(darkModeFlag1)});
                } else {
                    extraFlagField.invoke(window, new Object[]{Integer.valueOf(0), Integer.valueOf(darkModeFlag1)});
                }

                result = true;
            } catch (Exception var8) {
                ;
            }
        }

        return result;
    }
}
