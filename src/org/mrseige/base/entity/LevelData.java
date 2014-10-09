package org.mrseige.base.entity;

import java.util.ArrayList;

/**
 * 关卡数据
 * @author ljh
 *
 */
public class LevelData implements Cloneable {
	private ArrayList<MonsterAlloc> monsterAllocs;
	private int totalMonsterNumber;
	private int goldAward;
	private double goldAwardOdds;
	private double levelPassGold;
	public LevelData(ArrayList<MonsterAlloc> monsterAllocs,
			int totalMonsterNumber, int goldAward, double goldAwardOdds,
			double levelPassGold) {
		super();
		this.monsterAllocs = monsterAllocs;
		this.totalMonsterNumber = totalMonsterNumber;
		this.goldAward = goldAward;
		this.goldAwardOdds = goldAwardOdds;
		this.levelPassGold = levelPassGold;
	}
	public ArrayList<MonsterAlloc> getMonsterAllocs() {
		return monsterAllocs;
	}
	public void setMonsterAllocs(ArrayList<MonsterAlloc> monsterAllocs) {
		this.monsterAllocs = monsterAllocs;
	}
	public int getTotalMonsterNumber() {
		return totalMonsterNumber;
	}
	public void setTotalMonsterNumber(int totalMonsterNumber) {
		this.totalMonsterNumber = totalMonsterNumber;
	}
	public int getGoldAward() {
		return goldAward;
	}
	public void setGoldAward(int goldAward) {
		this.goldAward = goldAward;
	}
	public double getGoldAwardOdds() {
		return goldAwardOdds;
	}
	public void setGoldAwardOdds(double goldAwardOdds) {
		this.goldAwardOdds = goldAwardOdds;
	}
	public double getLevelPassGold() {
		return levelPassGold;
	}
	public void setLevelPassGold(double levelPassGold) {
		this.levelPassGold = levelPassGold;
	}
	
}