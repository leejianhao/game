package org.mrseige.activity;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 用户参数设置
 * @author ljh
 *
 */
public class GamePref {
	
	private static GamePref instance;
	
	private Context context;
	private static final String PREF="game pref";
	private static final String GAME_LEVEL_PREF="game level";
	
	
	private GamePref(Context context) {
	   this.context = context;
	}
	
	public static GamePref getInstance(Context context) {
      if (instance == null) {
         instance = new GamePref(context);
      }
      return instance;
   }
	public boolean setLevelPref(int level) {
      return setIntPref(GAME_LEVEL_PREF, level);
   }
	
	public boolean setIntPref(String pref, int data) {
      if (context != null) {
         SharedPreferences sp = context.getSharedPreferences(PREF,
               Context.MODE_PRIVATE);
         return sp.edit().putInt(pref, data).commit();
      }
      return false;
      
   }
	
	 public int getLevelPref() {
      int level = 0;
      if (context != null) {
         SharedPreferences sp = context.getSharedPreferences(PREF,
               Context.MODE_PRIVATE);
         level = sp.getInt(GAME_LEVEL_PREF, 0);
      }
      return level;
   }
}
