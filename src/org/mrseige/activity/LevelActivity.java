package org.mrseige.activity;

import org.mrseige.common.SysConstant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class LevelActivity extends BaseActivity {
	
	private ImageView gotomainpage;
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
        
		setContentView(R.layout.levelpage);
		passedLevel = GamePref.getInstance(this).getLevelPref();
		findViews();
		context = this;
		
	}
	
	private void findViews() {
		ImageView imageView;
		for(int i=0;i<5;i++) {
			imageView = ((ImageView) findViewById(R.id.imageView1+i));
			if(i<=passedLevel%5+1) {
				imageView.setImageResource(R.drawable.level_1_1+i);
				imageView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(LevelActivity.this,
								MrSeigeActivity.class);
						intent.putExtra(SysConstant.LEVEL_REF, passedLevel+1);
						startActivity(intent);
					}
				});
			}else {
				imageView.setImageResource(R.drawable.level_1_1+5);
				imageView.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						Toast.makeText(context, R.string.new_round, Toast.LENGTH_LONG).show();
					}
					
				});
			}
		}
		/*imageView1 = (ImageView) findViewById(R.id.imageView1);
		imageView1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LevelActivity.this,
						MrSeigeActivity.class);
				intent.putExtra(SysConstant.LEVEL_REF, 0);
				startActivity(intent);
			}
		});
		imageView2 = (ImageView) findViewById(R.id.imageView2);
		imageView2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LevelActivity.this,
						MrSeigeActivity.class);
				intent.putExtra(SysConstant.LEVEL_REF, 1);
				startActivity(intent);
			}
		});
		imageView3 = (ImageView) findViewById(R.id.imageView3);
		imageView3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LevelActivity.this,
						MrSeigeActivity.class);
				intent.putExtra(SysConstant.LEVEL_REF, 2);
				startActivity(intent);
			}
		});*/
		
		gotomainpage = (ImageView) findViewById(R.id.gotomainpage);
		gotomainpage.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		} );
	}
}
