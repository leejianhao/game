package org.mrseige.game;

public class MonsterAllocate {
	private MonsterWizard monsterWizard;
	private int count;
	MonsterAllocate(MonsterWizard monsterWizard, int count) {
		this.monsterWizard = monsterWizard;
		this.count = count;
	}
	public MonsterWizard getMonsterWizard() {
		return monsterWizard;
	}
	public void setMonsterWizard(MonsterWizard monsterWizard) {
		this.monsterWizard = monsterWizard;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
