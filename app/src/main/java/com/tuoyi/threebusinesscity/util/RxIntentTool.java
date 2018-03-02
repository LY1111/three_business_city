package com.tuoyi.threebusinesscity.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import java.io.File;

/**
 * Created by md
 * on 2018/3/2 0002.
 */

public class RxIntentTool {
    public RxIntentTool() {
    }

//    public static Intent getInstallAppIntent(Context context, String filePath) {
//        File apkfile = new File(filePath);
//        if(!apkfile.exists()) {
//            return null;
//        } else {
//            Intent intent = new Intent("android.intent.action.VIEW");
//            Uri contentUri = RxFileTool.getUriForFile(context, apkfile);
//            intent.setFlags(268435456);
//            if(Build.VERSION.SDK_INT >= 24) {
//                intent.addFlags(3);
//            }
//
//            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
//            return intent;
//        }
//    }

    public static Intent getUninstallAppIntent(String packageName) {
        Intent intent = new Intent("android.intent.action.DELETE");
        intent.setData(Uri.parse("package:" + packageName));
        return intent.addFlags(268435456);
    }

    public static Intent getLaunchAppIntent(Context context, String packageName) {
        return getIntentByPackageName(context, packageName);
    }

    private static Intent getIntentByPackageName(Context context, String packageName) {
        return context.getPackageManager().getLaunchIntentForPackage(packageName);
    }

    public static Intent getAppInfoIntent(String packageName) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        return intent.setData(Uri.parse("package:" + packageName));
    }

    public static Intent getShareInfoIntent(String info) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        return intent.putExtra("android.intent.extra.TEXT", info);
    }

    public static Intent getComponentNameIntent(String packageName, String className) {
        return getComponentNameIntent(packageName, className, (Bundle)null);
    }

    public static Intent getComponentNameIntent(String packageName, String className, Bundle bundle) {
        Intent intent = new Intent("android.intent.action.VIEW");
        if(bundle != null) {
            intent.putExtras(bundle);
        }

        ComponentName cn = new ComponentName(packageName, className);
        intent.setComponent(cn);
        return intent.addFlags(268435456);
    }

    public static Intent getAppDetailsSettingsIntent(Context mContext) {
        Intent localIntent = new Intent();
        localIntent.addFlags(268435456);
        if(Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", mContext.getPackageName(), (String)null));
        } else if(Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction("android.intent.action.VIEW");
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", mContext.getPackageName());
        }

        return localIntent;
    }

    public static Intent getAppDetailsSettingsIntent(String packageName) {
        Intent localIntent = new Intent();
        localIntent.addFlags(268435456);
        if(Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", packageName, (String)null));
        } else if(Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction("android.intent.action.VIEW");
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", packageName);
        }

        return localIntent;
    }
}

