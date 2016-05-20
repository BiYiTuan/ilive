package com.moonlive.android.Vitnam;

import java.lang.Thread.UncaughtExceptionHandler;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class UncaughtException implements UncaughtExceptionHandler {
    private final static String TAG = "UncaughtException";
    private static UncaughtException mUncaughtException;
    private Context context;
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private UncaughtException() {
    }

    /**
     * 同步方法，以免单例多线程环境下出现异常
     * 
     * @return
     */
    public synchronized static UncaughtException getInstance() {
        if (mUncaughtException == null) {
            mUncaughtException = new UncaughtException();
        }
        return mUncaughtException;
    }

    /**
     * 初始化，把当前对象设置成UncaughtExceptionHandler处理器
     */
    public void init() {
        Thread.setDefaultUncaughtExceptionHandler(mUncaughtException);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
    	Log.e(TAG, "uncaughtException thread : " + thread + "||name=" + thread.getName() + "||id=" + thread.getId() + "||exception=" + ex);
    	Toast.makeText(getContext(), "error", 500).show();
    }

}
