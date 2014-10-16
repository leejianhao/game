package org.mrseige.base.entity;

public class MonsterAlloc {
	private MonsterProperty monster;
	private int count;
	private int count2;
	public MonsterAlloc(MonsterProperty monster, int count, int count2) {
		super();
		this.monster = monster;
		this.count = count;
		this.count2 = count2;
	}
	public MonsterProperty getMonster() {
		return monster;
	}
	public void setMonster(MonsterProperty monster) {
		this.monster = monster;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getCount2() {
		return count2;
	}
	public void setCount2(int count2) {
		this.count2 = count2;
	}
	
	
}
