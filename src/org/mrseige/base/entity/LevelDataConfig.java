package org.mrseige.base.entity;

import java.util.ArrayList;
import java.util.List;

public class LevelDataConfig {
	
	public static List<LevelData> levelDataList = new ArrayList<LevelData>();
	static{
		LevelData ld_0_1 = new LevelData(
				new ArrayList<MonsterAlloc>() {
					{
						this.add(new MonsterAlloc(MonsterList.monsterList.get(MonsterEnum.A) , 17));
						this.add(new MonsterAlloc(MonsterList.monsterList.get(MonsterEnum.B) , 3));
						this.add(new MonsterAlloc(MonsterList.monsterList.get(MonsterEnum.E) , 1));
					}
				},21, 10, 0.25, 50);
		
		LevelData ld_0_2 = new LevelData(
				new ArrayList<MonsterAlloc>() {
					{
						this.add(new MonsterAlloc(MonsterList.monsterList.get(MonsterEnum.A) , 24));
						this.add(new MonsterAlloc(MonsterList.monsterList.get(MonsterEnum.B) , 5));
						this.add(new MonsterAlloc(MonsterList.monsterList.get(MonsterEnum.D) , 1));
						this.add(new MonsterAlloc(MonsterList.monsterList.get(MonsterEnum.E) , 1));
					}
				},31, 5, 0.25, 50);	
		LevelData ld_0_3 = new LevelData(
				new ArrayList<MonsterAlloc>() {
					{
						this.add(new MonsterAlloc(MonsterList.monsterList.get(MonsterEnum.A) , 10));
						this.add(new MonsterAlloc(MonsterList.monsterList.get(MonsterEnum.B) , 15));
						this.add(new MonsterAlloc(MonsterList.monsterList.get(MonsterEnum.C) , 2));
						this.add(new MonsterAlloc(MonsterList.monsterList.get(MonsterEnum.E) , 2));
						this.add(new MonsterAlloc(MonsterList.monsterList.get(MonsterEnum.F) , 1));
					}
				},30, 5, 0.25, 75);	
		levelDataList.add(ld_0_1);	
		levelDataList.add(ld_0_2);
		levelDataList.add(ld_0_3);
	}
	 
	
}
