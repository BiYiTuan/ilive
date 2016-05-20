package com.moonlive.android.Vitnam;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MsgService extends Service {

	
	@Override
	public void onCreate() {
		super.onCreate();
		return;
	}


	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

}
