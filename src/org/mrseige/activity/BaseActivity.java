package org.mrseige.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.text.format.Time;
import android.view.Window;
import android.view.WindowManager;

/**
 * 自定义Activity基类，用于派生
 * @author ljh
 *
 */
public class BaseActivity extends Activity {
	
	/**
	 * 全屏显示
	 * @date 2014-4-13
	 */
	protected void setFullScreen() {
		 //取消状态栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        //关闭屏幕顶部的标题
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	}
	
	/**
	 * 设置屏幕常亮
	 * @date 2014-4-15
	 */
	protected void setScreenLit() {
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); 
	}
	/**
	 * java定时器
	 * @date 2014-4-13
	 * @param task the task to schedule.
	 * @param delay time of execution.
	 * @see java.util.Timer#schedule(TimerTask, java.util.Date)
	 */
	protected void timeToggle(TimerTask task, long delay) {
		Timer timer = new Timer();
		timer.schedule(task, delay);
	}

}
