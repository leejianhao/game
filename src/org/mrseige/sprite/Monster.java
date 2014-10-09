package org.mrseige.sprite;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

import org.mrseige.base.AnimatedSprite;
import org.mrseige.base.entity.LevelData;
import org.mrseige.base.entity.MonsterAlloc;
import org.mrseige.base.entity.MonsterProperty;
import org.mrseige.common.DensityUtil;
import org.mrseige.common.GameManager;
import org.mrseige.common.SysConstant;
import org.mrseige.game.MonsterWizardRule;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;


/**
 * 僵尸类，处理僵尸逻辑
 * @author ljh
 * @date 2014-4-23 15:08:49
 */
public class Monster extends AnimatedSprite implements Serializable{
	
	private static final String TAG = Monster.class.getSimpleName();
	
	Context context;
	
	private long startTime = 0;
	
	LevelData levelData;
	
	private static final int STEP_Y = 1;
	
	private MonsterProperty monsterProperty;
	
	private boolean alive = true;
	
	private boolean collisionWithObstacling = false;
	
	
	public boolean isCollisionWithObstacling() {
		return collisionWithObstacling;
	}
	public void setCollisionWithObstacling(boolean collisionWithObstacling) {
		this.collisionWithObstacling = collisionWithObstacling;
	}

	private boolean collisionWithObstacle = false;
	private long collisionWithObstacleBegintime;
	
	public long getCollisionWithObstacleBegintime() {
		return collisionWithObstacleBegintime;
	}
	public void setCollisionWithObstacleBegintime(
			long collisionWithObstacleBegintime) {
		this.collisionWithObstacleBegintime = collisionWithObstacleBegintime;
	}

	private int totalMonsterNumber ;
	private int existMonsterNumber;
	private int noneSeeZoombie = -1;
	
	public int getNoneSeeZoombie() {
		return noneSeeZoombie;
	}
	
	public boolean isCollisionWithObstacle() {
		return collisionWithObstacle;
	}
	public void setCollisionWithObstacle(boolean collisionWithObstacle) {
		this.collisionWithObstacle = collisionWithObstacle;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public MonsterProperty getMonsterProperty() {
		return monsterProperty;
	}
	public void setMonsterProperty(MonsterProperty monsterProperty) {
		this.monsterProperty = monsterProperty;
	}
	
	public Monster() {
		
	}
	public Monster(Context context, LevelData levelData) {
		this.context = context;
		this.levelData = levelData;
		totalMonsterNumber = levelData.getTotalMonsterNumber();
	}
	
	public Monster(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	Random rm = new Random();
	/**
	 * 更新僵尸群
	 * @param zoombieList
	 */
	public void update(List<Monster> monsterList) {
		int y = 0;
		
		int screenWidth = GameManager.getInstance().getScreenWidth();
		 
		for(Monster monster : monsterList) {
			if(!monster.isCollisionWithObstacle()) {
				y = monster.getY() + monster.getMonsterProperty().getSpeed()*STEP_Y;
				Log.v(TAG, "y="+y);
				monster.setY(y);
				monster.setHeight(y+DensityUtil.dip2px(context, monster.getMonsterProperty().getHeight()));
			}
		}
		noneSeeZoombie = totalMonsterNumber - existMonsterNumber;
		
		long endTime = System.currentTimeMillis();
		if((noneSeeZoombie>0) && ((endTime-startTime) >= (noneSeeZoombie*200*3+300))) {
			List<MonsterAlloc> ma = levelData.getMonsterAllocs();
			Bitmap[] bitmaps = null;
			MonsterProperty monsterProperty = null;
			MonsterAlloc firstMonster = ma.get(0);
			//优先级低的先出现，如果该关卡中优先级最低的对象数量多于5，则依次出现
			//随后则随机出现
			if(ma.size()> 0 && monsterList.size() < 5 && firstMonster.getCount() > 0) {
				bitmaps = MonsterWizardRule.enumMap_bitmap.get(firstMonster.getMonster().getMonsterType());
				try {
					monsterProperty = (MonsterProperty) MonsterWizardRule.enumMap_monsterproperty.get(firstMonster.getMonster().getMonsterType()).clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
				firstMonster.setCount(firstMonster.getCount()-1);
			}else {
				int index = rm.nextInt(noneSeeZoombie);	
				Log.v(TAG, "index="+index);
				for(int i=0;i<ma.size();i++) {
					if(ma.get(i).getCount() > index) {
						bitmaps = MonsterWizardRule.enumMap_bitmap.get(ma.get(i).getMonster().getMonsterType());
						try {
							monsterProperty = (MonsterProperty) MonsterWizardRule.enumMap_monsterproperty.get(ma.get(i).getMonster().getMonsterType()).clone();
						} catch (CloneNotSupportedException e) {
							e.printStackTrace();
						}
						if(monsterProperty == null) {
							
							System.out.println(monsterProperty);
						}
						ma.get(i).setCount(ma.get(i).getCount() - 1);
						break;
					}else {
						index = index-ma.get(i).getCount();
					}
				}
			}
			startTime = endTime;
			addMonster(monsterList, screenWidth, bitmaps, monsterProperty);
		}
	}
	
	/**
	 * 增加怪物
	 * @param monsterList
	 * @param screenWidth
	 * @param monsterWidth
	 * @param monsterHeight
	 * @param bitmaps
	 */
	private void addMonster(List<Monster> monsterList, int screenWidth,
			Bitmap[] bitmaps, MonsterProperty monsterProperty) {
		int monsterWidth = (int) (bitmaps[0].getWidth()*(1+monsterProperty.getBulk()*SysConstant.ZOOM_PERCENT));
		int monsterHeight = (int) (bitmaps[0].getHeight()*(1+monsterProperty.getBulk()*SysConstant.ZOOM_PERCENT));
		int x =(int) (Math.random()*screenWidth);
		int size = monsterList.size();
		Random random = new Random();
		if(monsterList.size() >1) {
			if(Math.abs(x-monsterList.get(size-1).getX())<monsterWidth) {
				if(random.nextBoolean()) {
					x = monsterList.get(size-1).getX() - monsterWidth;
					if(x<0) {
						x = monsterList.get(size-1).getX() + monsterWidth;
					}
				}else {
					x = monsterList.get(size-1).getX() + monsterWidth;
					
					//简单判断，如果超出屏幕右侧，则向左移动两个怪物的距离
					//修正怪物部分在屏幕外的BUG 2014-7-9 10:47:55
					if(x + monsterWidth > screenWidth) {
						x = monsterList.get(size-1).getX()-2*monsterWidth;	
					}
				}
			}else {
				//判断是否超出屏幕右侧
				if(x + monsterWidth > screenWidth) {
					x = x-2*monsterWidth;
				}
			}
		}else {
			//判断是否超出屏幕右侧
			if(x + monsterWidth > screenWidth) {
				x = x-2*monsterWidth;
			}
		}
		Monster monster = new Monster(x, -monsterHeight, x+monsterWidth, 0);
		monster.setBitmap(bitmaps);
		monster.setLoop(true);
		monster.setMonsterProperty(monsterProperty);
		monsterList.add(monster);
		existMonsterNumber++;
	}
	
}
