package org.mrseige.common;

public class GameManager {
	
	private static final GameManager  instance= new GameManager();
	
	private GameManager() {
		
	}
	public static GameManager getInstance() {
		return instance;
	}
	
	private  int screenWidth;
	private  int screenHeight;

	public int getScreenWidth() {
		return screenWidth;
	}
	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}
	public int getScreenHeight() {
		return screenHeight;
	}
	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}
	
}
