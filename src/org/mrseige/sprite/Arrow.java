package org.mrseige.sprite;

import org.mrseige.base.Layer;
import org.mrseige.common.DensityUtil;
import org.mrseige.common.Pos;
import org.mrseige.common.SysConstant;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Log;

public class Arrow extends Layer {
	
	private static final String TAG = "Arrow";
	
	private Bitmap rotatedBitmap;
	private Rect rect;
	private float degree;
	private float arrowOutDegree;
	
	private Pos pos ;
	
	private Context context ;
	
	public Rect getRect() {
		return rect;
	}
	
	public Pos getPos() {
		return pos;
	}

	public void setPos(Pos pos) {
		this.pos = pos;
	}

	public void setDegree(float degree) {
		this.degree = degree;
	}
	
	public float getDegree() {
		return degree;
	}

	public Bitmap getRotatedBitmap() {
		return rotatedBitmap;
	}

	public Arrow(int x, int y, int width, int height, Context context) {
		super(x, y, width, height);
		this.context = context;
		Log.v(TAG, "x="+x+" y="+y+" width="+width+" height"+height);
		rect = new Rect();
		pos = new Pos(0, 0);
	}
	
	/*
	    * Get next pos by angle and speed
	    * 
	    * @param[in, out]: pos current position, it will be updated.
	    * 
	    * @param[in]: speed speed per step
	    * 
	    * @param[in]:angle angle to move
	    */
	public void getNextPos(Pos pos, float speed, float degree) {
	      double radians = Math.toRadians(degree);
	      int xStep = (int) (pos.x + speed * Math.sin(radians));
	      Log.v(TAG, "xStep"+xStep);
	      int yStep = (int) (pos.y - speed * Math.cos(radians));
	      Log.v(TAG, "yStep"+yStep);
	      pos.x = xStep;
	      pos.y = yStep;
	   }
	
	public void calRectByPos(Rect rect, Pos pos, int width, int height) {
		  rect.left = (int) (pos.x - width / 2);
	      rect.right = rect.left + width;
	      rect.top = (int) (pos.y - height / 2);
	      rect.bottom = rect.top + height;
	   }
	
	public void getNextPos(Pos pos, float degree,Bitmap bitmap) {
		  getNextPos(pos, DensityUtil.dip2px(context, SysConstant.ARROW_SPEED), degree);
	      calRectByPos(rect, pos, bitmap.getWidth(), bitmap.getHeight());
	}
	
	private void rotate(float degree) {
		Bitmap b = this.getBitmap();
	      if (b != null) {
	         Matrix m = new Matrix();
	         m.setRotate(degree);
	        
	         try {
	            rotatedBitmap = Bitmap.createBitmap(b, 0, 0, b.getWidth(), b
	                  .getHeight(), m, true);
	           /* if (b != rotatedBitmap && !b.isRecycled()) {    
                    b.recycle();  
                    b = rotatedBitmap;    
                }    */
	         } catch (OutOfMemoryError ex) {
	        	 
	         }
	      }
	 //     return b;
	}
	
   public void drawRotate(Canvas canvas) {
      
      canvas.save();
      Bitmap bmp;
      if(rotatedBitmap == null) {
    	  rotate(degree);
    	  bmp = rotatedBitmap;
    	  arrowOutDegree = degree;
    	  getNextPos(pos, arrowOutDegree,bmp);
      }else {
    	  bmp = rotatedBitmap;
    	  getNextPos(pos, arrowOutDegree,bmp);
      }
      Log.v(TAG,"rect.left="+rect.left+" rect.top="+rect.top);
      canvas.drawBitmap(bmp, rect.left, rect.top, null);
      canvas.restore();
   }

   /**
	 *检测是否在屏幕外 
	 */
	public boolean checkOutOfScreen(int screenWidth, int screenHeight) {
		if(rect.right<0 || rect.left>screenWidth || rect.bottom<0) {
			return true;
		}
		return false;
	}
}
