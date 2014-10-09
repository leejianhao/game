package org.mrseige.base.entity;

public class MonsterAlloc {
	private MonsterProperty monster;
	private int count;
	public MonsterAlloc(MonsterProperty monster, int count) {
		super();
		this.monster = monster;
		this.count = count;
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
	
	
}
