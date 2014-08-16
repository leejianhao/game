package org.mrseige.game;
/**
 * 僵死类别
 * @author ljh
 *
 */
public enum MonsterWizard {
	A("基本怪","行走的僵尸",55,72,2,1,1,1,0),
	B("速度怪","爬行的僵尸",72,72,1,1,2,1,1),
	C("生命怪","组合小型尸群",72,72,3,1,1,2,2),
	D("自爆怪","肥胖僵尸",72,100,5,5,1,3,3),
	E("全加强怪","持剑盾强壮僵尸",72,72,8,1,2,2,4),
	F("远程怪","装备链球大型僵尸",72,72,10,1,0,4,5),
	AA("基本怪","",72,72,6,1,1,1,6),
	BB("速度怪","",72,72,3,1,2,1,7),
	CC("生命怪","",72,72,9,1,1,2,8),
	DD("自爆怪","",72,72,10,5,1,3,9),
	EE("全加强怪","",72,72,15,1,2,2,10),
	FF("远程怪","",72,72,20,1,0,4,11),
	AAA("基本怪","",72,72,16,1,1,1,12),
	BBB("速度怪","",72,72,8,1,2,1,13),
	CCC("生命怪","",72,72,20,1,2,2,14),
	DDD("自爆怪","",72,72,45,5,1,3,15),
	EEE("全加强怪","",72,72,175,1,2,2,16),
	FFF("远程怪","",72,72,125,1,0,4,17);
	
	/**编号**/
	private String code;
	/**类型**/
	private String type;
	
	/**宽度**/
	private int width;
	
	/**高度**/
	private int height;
	
	/**基本形象**/
	private String figure;
	/**生命**/
	private int life;
	/**攻击力**/
	private int attack;
	/**移动速度**/
	private int speed;
	/**体积**/
	private int bulk;
	
	private int priority;
	
	private MonsterWizard(String type, String figure, int width, int height,
			int life,int attack, int speed, int bulk, int priority) {
		this.type = type;
		this.figure = figure;
		this.width = width;
		this.height = height;
		this.life = life;
		this.attack = attack;
		this.speed = speed;
		this.bulk = bulk;
		this.priority = priority;
	}

	public String getCode() {
		return code;
	}

	public String getType() {
		return type;
	}	

	public String getFigure() {
		return figure;
	}

	public int getLife() {
		return life;
	}

	public int getAttack() {
		return attack;
	}

	public int getSpeed() {
		return speed;
	}

	public int getBulk() {
		return bulk;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	
}
