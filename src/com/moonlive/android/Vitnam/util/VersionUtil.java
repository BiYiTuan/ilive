package com.moonlive.android.Vitnam.util;

import android.content.Context;
import android.content.pm.PackageManager;

import com.moonlive.android.Vitnam.Configs;

public class VersionUtil {

	
	public static int getVerCode(Context paramContext) {
		try {
			int i = paramContext.getPackageManager().getPackageInfo(
					Configs.PKG_NAME, 0).versionCode;
			return i;
		} catch (PackageManager.NameNotFoundException localNameNotFoundException) {
			localNameNotFoundException.printStackTrace();
		}
		return -1;
	}

	public static String getVerName(Context paramContext) {
		try {
			String str = paramContext.getPackageManager().getPackageInfo(
					Configs.PKG_NAME, 0).versionName;
			return str;
		} catch (PackageManager.NameNotFoundException localNameNotFoundException) {
			localNameNotFoundException.printStackTrace();
		}
		return "";
	}
}
