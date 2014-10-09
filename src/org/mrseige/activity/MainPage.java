package org.mrseige.activity;

import org.mrseige.common.BitMapManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 
 * 游戏主界面
 * @author ljh
 *
 */
public class MainPage extends BaseActivity implements OnClickListener{
	
	/**
	 * 开始
	 */
	private ImageView play;
	/**
	 * 排行榜
	 */
	private ImageView rank;
	/**
	 * 升级
	 */
	private ImageView upgrade;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//设置全屏
        this.setFullScreen();
        //设置屏幕常亮
        this.setScreenLit();
        setContentView(R.layout.mainpage);
        
        findViews();
        
	}
	
	
	private void findViews() {
		play = (ImageView) findViewById(R.id.playIcon);
		play.setOnClickListener(this);
		rank = (ImageView) findViewById(R.id.rankIcon);
		rank.setOnClickListener(this);
		upgrade = (ImageView) findViewById(R.id.upgradeIcon);
		upgrade.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
			case R.id.playIcon:
				intent.setClass(MainPage.this, LevelActivity.class);
				startActivity(intent);
				break;
			case R.id.rankIcon:
				Toast.makeText(getApplicationContext(), "该功能暂未开放！", Toast.LENGTH_LONG).show();;
				break;
			case R.id.upgradeIcon:
		         finish();
		         break;
		}
	}

	
}	
