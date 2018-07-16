package com.pft.quicktouch.tool;

import android.content.Context;
import android.content.SharedPreferences;

import com.pft.quicktouch.MyApplicaiton;

/**
 * sp工具
 */
public class SpTool {

    /**
     * 存放int类型的值
     *
     * @param Spname sp名
     * @param name   属性
     * @param value  存放值
     */
    public static void putIntValue(String Spname, String name, int value) {

        SharedPreferences sp = MyApplicaiton.mMyApplicaiton.getSharedPreferences(Spname, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(name, value);
        editor.commit();
    }

    /**
     * 存放String类型的值
     *
     * @param Spname sp名
     * @param name   属性
     * @param value  存放值
     */
    public static void putStringValue(String Spname, String name, String value) {

        SharedPreferences sp = MyApplicaiton.mMyApplicaiton.getSharedPreferences(Spname, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(name, value);
        editor.commit();
    }

    /**
     * 存放Boolean类型的值
     *
     * @param Spname sp名
     * @param name   属性
     * @param value  存放值
     */
    public static void putBooleanValue(String Spname, String name, Boolean value) {

        SharedPreferences sp = MyApplicaiton.mMyApplicaiton.getSharedPreferences(Spname, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(name, value);
        editor.commit();
    }

    /**
     * 获取int类型的值
     *
     * @param Spname  sp名
     * @param intname 属性
     */
    public static int gutIntValue(String Spname, String intname) {

        SharedPreferences sp = MyApplicaiton.mMyApplicaiton.getSharedPreferences(Spname, Context.MODE_PRIVATE);
        int value = sp.getInt(intname, 0);
        return value;
    }

    /**
     * 获取String类型的值
     *
     * @param Spname     sp名
     * @param stringName 属性
     */
    public static String gutStringValue(String Spname, String stringName) {

        SharedPreferences sp = MyApplicaiton.mMyApplicaiton.getSharedPreferences(Spname, Context.MODE_PRIVATE);
        String value = sp.getString(stringName, "");
        return value;
    }

    /**
     * 获取int类型的值
     *
     * @param Spname sp名
     * @param name   属性
     */
    public static boolean gutBooleanValue(String Spname, String name) {

        SharedPreferences sp = MyApplicaiton.mMyApplicaiton.getSharedPreferences(Spname, Context.MODE_PRIVATE);
        boolean value = sp.getBoolean(name, false);
        return value;
    }

    /**
     * 清除登录用户的信息
     */
    public static void clearLoginCount() {
        SharedPreferences sp = MyApplicaiton.mMyApplicaiton.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isLogin", false);
        editor.commit();
    }

}
