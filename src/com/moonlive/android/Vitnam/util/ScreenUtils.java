package com.moonlive.android.Vitnam.util;

import java.lang.reflect.Method;

import android.app.Activity;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;

public class ScreenUtils {
	
	
	public static final Logger log = Logger.getInstance();
	public static int[] getScreenSize2(Activity activity) {
		// 获取屏幕宽高（方法1）
		int screenWidth = activity.getWindowManager().getDefaultDisplay()
				.getWidth(); // 屏幕宽（像素，如：480px）
		int screenHeight = activity.getWindowManager().getDefaultDisplay()
				.getHeight(); // 屏幕高（像素，如：800p）
		// 获取屏幕密度（方法2）
		DisplayMetrics dm = new DisplayMetrics();
		dm = activity.getResources().getDisplayMetrics();
		float density = dm.density; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
		int densityDPI = dm.densityDpi; // 屏幕密度（每寸像素：120/160/240/320）
		float xdpi = dm.xdpi;
		float ydpi = dm.ydpi;
		screenWidth = dm.widthPixels; // 屏幕宽（像素，如：480px）
		screenHeight = dm.heightPixels; // 屏幕高（像素，如：800px）
		// 获取屏幕密度（方法3）
		dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		density = dm.density; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
		densityDPI = dm.densityDpi; // 屏幕密度（每寸像素：120/160/240/320）
		xdpi = dm.xdpi;
		ydpi = dm.ydpi;
		int screenWidthDip = dm.widthPixels; // 屏幕宽（dip，如：320dip）
		int screenHeightDip = dm.heightPixels; // 屏幕宽（dip，如：533dip）
		screenWidth = (int) (dm.widthPixels * density + 0.5f); // 屏幕宽（px，如：480px）
		screenHeight = (int) (dm.heightPixels * density + 0.5f); // 屏幕高（px，如：800px）
		log.i("Screen width = " + screenWidth +" screenHeight = " + screenHeight);
		return new int[] {screenWidth, screenHeight + 50};
	}
	
	
	
	@SuppressWarnings("deprecation")
	public static int[] getScreenSize(Activity activity) {
		int[] screen = new int[2];
		DisplayMetrics metrics = new DisplayMetrics();
		Display display = activity.getWindowManager().getDefaultDisplay();
		display.getMetrics(metrics);
		int ver = Build.VERSION.SDK_INT;
		if (ver < 13) {
			screen[1] = metrics.heightPixels;
		} else if (ver == 13) {
			try {
				Method method = display.getClass().getMethod("getRealHeight");
				screen[1] = (Integer) method.invoke(display);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(ver == 17){
			try {
				Method method = display.getClass().getMethod("getHeight");
				screen[1] = (Integer) method.invoke(display) + 50;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (ver > 13 && ver != 17) {
			try {
				Method method = display.getClass().getMethod("getRawHeight");
				screen[1] = (Integer) method.invoke(display);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		screen[0] = display.getWidth();
		return screen;
	}
}
