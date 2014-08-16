package org.mrseige.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * 
 * 游戏主界面
 * @author ljh
 *
 */
public class MainPage extends BaseActivity implements OnClickListener{
	
	private Button start;
	private Button ranking_board;
	private Button exit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//设置全屏
        this.setFullScreen();
        //设置屏幕常亮
        this.setScreenLit();
        setContentView(R.layout.main_page);
        
        findViews();
	}
	
	
	private void findViews() {
		start = (Button) findViewById(R.id.mainpage_start);
		start.setOnClickListener(this);
		ranking_board = (Button) findViewById(R.id.mainpage_ranking_board);
		ranking_board.setOnClickListener(this);
		exit = (Button)findViewById(R.id.mainpage_exit);
		exit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
			case R.id.mainpage_start:
				intent.setClass(MainPage.this, LevelActivity.class);
				startActivity(intent);
				break;
			case R.id.mainpage_ranking_board:
				Toast.makeText(getApplicationContext(), "该功能暂未开放！", Toast.LENGTH_LONG).show();;
				break;
			case R.id.mainpage_exit:
		         finish();
		         break;
		}
	}

	
}	
