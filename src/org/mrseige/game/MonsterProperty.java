package org.mrseige.game;

public class MonsterProperty implements Cloneable {
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
	public MonsterProperty(String type, String figure, int width, int height,
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getFigure() {
		return figure;
	}

	public void setFigure(String figure) {
		this.figure = figure;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getBulk() {
		return bulk;
	}

	public void setBulk(int bulk) {
		this.bulk = bulk;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
}