package org.mrseige.activity;

import org.mrseige.common.SysConstant;
import org.mrseige.view.GameView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class LevelActivity extends BaseActivity {
	
	private static final String TAG = LevelActivity.class.getSimpleName();
	
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
		
		context = this;
		
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		passedLevel = GamePref.getInstance(this).getLevelPref();
		findViews();
	}
	
	private void findViews() {
		ImageView imageView = ((ImageView)findViewById(R.id.imageView1));
		for(int i=0;i<5;i++) {
			switch(i) {
			case 0:imageView = ((ImageView)findViewById(R.id.imageView1));
			break;
			case 1:imageView = ((ImageView)findViewById(R.id.imageView2));
			break;
			case 2:imageView = ((ImageView)findViewById(R.id.imageView3));
			break;
			case 3:imageView = ((ImageView)findViewById(R.id.imageView4));
			break;
			case 4:imageView = ((ImageView)findViewById(R.id.imageView5)); 
			}
			if(i<=passedLevel%5) {
				
				final int index = i;
				imageView.setImageResource(R.drawable.level_1_1);
				imageView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(LevelActivity.this,
								MrSeigeActivity.class);
						intent.putExtra(SysConstant.LEVEL_REF, passedLevel-passedLevel%5+index);
						Log.d(TAG, "现在是第："+(passedLevel-passedLevel%5+index));
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
		gotomainpage = (ImageView) findViewById(R.id.gotomainpage);
		gotomainpage.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		} );
	}
}
