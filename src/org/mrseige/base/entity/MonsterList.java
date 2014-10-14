package org.mrseige.base.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 游戏怪物列表
 * @author ljh
 *
 */
public class MonsterList {
	@SuppressWarnings("serial")
	public static Map<MonsterEnum,MonsterProperty> monsterList = new HashMap<MonsterEnum,MonsterProperty>(){
		{
			this.put(MonsterEnum.A,new MonsterProperty(MonsterEnum.A,"基本怪","行走的僵尸",55,72,2,1,1,1,0));
			this.put(MonsterEnum.B,new MonsterProperty(MonsterEnum.B,"速度怪","爬行的僵尸",72,72,1,1,2,1,1));
			this.put(MonsterEnum.C,new MonsterProperty(MonsterEnum.C,"生命怪","组合小型尸群",72,72,3,1,1,2,2));
			this.put(MonsterEnum.D,new MonsterProperty(MonsterEnum.D,"自爆怪","肥胖僵尸",72,100,5,5,1,3,3));
			this.put(MonsterEnum.E,new MonsterProperty(MonsterEnum.E,"全加强怪","持剑盾强壮僵尸",65,72,8,1,2,2,4));
			this.put(MonsterEnum.F,new MonsterProperty(MonsterEnum.F,"远程怪","装备链球大型僵尸",72,72,10,1,0,4,5));
			this.put(MonsterEnum.AA,new MonsterProperty(MonsterEnum.AA,"基本怪","",72,72,6,1,1,1,6));
			this.put(MonsterEnum.BB,new MonsterProperty(MonsterEnum.BB,"速度怪","",72,72,3,1,2,1,7));
			this.put(MonsterEnum.CC,new MonsterProperty(MonsterEnum.CC,"生命怪","",72,72,9,1,1,2,8));
			this.put(MonsterEnum.DD,new MonsterProperty(MonsterEnum.DD,"自爆怪","",72,72,10,5,1,3,9));
			this.put(MonsterEnum.EE,new MonsterProperty(MonsterEnum.EE,"全加强怪","",72,72,15,1,2,2,10));
			this.put(MonsterEnum.FF,new MonsterProperty(MonsterEnum.FF,"远程怪","",72,72,20,1,0,4,11));
			this.put(MonsterEnum.AAA,new MonsterProperty(MonsterEnum.AAA,"基本怪","",72,72,16,1,1,1,12));
			this.put(MonsterEnum.BBB,new MonsterProperty(MonsterEnum.BBB,"速度怪","",72,72,8,1,2,1,13));
			this.put(MonsterEnum.CCC,new MonsterProperty(MonsterEnum.CCC,"生命怪","",72,72,20,1,2,2,14));
			this.put(MonsterEnum.DDD,new MonsterProperty(MonsterEnum.DDD,"自爆怪","",72,72,45,5,1,3,15));
			this.put(MonsterEnum.EEE,new MonsterProperty(MonsterEnum.EEE,"全加强怪","",72,72,175,1,2,2,16));
			this.put(MonsterEnum.FFF,new MonsterProperty(MonsterEnum.FFF,"远程怪","",72,72,125,1,0,4,17));
	  }
	};
}
