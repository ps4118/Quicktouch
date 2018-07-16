package com.pft.quicktouch.tool;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.List;


import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络判断工具
 */
public class NetTool {
    /**
     * 判断是否联网
     *
     * @param context
     * @return
     */
    public static boolean checkNetandWifi(Context context) {
        @SuppressWarnings("static-access")
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfo = cm.getAllNetworkInfo();
        if (networkInfo != null && networkInfo.length > 0) {
            for (int i = 0; i < networkInfo.length; i++) {
                if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        ToastTool.showToast(context, "请检查网络设置");
        return false;

    }


//    /**
//     * 获取ip
//     */
//    public static String getLocalHostIp() {
//        String ipaddress = "";
//        try {
//
//            for (Enumeration<NetworkInterface> en = NetworkInterface
//
//                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
//
//                NetworkInterface intf = en.nextElement();
//
//                for (Enumeration<InetAddress> ipAddr = intf.getInetAddresses(); ipAddr
//
//                        .hasMoreElements(); ) {
//
//                    InetAddress inetAddress = ipAddr.nextElement();
//                    // ipv4地址
//                    if (!inetAddress.isLoopbackAddress()
//                            && InetAddressUtils.isIPv4Address(inetAddress
//                            .getHostAddress())) {
//                        ipaddress = inetAddress.getHostAddress();
//                        System.out.println("ip==>>" + ipaddress);
//                        return ipaddress;
//
//                    }
//
//                }
//
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return "";
//    }

    /**
     * 返回app运行状态 1:程序在前台运行 2:程序在后台运行 3:程序未启动 注意：需要配置权限<uses-permission
     * android:name="android.permission.GET_TASKS" />
     */
    public static boolean getAppSatus(Context context, String pageName) {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> list = am.getRunningTasks(100);
        // 判断程序是否在栈顶
        for (RunningTaskInfo info : list) {
            if (info.topActivity.getPackageName().equals(pageName)
                    && info.baseActivity.getPackageName().equals(pageName)) {
                return true;
            }
        }
        return false;
    }
}
