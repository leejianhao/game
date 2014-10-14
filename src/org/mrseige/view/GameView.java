package org.mrseige.view;

import java.util.ArrayList;
import java.util.List;

import org.mrseige.activity.GamePref;
import org.mrseige.activity.MrSeigeActivity;
import org.mrseige.base.AnimatedSprite;
import org.mrseige.base.Layer;
import org.mrseige.base.entity.MonsterList;
import org.mrseige.common.BitMapManager;
import org.mrseige.common.DensityUtil;
import org.mrseige.common.GameManager;
import org.mrseige.common.Pos;
import org.mrseige.common.SysConstant;
import org.mrseige.game.GameStatus;
import org.mrseige.game.MonsterWizardRule;
import org.mrseige.game.UpdateThread;
import org.mrseige.sprite.Arrow;
import org.mrseige.sprite.Crossbow;
import org.mrseige.sprite.FixTool;
import org.mrseige.sprite.FrozenWeapon;
import org.mrseige.sprite.Monster;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{
	
	private static final String TAG = GameView.class.getSimpleName();
	
	private Context context;
	private int screenWidth;
	private int screenHeight;
	int distanceToScreenBottom;

	public Layer background;
	private Layer obstacle;
	
	private Crossbow bow;
	
	private FrozenWeapon fw;
	private FixTool ft;
	
	private UpdateThread updateThread;
	/**初始僵尸**/
	private List<Monster> monsterList = new ArrayList<Monster>();
	/**爆炸动画**/
	private List<AnimatedSprite> explodeList = new ArrayList<AnimatedSprite>();
	
	private Monster monster;
	/**游戏等级**/
	private int gameLevel;
	
	/**墙体生命值**/
	public int obsLifeCount = SysConstant.ObsLifeCount;
	
	private Handler handler;
	private GameStatus gameStatus;
	
	
	public GameView(Context context) {
		super(context);
		this.context = context;
		getHolder().addCallback(this);
		screenWidth = MrSeigeActivity.DEVICE_WIDTH;
		screenHeight = MrSeigeActivity.DEVICE_HEIGHT;
		this.setKeepScreenOn(true);		//设置屏幕常亮
	//	setFocusableInTouchMode(true); 
	}
	
	//xml调用
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		getHolder().addCallback(this);
		screenWidth = context.getResources().getDisplayMetrics().widthPixels;
		screenHeight = context.getResources().getDisplayMetrics().heightPixels;
		this.setKeepScreenOn(true);		//设置屏幕常亮
		
		GameManager.getInstance().setScreenWidth(screenWidth);
		GameManager.getInstance().setScreenHeight(screenHeight);
		
		distanceToScreenBottom = (int) (screenHeight*0.3);
		
	   	BitMapManager.getInstance().loadResource(context);
	   	
	   //	loadByLevel();
	}
	
	private org.mrseige.base.entity.LevelData loadByLevel() {
		int rows = gameLevel/3;
		int cols = gameLevel%5;
		Log.d(TAG, "rows: "+rows+" cols: "+cols);
		return org.mrseige.base.entity.LevelConfig.getInstance().getLevel()[rows][cols];
	}
	

	public void init(Handler handler, int gameLevel) {
		this.gameLevel = gameLevel;
		this.handler = handler;
	}
	

	public void collisionCheck() {
		for(Monster monster : monsterList) {
			if(obstacle.isCollision((int) (monster.getHeight()-screenHeight*0.03))) {
				monster.setCollisionWithObstacle(true);
				if(!monster.isCollisionWithObstacling()) {
					monster.setCollisionWithObstacleBegintime(System.currentTimeMillis());
					monster.setCollisionWithObstacling(true);
					
					//切换到攻击动画
					if(MonsterWizardRule.enumMap_attackBitmap.get(monster.getBitmap())!=null)
						monster.setBitmap(MonsterWizardRule.enumMap_attackBitmap.get(monster.getBitmap()));
					
				}
				
				//怪物与墙体碰撞超过1s
				long now = System.currentTimeMillis();
				if(System.currentTimeMillis() - monster.getCollisionWithObstacleBegintime() >= 1000) {
					monster.setCollisionWithObstacleBegintime(now);
					obsLifeCount--;
					//墙体变换bitmap
					if(obsLifeCount<=50) obstacle.setBitmap(BitMapManager.getInstance().obstacle[1]); 
					if(obsLifeCount<=40) obstacle.setBitmap(BitMapManager.getInstance().obstacle[2]);
					if(obsLifeCount<=30) obstacle.setBitmap(BitMapManager.getInstance().obstacle[3]);
					if(obsLifeCount<=20) obstacle.setBitmap(BitMapManager.getInstance().obstacle[4]);
					if(obsLifeCount<=10) obstacle.setBitmap(BitMapManager.getInstance().obstacle[5]);
					//产生消息并发送
					if(obsLifeCount<=0) handler.sendMessage(handler.obtainMessage(SysConstant.GAME_OVER));	
				}
			}
		}
		
		for(int i=bow.getArrowList().size()-1;i>=0;i--) {
			Arrow arrow = bow.getArrowList().get(i);
			if(arrow.checkOutOfScreen(screenWidth, screenHeight)) {
				bow.getArrowList().remove(arrow);
			}
		}
		/*for(int i=bow.getArrowList().size()-1;i>=0;i--) {
			Arrow arrow = bow.getArrowList().get(i);
			Rect rect = arrow.getRect();
			for(Monster monster : monsterList) {
				Rect rect2 = new Rect();
				rect2.left=monster.getX();
				rect2.top = monster.getH();
				rect2.right = rect2.left+monster.getWidth();
				rect2.bottom = rect2.top + monster.getHeight();
				if(rect.intersect(rect2)) {
					bow.getArrowList().remove(arrow);
				}
			}
		}*/
		for(int i=bow.getArrowList().size()-1;i>=0;i--) {
			Arrow arrow = bow.getArrowList().get(i);
			
			//弓箭热点
			Pos pos = arrow.getPos();
			float degree = arrow.getDegree();
			double radians = Math.toRadians(degree);
			float posX = (float) (pos.x + DensityUtil.dip2px(context, 50)*Math.sin(radians));
			float posY = (float) (pos.y - DensityUtil.dip2px(context, 50)*Math.cos(radians));
			Pos tmp = new Pos(posX, posY);
			
			for(int j=monsterList.size()-1;j>=0;j--) {
				Monster monster = monsterList.get(j);
				if(monster.isAlive() && monster.isConlision(tmp, DensityUtil.dip2px(context, 36),0)) {
					bow.getArrowList().remove(arrow);
					int life = monster.getMonsterProperty().getLife();
					life--;monster.getMonsterProperty().setLife(life);
					if(life ==0 ) {
						AnimatedSprite explode = new AnimatedSprite(monster.getX(),monster.getY(),monster.getWidth(),monster.getHeight());
						explode.setBitmap(MonsterWizardRule.enumMap_deadBitmap.get(monster.getBitmap()));
						explode.setLoop(false);
						explodeList.add(explode);
						
						monsterList.remove(monster);
					}else {
						//标记怪物被射中
						monster.setAttacked(true);
					}
				}
			}
		}
	}
	
	public void update() {
		monster.update(monsterList);
	}
	
	public void render(Canvas canvas) {
		background.draw(canvas);
		obstacle.draw(canvas);
		bow.drawSelf(canvas);
		bow.drawQpath(canvas);
		bow.drawArrow(canvas);
		bow.loadArrow();
		fw.draw(canvas);
		ft.draw(canvas);
		
		for(int i=0;i<monsterList.size();i++) {
			if(monsterList.get(i).isAlive()) {
				if(monsterList.get(i).isAttacked()) {
					monsterList.get(i).draw2(canvas);
				}
				monsterList.get(i).draw(canvas);
			}
			
		}
		for(int i=0;i<explodeList.size();i++) {
			explodeList.get(i).draw(canvas);
		}
		//check gamenext 动画结束后
		if(monster.getNoneSeeZoombie()==0 && monsterList.size()==0) {
			handler.sendMessage(handler.obtainMessage(SysConstant.GAME_PASS));
		}
	}
	
	public void explode() {
		for(int i=0;i<explodeList.size();i++) {
			AnimatedSprite s = explodeList.get(i);
			if (s.isEnd()) {
				explodeList.remove(s);
				s = null;
			}
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		playGame();
		updateThread = new UpdateThread(GameView.this);
		updateThread.setRunning(true);
		updateThread.start();
		
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d(TAG, "surfaceDestroyed...");
		updateThread.setRunning(false);
		
		// TODO: save running data if press HOME, and enter into pause mode
		
		//清空bitmap，释放内存
		BitMapManager.getInstance().clear();
		
		boolean flag = true;
		while(flag) {
			try{
				//阻塞当前线程，等待子线程结束
				updateThread.join();
				flag = false;
			}catch(InterruptedException e) {
				
			}
		}
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		Log.v(TAG, "surfaceChanged invoked...");
		monsterList.clear();
	}
	
	public void setRestoredState(GameStatus gameStatus) {
       this.gameStatus = gameStatus;
    }
	
	public void getGameStatus(GameStatus gameStatus) {
		if (gameStatus == null) {
            gameStatus = new GameStatus();
        }
		gameStatus.setMonsterList(monsterList);
		gameStatus.setExplodeList(explodeList);
		gameStatus.setBow(bow);
		this.gameStatus = gameStatus;
	}
	/**
    * 开始新游戏
    */
	public void playGame() {
		background = new Layer(0, 0, screenWidth, screenHeight);
		background.setBitmap(BitMapManager.getInstance().background[0]);
		
		obstacle = new Layer(0,(int) (screenHeight-distanceToScreenBottom-screenHeight*0.05), screenWidth,screenHeight);
		obstacle.setBitmap(BitMapManager.getInstance().obstacle[0]);   
		
		Bitmap crossbow = BitMapManager.getInstance().bow[0];
		int cbHeight = crossbow.getHeight();
		int cbWidth =  crossbow.getWidth();
		bow = new Crossbow((screenWidth-cbWidth)/2, screenHeight-distanceToScreenBottom, (screenWidth-cbWidth)/2+cbWidth, screenHeight-distanceToScreenBottom+cbHeight, context);
		bow.setBitmap(crossbow);
		
		Bitmap frozenWeapon = BitMapManager.getInstance().frozenWeapon[0];
		int fwHeight = frozenWeapon.getHeight();
		int fwWidth =  frozenWeapon.getWidth();
		fw = new FrozenWeapon((screenWidth-SysConstant.FROZEN_WEAPON_MARGIN_BOTTOM_RIGHT-fwWidth), (screenHeight-SysConstant.FROZEN_WEAPON_MARGIN_BOTTOM_RIGHT-fwHeight), (screenWidth-SysConstant.FROZEN_WEAPON_MARGIN_BOTTOM_RIGHT), (screenHeight-SysConstant.FROZEN_WEAPON_MARGIN_BOTTOM_RIGHT));
		fw.setBitmap(frozenWeapon);
		
		Bitmap fixTool = BitMapManager.getInstance().fixTool[0];
		int ftHeight = fixTool.getHeight();
		int ftWidth =  fixTool.getWidth();
		ft = new FixTool((SysConstant.FROZEN_WEAPON_MARGIN_BOTTOM_RIGHT), (screenHeight-SysConstant.FROZEN_WEAPON_MARGIN_BOTTOM_RIGHT-ftHeight), (SysConstant.FROZEN_WEAPON_MARGIN_BOTTOM_RIGHT+ftWidth), (screenHeight-SysConstant.FROZEN_WEAPON_MARGIN_BOTTOM_RIGHT));
		ft.setBitmap(fixTool);
		
		
		GameManager.getInstance().setScreenWidth(screenWidth);
		GameManager.getInstance().setScreenHeight(screenHeight);
		
		monster = new Monster(context, loadByLevel());
   }
	   
	/**暂停游戏**/
	public void pauseGame() {
		updateThread.stopUpdate();
    }
	
	/**结束游戏**/
	public void stopGame() {
		updateThread.stopUpdate();
    }
	
	/**重置游戏**/
	public void resetGame() {
		monsterList.clear();
		bow.getArrowList().clear();
		
		playGame();
		
		obsLifeCount = SysConstant.ObsLifeCount;
		obstacle.setBitmap(BitMapManager.getInstance().obstacle[0]);
		if(updateThread != null) {
			updateThread.setRunning(true);
			updateThread.startUpdate();
		}else {
			updateThread = new UpdateThread(GameView.this);
			updateThread.setRunning(true);
			updateThread.start();
		}
	}
	
   /**
    * 继续游戏
    */
   public void resumeGame() {
      updateThread.startUpdate();
   }
   
   public void toNextLevel() {
	   int passedLevel = GamePref.getInstance(context).getLevelPref();
	   //开始新关卡
	   if(gameLevel+1>passedLevel) {
		   GamePref.getInstance(context).setLevelPref(gameLevel+1);
	   }
	   
	   //关卡推进--关卡循环
	   gameLevel = ++gameLevel % MonsterList.monsterList.size();
   }
   PointF startPoint = new PointF(); 
   boolean touchOccupied = false;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		if(event.getAction()== MotionEvent.ACTION_DOWN) {
			startPoint.x = event.getX();
			startPoint.y = event.getY();
			touchOccupied = false;
		}
		/*if(fw.touch(event) && fw.onTouch(event)) {
			touchOccupied = true;
			return true;
		}*/
		
		if(bow.isContains(startPoint.x, startPoint.y) && !touchOccupied && bow.onTouch(event)) {	//弹起的时候手指不在bitmap上，所以出现了箭未射出的情况
			return true;
		}
		if(ft.isContains(startPoint.x, startPoint.y) && ft.onTouch(event)) {
			//测试
			this.obsLifeCount = SysConstant.ObsLifeCount;
			this.obstacle.setBitmap(BitMapManager.getInstance().obstacle[0]);
			return true;
		}
		
		return false;
	}
	
}
