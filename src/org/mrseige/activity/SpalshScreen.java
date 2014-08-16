package org.mrseige.activity;

import java.util.TimerTask;

import android.content.Intent;
import android.os.Bundle;

/**
 * 游戏授权页
 * @author ljh
 *
 */
public class SpalshScreen extends BaseActivity {
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //设置全屏
        this.setFullScreen();
        
        //3秒后切入主界面
        this.timeToggle(new TimerTask() {
        	@Override
        	public void run() {
        		Intent intent = new Intent(SpalshScreen.this, MainPage.class);
        		startActivity(intent);
        		  //结束StepIntoActivity生命周期
                SpalshScreen.this.finish();	
        	}
        }, 2000);
        
        setContentView(R.layout.splash);
    }
}