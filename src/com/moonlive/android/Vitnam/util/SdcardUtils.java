package com.moonlive.android.Vitnam.util;

public class SdcardUtils {
	
	public static boolean existSdcard(){
		if(android.os.Environment.getExternalStorageState().
				equals(android.os.Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}
}
