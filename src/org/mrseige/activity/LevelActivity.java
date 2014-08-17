package org.mrseige.activity;

import org.mrseige.common.SysConstant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelActivity extends BaseActivity {
	
	Button button1,button2,button3;
	private int passedLevel;
	private Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//设置全屏
        this.setFullScreen();
        //设置屏幕常亮
        this.setScreenLit();
        
		setContentView(R.layout.level_page);
		
		findViews();
		
		context = this;
		passedLevel = GamePref.getInstance(this).getLevelPref();
		
	}
	private void findViews() {
		button1 = (Button) findViewById(R.id._1);
		button1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LevelActivity.this,
						MrSeigeActivity.class);
				intent.putExtra(SysConstant.LEVEL_REF, 0);
				startActivity(intent);
			}
		});
		button2 = (Button) findViewById(R.id._2);
		button2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LevelActivity.this,
						MrSeigeActivity.class);
				intent.putExtra(SysConstant.LEVEL_REF, 1);
				startActivity(intent);
			}
		});
		button3 = (Button) findViewById(R.id._3);
		button3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LevelActivity.this,
						MrSeigeActivity.class);
				intent.putExtra(SysConstant.LEVEL_REF, 2);
				startActivity(intent);
			}
		});
	}
}
