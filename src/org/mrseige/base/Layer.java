package org.mrseige.base;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.MotionEvent;

public class Layer extends Tiled {
	/**是否移动**/
	protected boolean move;

	public boolean isMove() {
		return move;
	}

	public void setMove(boolean move) {
		this.move = move;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	protected Bitmap bitmap;
	
	public Layer(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.save();//保存画布，使以后画面不受下面的影响
		RectF rect = new RectF(x,y,width,height);
		if(bitmap!=null)
			canvas.drawBitmap(bitmap, null, rect, null);
		else
			canvas.drawRect(rect, null);
		canvas.restore();//读取开始保存的画布
		rect = null;
	}

	@Override
	public boolean touch(MotionEvent ev) {
		return this.isContains((int)ev.getX(),(int)ev.getY());
	}
	
}

