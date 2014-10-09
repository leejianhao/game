package org.mrseige.base.entity;


public class LevelConfig {
	
	private static final LevelConfig instance = new LevelConfig();
	
	
	//三大关五小关
	private LevelData[][] level = new LevelData[3][5];
	
	private LevelConfig() {
		int index = 0;	
		int size = LevelDataConfig.levelDataList.size();
		for(int i=0;i<3;i++) {
			for(int j=0;i<5;j++) {
				if(size>j) {
					level[i][j] = LevelDataConfig.levelDataList.get(index++);
				}else {
					return;
				}
			}
			size = size-5;
			if(size<=0) return;
		}
	}
	
	public static LevelConfig getInstance() {
		return instance;
	}

	public LevelData[][] getLevel() {
		return level;
	}

	public void setLevel(LevelData[][] level) {
		this.level = level;
	}


}
