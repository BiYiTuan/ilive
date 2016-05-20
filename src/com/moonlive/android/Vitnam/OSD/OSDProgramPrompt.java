package com.moonlive.android.Vitnam.OSD;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.moonlive.android.iptv.R;

public class OSDProgramPrompt extends OSD{
	
	private View mContainer;
	private TextView mTextPrompt;
	public OSDProgramPrompt(Context context,View container){
		mContainer = container;
		mTextPrompt = (TextView) mContainer.findViewById(R.id.program_prompt);
		setProperity(OSD.PROPERITY_LEVEL_3);
	}
	
	@Override
	public void setVisibility(int visibility) {
		mTextPrompt.setVisibility(visibility);
	}

	@Override
	public int getVisibility() {
		return mTextPrompt.getVisibility();
	}

}
