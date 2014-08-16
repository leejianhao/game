package org.mrseige.sprite;

import org.mrseige.base.Layer;
import org.mrseige.base.TouchAble;
import org.mrseige.view.GameView;

import android.util.Log;
import android.view.MotionEvent;

public class FrozenWeapon extends Layer implements TouchAble{
	
	private static final String TAG = GameView.class.getSimpleName();
	
	public FrozenWeapon(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
		
	public boolean onTouch(MotionEvent event) {
		// TODO Auto-generated method stub
		int x = (int) event.getX();
		int y = (int) event.getY();
		if (true) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				if(!touch(event)) return false;
				break;
			case MotionEvent.ACTION_MOVE:
				this.x = x - this.getW() / 2;
				this.y = y - this.getH() / 2;
				this.width = this.x+this.getBitmap().getWidth();
				this.height = this.y+this.getBitmap().getHeight();
				Log.d(TAG, "x="+this.x+" y="+this.y+" width="+width+" height="+height);
				break;
			case MotionEvent.ACTION_UP:
				break;
			default:
				break;
			}
		}
		return true;
	}
}
