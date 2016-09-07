package com.app.utiles.other;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import com.app.ui.activity.base.BaseApplication;


public class ActivityUtile {
    /**
     * activity的通用跳转
     */
    public static void startActivityCommon(Class<?> activity) {
        startActivityCommon(activity, "","", null);
    }
    /**
     * activity的通用跳转
     */
    public static void startActivityCommon(Class<?> activity, String arg0) {
        startActivityCommon(activity, arg0,"", null);
    }
    /**
     * activity的通用跳转
     */
    public static void startActivityCommon(Class<?> activity, String arg0, String arg1) {
        startActivityCommon(activity, arg0,arg1, null);
    }
    /**
     * activity的通用跳转
     */
    public static void startActivityCommon(Class<?> activity, Bundle bundle) {
        startActivityCommon(activity, "","", bundle);
    }
    /**
     * activity的通用跳转
     */
    public static void startActivityCommon(Class<?> activity, String arg0, Bundle bundle) {
        startActivityCommon(activity, arg0,"", bundle);
    }

    /**
     * activity的通用跳转
     */
    public static void startActivityCommon(Class<?> activity, String arg0, String arg1,
                                           Bundle bundle) {
        Intent it = new Intent();
        if (bundle != null) {
            it.putExtras(bundle);
        }
        if (!TextUtils.isEmpty(arg0)) {
            it.putExtra("arg0", arg0);
        }
        if (!TextUtils.isEmpty(arg0)) {
            it.putExtra("arg1", arg1);
        }
        it.setClass(BaseApplication.context, activity);
        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.context.startActivity(it);
    }

    /**
     * 关闭activity
     */
    public static void closeTopActivity(Activity activity, Class<?> cl) {
        Intent intent = new Intent();
        intent.setClass(activity, cl);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
    }
    /**
     * 打开网络设置界面
     */
    public static void openSetting(Activity activity) {
        Intent intent = new Intent("/");
        ComponentName cm =
                new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
        intent.setComponent(cm);
        intent.setAction("android.intent.action.VIEW");
        activity.startActivityForResult(intent, 0);
    }
    /**
     * 拨号界面
     */
    public static void callPhone(String tile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + tile));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.context.startActivity(intent);
    }

    /**
     * 分享
     *
     * @param subject
     * @param text
     */
    public static void shareFor(String subject, String text, String chooserName) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent = Intent.createChooser(intent, chooserName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.context.startActivity(intent);
    }
}
