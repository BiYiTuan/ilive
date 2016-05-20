package com.moonlive.android.Vitnam.OSD;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import com.moon.utils.AESSecurity;
import com.moon.utils.MD5Util;
import com.moonlive.android.Vitnam.Configs;
import com.moonlive.android.Vitnam.util.Logger;
import com.moonlive.android.Vitnam.util.MACUtils;

public class ProgramCache {
	
	static Logger logger = Logger.getInstance();
	static String AESKEY="osJnrSIHvx3YShWt";
	public static boolean isExist(){
		logger.i("Program cache file = " + Configs.CONTENT_CACHE_FILE);
		File file = new File(Configs.CONTENT_CACHE_FILE);
		return file.exists();
	}
	
	public static boolean isDirectory(){
		File file = new File(Configs.CONTENT_CACHE_FILE);
		return file.isDirectory();
	}
	
	public static boolean delFile(){
		File file = new File(Configs.CONTENT_CACHE_FILE);
		if(file.exists())
			return file.delete();
		return false;
	}
	
	
	public static void save(String saveGsonStr){
		File file = new File(Configs.CONTENT_CACHE_FILE);
		if(file.isDirectory()) return;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			String ciphertext = AESSecurity.encrypt(saveGsonStr, MD5Util.getStringMD5_16(AESKEY));
			fos.write(ciphertext.getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		} finally {
			if(null != fos)
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	@SuppressWarnings("resource")
	public static String getGsonString(){
		File file = new File(Configs.CONTENT_CACHE_FILE);
		if(file.isDirectory()) return "";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(Configs.CONTENT_CACHE_FILE));
			String resultStr = "";
			String data = br.readLine(); 
			if(null != data)
				resultStr += data;
			while(data != null){  
				data = br.readLine(); 
				if(null != data)
					resultStr += data;
			} 
			String expressly = AESSecurity.decrypt(resultStr, MD5Util.getStringMD5_16(AESKEY));
			return expressly;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  catch (IOException e) {
			e.printStackTrace();
		}  catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	
	public static boolean checkIsSame(String gsonStr){
		File file = new File(Configs.CONTENT_CACHE_FILE);
		String md5_01 = MD5Util.getFileMD5String(file);
		String cipherText = AESSecurity.encrypt(gsonStr, 
							MD5Util.getStringMD5_16(MACUtils.getMac()));
		String md5_02 = MD5Util.getStringMD5_32(cipherText);
		logger.i("md51 = " + md5_01 + "  md52 = " + md5_02);
		if(null == md5_01) return false;
		if(null == md5_02) return false;
		if(md5_01.equals(md5_02)) return true;
		return false;
	}
	
}
