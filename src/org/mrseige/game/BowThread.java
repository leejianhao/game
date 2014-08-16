package org.mrseige.game;

import org.mrseige.view.GameView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

public class BowThread extends Thread {
	 private boolean running = false;
	 private SurfaceHolder surfaceHolder;
	 private GameView gameView;
	 private Bitmap bitmap;
	 private float rotate;
	 
	 public BowThread(GameView gameView, Bitmap bitmap) {
		 this.gameView = gameView;
		 surfaceHolder = gameView.getHolder();
		 this.bitmap = bitmap;
	 }
	 
	 public void setRunning(boolean running) {
		 this.running = running;
	 }
	 
	 public float getRotate() {
		return rotate;
	}

	public void setRotate(float rotate) {
		this.rotate = rotate;
	}

	@Override
	 public void run() {
		// TODO Auto-generated method stub            
         Canvas canvas = null;  
         while(running){  
             try {  
                 canvas = surfaceHolder.lockCanvas(); //获取画布  
                 Paint paint = new Paint();  
                 //canvas.drawBitmap(rotateImg, 0, 0, paint); //绘制旋转的背景  
                 //创建矩阵控制图片旋转和平移  
                 Matrix matrix = new Matrix();  
                 //设置旋转角度  
                 matrix.postRotate(rotate,  
                		 bitmap.getWidth() / 2, bitmap.getHeight() / 2);  
                 //设置左边距和上边距  
                 matrix.postTranslate(0, 0);  
                 //绘制旋转图片  
                 canvas.drawBitmap(bitmap, matrix, paint);  
                 //休眠控制最大帧率为每秒3绘制30次  
                 Thread.sleep(30);  
                   
                 surfaceHolder.unlockCanvasAndPost(canvas);//解锁画布，提交画好的图像  
             } catch (Exception e) {  
                 // TODO: handle exception  
             }     
               
         }  
	 }
}
