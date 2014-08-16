package org.mrseige.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 得分数据库业务类
 * @author ljh
 * @date 2014-4-27 
 */
public class ScoreListDatabaseHelper {
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "mrseige.db";
	private static final String TABLE_NAM = "score";
	
	private static final String SCORE_COLUMN_ID ="_id";
	private static final String SCORE_COLUMN_NAME ="name";
	private static final String SCORE_COLUMN_SCORE ="score";
	
	private static final String CREATE_TABLE_SCORE_BOARD
							= "create table "+ TABLE_NAM +"( "
							+SCORE_COLUMN_ID+" integer primary key, "
							+SCORE_COLUMN_NAME+" text,"
							+SCORE_COLUMN_SCORE+" text)";
	
	private ScoreOpenHelper openHelper;
	private SQLiteDatabase database;
	
	public ScoreListDatabaseHelper(Context context) {
		openHelper = new ScoreOpenHelper(context);
		database = openHelper.getWritableDatabase();
	}
	
	/**
	 * 保存得分记录
	 * @param name
	 * @param score
	 */
	public void saveScoreRecord(String name, Long score) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(SCORE_COLUMN_NAME, name);
		contentValues.put(SCORE_COLUMN_SCORE, score);
		database.insert(TABLE_NAM, null, contentValues);
	}
	
	/**
	 * 查询得分列表记录
	 * @return
	 */
	public Cursor getTimeRecordList() {
		return database.rawQuery("select * from "+TABLE_NAM, null) ;
	}
	
	/**
	 * provides with all of the basic behavior to manage a database.
	 */
	private class ScoreOpenHelper extends SQLiteOpenHelper{
		
		public ScoreOpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		
		/**
		 * 数据库每一次被创建的时候调用
		 */
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(
					CREATE_TABLE_SCORE_BOARD
					);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("drop table if exists "+ TABLE_NAM);
			onCreate(db);
		}

	}
}

