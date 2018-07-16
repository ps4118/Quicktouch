package com.pft.quicktouch.tool;

import java.lang.reflect.Method;


import com.pft.quicktouch.MyApplicaiton;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class ScreenTool {
	/**
	 * 获取虚拟键高度
	 * 
	 * @param poCotext
	 * @return
	 */
	public static int getVrtualBtnHeight(Context poCotext) {
		int location[] = getScreenWH(poCotext);
		int realHeiht = getDpi((Activity) poCotext);
		int virvalHeight = realHeiht - location[1];
		return virvalHeight;
	}

	/**
	 * 获取设备密度
	 * 
	 * @param activity
	 * @return
	 */
	public static int getDpi(Activity activity) {
		Display display = activity.getWindowManager().getDefaultDisplay();
		DisplayMetrics dm = new DisplayMetrics();
		int height = 0;
		@SuppressWarnings("rawtypes")
		Class c;
		try {
			c = Class.forName("android.view.Display");
			@SuppressWarnings("unchecked")
			Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
			method.invoke(display, dm);
			height = dm.heightPixels;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return height;
	}

	/**
	 * 获取屏幕宽高
	 * 
	 * @param poCotext
	 * @return
	 */

	public static int[] getScreenWH(Context poCotext) {
		return new int[] { MyApplicaiton.width, MyApplicaiton.height };
	}

	public static int getStatusHeight(Activity activity) {
		int statusHeight = 0;
		Rect localRect = new Rect();
		activity.getWindow().getDecorView()
				.getWindowVisibleDisplayFrame(localRect);
		statusHeight = localRect.top;
		if (0 == statusHeight) {
			Class<?> localClass;
			try {
				localClass = Class.forName("com.android.internal.R$dimen");
				Object localObject = localClass.newInstance();
				int i5 = Integer.parseInt(localClass
						.getField("status_bar_height").get(localObject)
						.toString());
				statusHeight = activity.getResources()
						.getDimensionPixelSize(i5);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		}
		return statusHeight;
	}

	public static void initSystemBar(Activity activity) {

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

			setTranslucentStatus(activity, true);

		}

		SystemStatusTool tintManager = new SystemStatusTool(activity);

		tintManager.setStatusBarTintEnabled(true);

		// 使用颜色资源

		tintManager.setStatusBarTintResource(Color.TRANSPARENT);

	}

	private static void setTranslucentStatus(Activity activity, boolean on) {

		Window win = activity.getWindow();

		WindowManager.LayoutParams winParams = win.getAttributes();

		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;

		if (on) {

			winParams.flags |= bits;

		} else {

			winParams.flags &= ~bits;

		}

		win.setAttributes(winParams);

	}

}
