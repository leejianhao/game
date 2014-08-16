package org.mrseige.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelActivity extends BaseActivity {
	
	Button button1;
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
	}
	private void findViews() {
		button1 = (Button) findViewById(R.id._1);
		button1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LevelActivity.this,
						MrSeigeActivity.class);
				startActivity(intent);
			}
		});
	}
}
