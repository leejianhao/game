package org.mrseige.game;

import java.util.ArrayList;
import java.util.List;

import org.mrseige.base.AnimatedSprite;
import org.mrseige.sprite.Crossbow;
import org.mrseige.sprite.Monster;

public class GameStatus {
	private int status;
	public static final int GAME_INIT = 0;
	public static final int GAME_RUNNING = 1;
	public static final int GAME_PAUSED = 2;
	public static final int GAME_STOPPED = 3;
	
	/**初始僵尸**/
	private List<Monster> monsterList = new ArrayList<Monster>();
	/**爆炸动画**/
	private List<AnimatedSprite> explodeList = new ArrayList<AnimatedSprite>();
	/**弓弩**/
	private Crossbow bow;
	
	public GameStatus() {
		status = GAME_INIT;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<Monster> getMonsterList() {
		return monsterList;
	}
	public void setMonsterList(List<Monster> monsterList) {
		this.monsterList = monsterList;
	}
	public List<AnimatedSprite> getExplodeList() {
		return explodeList;
	}
	public void setExplodeList(List<AnimatedSprite> explodeList) {
		this.explodeList = explodeList;
	}
	public Crossbow getBow() {
		return bow;
	}
	public void setBow(Crossbow bow) {
		this.bow = bow;
	}
	
}
