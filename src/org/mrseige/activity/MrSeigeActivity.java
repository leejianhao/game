package org.mrseige.activity;

import org.mrseige.common.BitMapManager;
import org.mrseige.common.SysConstant;
import org.mrseige.game.GameStatus;
import org.mrseige.slider.MultiDirectionSlidingDrawer;
import org.mrseige.view.GameView;

import android.content.SharedPreferences;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 末日围城主界面
 * @author ljh
 * @date 2014-5-18
 */
public class MrSeigeActivity extends BaseActivity{
	
	private static final String TAG = MrSeigeActivity.class.getSimpleName();
	private static final String PREF = "mrseige pref";
	private static final String GAME_STATE_PREF = "mrseige state pref";
	 
	public static int DEVICE_WIDTH;
	public static int DEVICE_HEIGHT;
	
	private GameView gameView;
	
	private ImageView gamePause;
	private TextView gameInfoText;
	private LinearLayout gameInfo;
	private Button gameInfoPlayBtn;
	private Button gameInfoRestartBtn;
	private Button gameInfoNextLvBtn;
	
	private GameStatus gameStatus;
	
	private int level;
	
	//slider
	Button mCloseButton;
	Button mOpenButton;
	MultiDirectionSlidingDrawer mDrawer;
	
	private Handler handler = new Handler() {
		
		public void handleMessage(Message msg) {
			switch(msg.what) {
			case SysConstant.GAME_OVER:
				stopGame(SysConstant.GAME_OVER);
				break;
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//设置全屏
        this.setFullScreen();
        //设置屏幕常亮
        this.setScreenLit();
        
		//获取设备屏幕宽高
		DisplayMetrics dm = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(dm);
		DEVICE_WIDTH = dm.widthPixels;
		DEVICE_HEIGHT = dm.heightPixels;
		setContentView(R.layout.game_view);
		findViews();
		setupListeners();
	}

	private void init() {
	/*	mScore = GamePref.getInstance(this).getScorePref();
	      initScoreBoard();*/
		gameStatus = new GameStatus();
	}
	
	/**
	 * 开始游戏
	 */
	private void playGame() {
      Log.d(TAG, "playGame...");
      gameStatus.setStatus(GameStatus.GAME_RUNNING);
      showGamePause();
      hideGameInfo();
   }
   
   /**重新游戏**/
   private void resetGame() {
      Log.d(TAG, "reset game...");
      gameView.resetGame();
      playGame();
   }
	
   @Override
   protected void onRestoreInstanceState(Bundle outState) {
      Log.d(TAG, "onRestoreInstanceState");
      SharedPreferences sp = this.getSharedPreferences(PREF, MODE_PRIVATE);
      int status = sp.getInt(GAME_STATE_PREF, GameStatus.GAME_INIT);
      if (status == GameStatus.GAME_PAUSED) {
         restorePausedGame();
      }

   }
   
	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume invoked...");
		init();
		
		level = this.getIntent().getIntExtra(SysConstant.LEVEL_REF, 0);
		gameView.init(handler, level);
		
		/*SharedPreferences sp = this.getSharedPreferences(PREF, MODE_PRIVATE);
	    int gameStatus = sp.getInt(GAME_STATE_PREF, GameStatus.GAME_INIT);
	    if(gameStatus == GameStatus.GAME_INIT) {
	    	playGame();
	    } else if(gameStatus == GameStatus.GAME_STOPPED) {
	    	playGame();
	    } else if(gameStatus == GameStatus.GAME_PAUSED) {
	    	restorePausedGame();
	    }else {
	    	 // TODO stop
	    }*/
		
	}

	@Override
	protected void onPause() {
		Log.d(TAG, "onPause invoked...");
		
		if(this.isFinishing() || gameStatus.getStatus() ==GameStatus.GAME_STOPPED) {
			Log.d(TAG, "this isfinishing! or game stopped");
			resetState();
			finish();
		}else {
			Log.d(TAG, "not this isfinishing!");
		//	pauseGame();
			saveState();
		}
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	
	private void findViews() {
		gamePause = (ImageView) findViewById(R.id.game_pause);
		gameView = (GameView) findViewById(R.id.game_view);
		
		gameInfoText = (TextView) findViewById(R.id.game_view_info_text);
		gameInfo = (LinearLayout) findViewById(R.id.game_view_info);
		gameInfoPlayBtn = (Button) findViewById(R.id.game_view_play_btn);
		gameInfoRestartBtn = (Button) findViewById(R.id.game_view_restart_btn);
	    gameInfoNextLvBtn =  (Button) findViewById(R.id.game_view_next_level);
	    
	    //slider
	    mCloseButton = (Button) findViewById( R.id.button_close );
	   	mOpenButton = (Button) findViewById( R.id.button_open );
	   	mDrawer = (MultiDirectionSlidingDrawer) findViewById( R.id.drawer );
	}
	
	private void setupListeners() {
		if (gamePause != null) {
	         gamePause.setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	               pauseGame();
	               //游戏暂停时，手气drawer
	               mDrawer.animateClose();
	            }
	         });
	      }
		if (gameInfoPlayBtn != null) {
	         gameInfoPlayBtn.setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	               if (gameStatus.getStatus() == GameStatus.GAME_PAUSED) {
	                  resumeGame();
	               } 
	            }
	         });
	      }
		if (gameInfoRestartBtn != null) {
	         gameInfoRestartBtn.setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	               resetGame();
	            }
	         });
	      }
		//slider
		if(mCloseButton != null) {
			mCloseButton.setOnClickListener( new OnClickListener() {
				@Override
				public void onClick( View v )
				{
					mDrawer.animateClose();
				}
			});
	        
		}
		
		if(mOpenButton != null) {
			
			mOpenButton.setOnClickListener( new OnClickListener() {
				
				@Override
				public void onClick( View v )
				{
					if( !mDrawer.isOpened() )
						mDrawer.animateOpen();
				}
			});
		}
	}
	
	private void resumeGame() {
      gameStatus.setStatus(GameStatus.GAME_RUNNING);
      gameView.resumeGame();
      showGamePause();
      hideGameInfo();
   }
	
	/**
    * if pause btn is clicked or activity runs onPause(),u should call this func
    */
   private void pauseGame() {
	  Log.i(TAG, "暂停游戏");
      gameView.pauseGame();
      gameStatus.setStatus(GameStatus.GAME_PAUSED);
      hideGamePause();
      showGameInfo(SysConstant.GAME_PAUSE, R.string.paused);
   }
   
   private void stopGame(int why) {
	   gameView.stopGame();
	   gameStatus.setStatus(GameStatus.GAME_STOPPED);
	   hideGamePause();
	   int info = R.string.over;
	   showGameInfo(why, info);
   }
   
   private void hideGamePause() {
	   gamePause.setVisibility(View.INVISIBLE);
   }
   
   private void hideGameInfo() {
      gameInfo.setVisibility(View.GONE);
   }
   
   private void showGamePause() {
      gamePause.setVisibility(View.VISIBLE);
   }
   
   private void showGameInfo(int why, int infoId) {
	   String info = this.getResources().getString(infoId);
	   gameInfoText.setText(info);
	   gameInfo.setVisibility(View.VISIBLE);
	   switch(why) {
	   case SysConstant.GAME_PAUSE:
		   gameInfoPlayBtn.setVisibility(View.VISIBLE);
	       gameInfoRestartBtn.setVisibility(View.GONE);
	       gameInfoNextLvBtn.setVisibility(View.GONE);
	       break;
	   case SysConstant.GAME_OVER:
		   gameInfoPlayBtn.setVisibility(View.GONE);
	       gameInfoRestartBtn.setVisibility(View.VISIBLE);
	       gameInfoNextLvBtn.setVisibility(View.GONE);
	   }
   }
   /*@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return gameView.onTouchEvent(event);
	}*/
   
   /**
    * 保留退出时状态
    */
   private void saveState() {
	   Log.d(TAG, "saveState...");
	   
	   /*gameView.getGameStatus(gameStatus);
	   
	   SharedPreferences sp = this.getSharedPreferences(PREF, MODE_PRIVATE);
	   sp.edit().putInt(GAME_STATE_PREF, GameStatus.GAME_PAUSED).commit();*/
   }
   
   private void restorePausedGame() {
	   gameStatus.setStatus(GameStatus.GAME_PAUSED);
	   
	   SharedPreferences sp = this.getSharedPreferences(PREF, MODE_PRIVATE);
   }
   
   private void resetState() {
	   SharedPreferences sp = this.getSharedPreferences(PREF, MODE_PRIVATE);
	   sp.edit().putInt(GAME_STATE_PREF, GameStatus.GAME_INIT).commit();
   }
   
   /*private final class TouchListener implements OnTouchListener{
	   
	   private PointF startPoint = new PointF();  
	   private Matrix matrix = new Matrix();  
	   private Matrix currentMatrix = new Matrix();
	   
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch(event.getAction() & MotionEvent.ACTION_MASK) {
				case MotionEvent.ACTION_DOWN://手指按下屏幕
					currentMatrix.set(frozenView.getImageMatrix());//记录ImageView当前移动位置  
					startPoint.set(event.getX(), event.getY());
					break;
				case MotionEvent.ACTION_MOVE://手指按下屏幕
					float dx = event.getX() -startPoint.x;//得到在x轴的移动距离  
					float dy = event.getY() - startPoint.y;//得到在y轴的移动距离  
					  
					matrix.set(currentMatrix);//在没有进行移动之前的位置基础上进行移动  
					matrix.postTranslate(dx, dy);  
					break;
				case MotionEvent.ACTION_UP://手指离开屏幕  
					break;  
			}
			frozenView.setImageMatrix(matrix);
			return true;
	   }
   }*/
}
