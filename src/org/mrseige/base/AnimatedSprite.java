package org.mrseige.base;

import java.io.Serializable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

public class AnimatedSprite extends Tiled implements Serializable{

	protected Bitmap[] bitmap;
	protected boolean isLoop;
	protected boolean isEnd;
	private int position=0;
	private long start_timer;
	private static final int ANIMA_TIMER=100;//每帧动画绘制所需要的时间
	
	private Paint paint;
	public AnimatedSprite() {
		
	}
	
	public AnimatedSprite(int x, int y, int width, int height) {
			super(x, y, width, height);
			paint = new Paint();
			paint.setColor(Color.RED);
			// TODO Auto-generated constructor stub
	}
	
	@Override
	public void draw(Canvas canvas) {
		if(bitmap==null)return;
		canvas.save();
		RectF rect = new RectF(x,y,width,height);
		Log.i("value", " "+this.getPosition());
		//Bug:避免怪物Bitmap变化时，引发的IndexOutOfBoundException
		if(position>=bitmap.length)position=bitmap.length-1; 
	//	canvas.drawRect(x, y, width, height, paint);
		canvas.drawBitmap(bitmap[position], null,rect, null);
		canvas.restore();
		if(!isEnd){
			long end = System.currentTimeMillis();
			if(end-start_timer>=ANIMA_TIMER){
				start_timer = end;
				position++;
				if(position>=bitmap.length){
					position = 0;
					isEnd = true;
					//循环时，还原帧
					if(isLoop){
						isEnd = false;
					}
				}
			}
		}
	}
	
	/**
	 * 计数器
	 */
	private int count =0;
	/**
	 * 标记怪物被射中
	 */
	private boolean attacked = false;
	
	public boolean isAttacked() {
		return attacked;
	}

	public void setAttacked(boolean attacked) {
		this.attacked = attacked;
	}

	public void draw2(Canvas canvas) {
		if(count++<=3) {
			canvas.save();
			canvas.drawRect(x, y, width, height, paint);
			canvas.restore();
		}else {
			attacked = false;
			count = 0;
		}
	}
	
	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public Bitmap[] getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap[] bitmap) {
		this.bitmap = bitmap;
	}

	public boolean isLoop() {
		return isLoop;
	}

	public void setLoop(boolean isLoop) {
		this.isLoop = isLoop;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public boolean touch(MotionEvent ev) {
		return this.isContains((int)ev.getX(),(int) ev.getY());
	}

}

