package com.pft.quicktouch;

import android.app.Application;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.kongzue.dialog.v2.DialogSettings;
import com.pft.quicktouch.bean.User;
import com.pft.quicktouch.tool.SpTool;

import static com.kongzue.dialog.v2.DialogSettings.THEME_LIGHT;
import static com.kongzue.dialog.v2.DialogSettings.TYPE_KONGZUE;
import static com.kongzue.dialog.v2.DialogSettings.TYPE_MATERIAL;

/**
 * 项目采用mvp框架模式
 * 对于简单的界面，就按常规的模式处理，不用按照mvp模式划分
 * <p>
 * pullToRefresh控件点击item,获取对象需要-1操作，因为控件加了headview,position=0为headview
 */
public class MyApplicaiton extends Application {
    public static MyApplicaiton mMyApplicaiton;
    public static int width;//屏幕宽度
    public static int height;//屏幕高度
    //全局user,方便获取信息
    public static User user = new User();
    //是否已经登录
    public static boolean isLogin = false;
    //手机号（用户名）
    public static String phone = "";
    //密码
    public static String pass = "";


    public static final String tag = MyApplicaiton.class.getSimpleName();

    @Override

    public void onCreate() {
        super.onCreate();
        mMyApplicaiton = this;
        getWight();
        //全局设置对话框的样式
        DialogSettings.type = TYPE_KONGZUE;
        DialogSettings.tip_theme = THEME_LIGHT;
    }

    /**
     * 获取屏幕宽高
     */
    void getWight() {
        WindowManager wm = (WindowManager) this.getSystemService(WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;         // 屏幕宽度（像素）
        height = dm.heightPixels;
    }


}
