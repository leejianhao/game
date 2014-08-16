package org.mrseige.sprite;

import org.mrseige.base.Layer;
import org.mrseige.base.TouchAble;

import android.view.MotionEvent;

public class FixTool extends Layer implements TouchAble{
	
	public FixTool(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
		
	public boolean onTouch(MotionEvent event) {
		return true;
	}
}
