package org.mrseige.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 关卡
 * @author ljh
 *
 */
public class LevelWizard {
	private static ArrayList<LevelConfig> levelConfigList = new ArrayList<LevelConfig>();
	private static ArrayList<LevelConfig> levelConfigList_backup = new ArrayList<LevelConfig>();
	private static LevelWizard levelWizard = new LevelWizard();
	
	public LevelWizard() {
		@SuppressWarnings("serial")
		ArrayList<MonsterAllocate> monsterConfig_1 = new ArrayList<MonsterAllocate>() {
			{
				add(new MonsterAllocate(MonsterWizard.A, 17));
				add(new MonsterAllocate(MonsterWizard.B, 3));
				add(new MonsterAllocate(MonsterWizard.E, 1));
			}
		};
		
		@SuppressWarnings("serial")
		ArrayList<MonsterAllocate> monsterConfig_2 = new ArrayList<MonsterAllocate>() {
			{
				add(new MonsterAllocate(MonsterWizard.A, 24));
				add(new MonsterAllocate(MonsterWizard.B, 5));
				add(new MonsterAllocate(MonsterWizard.D, 1));
				add(new MonsterAllocate(MonsterWizard.E, 1));
			}
		};
		
		@SuppressWarnings("serial")
		ArrayList<MonsterAllocate> monsterConfig_3 = new ArrayList<MonsterAllocate>() {
			{
				add(new MonsterAllocate(MonsterWizard.A, 10));
				add(new MonsterAllocate(MonsterWizard.B, 15));
				add(new MonsterAllocate(MonsterWizard.C, 2));
				add(new MonsterAllocate(MonsterWizard.E, 2));
				add(new MonsterAllocate(MonsterWizard.F, 1));
			}
		};
		
		/*@SuppressWarnings("serial")
		ArrayList<MonsterAllocate> monsterConfig_4 = new ArrayList<MonsterAllocate>() {
			{
				add(new MonsterAllocate(MonsterWizard.A, 10));
				add(new MonsterAllocate(MonsterWizard.B, 15));
				add(new MonsterAllocate(MonsterWizard.C, 2));
				add(new MonsterAllocate(MonsterWizard.E, 2));
				add(new MonsterAllocate(MonsterWizard.F, 1));
			}
		};*/
		
		levelConfigList.clear();
		levelConfigList.add(new LevelConfig(monsterConfig_1, 21, 10, 0.25, 50));
		levelConfigList.add(new LevelConfig(monsterConfig_2, 31, 5, 0.25, 50));
		levelConfigList.add(new LevelConfig(monsterConfig_3, 30, 5, 0.25, 75));
		
		//backup
		levelConfigList_backup.addAll(levelConfigList);
	}
	
	
	public class LevelConfig implements Cloneable {
		
		private ArrayList<MonsterAllocate> monsterAllocate;
		private int totalMonsterNumber;
		private int goldAward;
		private double goldAwardOdds;
		private double levelPassGold;
		
		public LevelConfig(ArrayList<MonsterAllocate> monsterAllocate,
							int totalMonsterNumber,
							int goldAward,
							double goldAwardOdds,
							int levelPassGold) {
			this.monsterAllocate = monsterAllocate;
			this.totalMonsterNumber = totalMonsterNumber;
			this.goldAward = goldAward;
			this.goldAwardOdds = goldAwardOdds;
			this.levelPassGold = levelPassGold;
		}

		public ArrayList<MonsterAllocate> getMonsterAllocate() {
			if(!monsterAllocate.isEmpty()) {
				Collections.sort(monsterAllocate, new Comparator<MonsterAllocate>() {
					@Override
					public int compare(MonsterAllocate lhs, MonsterAllocate rhs) {
						
						return lhs.getMonsterWizard().getPriority() < rhs.getMonsterWizard().getPriority() ? -1 : 1;
					}
				});
			}
			return monsterAllocate;
		}

		public void setMonsterAllocate(ArrayList<MonsterAllocate> monsterAllocate) {
			this.monsterAllocate = monsterAllocate;
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
	
	public LevelConfig getLevelConfig(int gameLevel) {
		return levelConfigList.get(gameLevel);
	}
	
	public static LevelWizard getInstance() {
		return levelWizard;
	}
	
	//测试
	public static int getCurrentSize() {
		return levelConfigList.size();
	}
}
