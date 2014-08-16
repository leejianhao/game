package org.mrseige.test;

import org.mrseige.db.ScoreListDatabaseHelper;

import android.test.AndroidTestCase;

public class ScoreServiceTest extends AndroidTestCase {
	
	public void  testScoreDb() {
		ScoreListDatabaseHelper sdh = new ScoreListDatabaseHelper(getContext());
		sdh.saveScoreRecord("ljh", 100l);
	}
}
