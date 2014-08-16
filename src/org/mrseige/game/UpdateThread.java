package org.mrseige.game;

import org.mrseige.view.GameView;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class UpdateThread extends Thread {
	 private static final String TAG = UpdateThread.class.getSimpleName();
	 
	 //希望达到的每秒帧数
	 private static final int MAX_FPS = 30;
	 //一帧所需时间
	 private static final int FRAME_PERIOD = 1000/MAX_FPS;
	 
	 private int MAX_FRAME_SKIPS = 2;
		
	 private boolean running = false;
	 private boolean flag = true;
	 
	 private SurfaceHolder surfaceHolder;
	 private GameView gameView;
	 
	 public UpdateThread(GameView gameView) {
		 this.gameView = gameView;
		 surfaceHolder = gameView.getHolder();
	 }
	 
	 public void setRunning(boolean running) {
		 this.running = running;
	 }

	 /**
	  * 开始绘制画布
	  */
	 public void startUpdate() {
	    flag = true;
	 }
	
	 /**
	  * 停止绘制画布
	  */
	 public void stopUpdate() {
		 flag = false;
	 }
	 
	 @Override
	 public void run() {
		Canvas canvas;
		Log.d(TAG, "开始游戏循环！");
		
		long beginTime;
		long timeDiff;
		int sleepTime;
		int framesSkipped = 0;
		while(running) {
			canvas = null;
			if(flag) {
				try {
					canvas = this.surfaceHolder.lockCanvas();
					synchronized(surfaceHolder) {
						framesSkipped =0;
						beginTime = System.currentTimeMillis();
						if(canvas!=null) {
							gameView.render(canvas);
							gameView.update();
							gameView.collisionCheck();
							gameView.explode();
						}
						timeDiff = System.currentTimeMillis() - beginTime;
						sleepTime = (int)(FRAME_PERIOD - timeDiff);
						Log.i(TAG, sleepTime+"");
						if(sleepTime > 0) {
							Log.v(TAG, String.valueOf(sleepTime));
							try {
								Thread.sleep(sleepTime);
							}catch(InterruptedException e) {
								e.printStackTrace();
							}
						}
						
						/*while(sleepTime < 0 && framesSkipped <MAX_FRAME_SKIPS) {
							if(flag)gameView.update();	//更新数据
							sleepTime += FRAME_PERIOD;
							framesSkipped++;
							Log.v(TAG, String.valueOf(sleepTime));
						}*/
					}
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					if(canvas != null) {
						/**结束锁定画布，并提交改变**/
						surfaceHolder.unlockCanvasAndPost(canvas);
					}
				}
			}
		}
	}
}
