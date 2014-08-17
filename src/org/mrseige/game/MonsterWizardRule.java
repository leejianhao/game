package org.mrseige.game;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import org.mrseige.common.BitMapManager;

import android.graphics.Bitmap;

public class MonsterWizardRule {
	public static EnumMap<MonsterWizard, Bitmap[]> enumMap_bitmap = new EnumMap<MonsterWizard, Bitmap[]>(MonsterWizard.class) ;
	public static Map<Bitmap[], Bitmap[]> enumMap_deadBitmap = new HashMap<Bitmap[], Bitmap[]>() ;
	public static Map<Bitmap[], Bitmap[]> enumMap_attackBitmap = new HashMap<Bitmap[], Bitmap[]>() ;
	public static EnumMap<MonsterWizard, MonsterProperty> enumMap_monsterproperty = new EnumMap<MonsterWizard, MonsterProperty>(MonsterWizard.class);
	
	static MonsterProperty copyToMonster(MonsterWizard mw) {
		MonsterProperty monsterProperty = new MonsterProperty(mw.getType(), mw.getFigure(), mw.getWidth(), mw.getHeight(), mw.getLife(), mw.getAttack(), mw.getSpeed(), mw.getBulk(), mw.getPriority());
		return monsterProperty;
	}
	static{
		enumMap_bitmap.put(MonsterWizard.A, BitMapManager.getInstance().A);
		enumMap_deadBitmap.put(BitMapManager.getInstance().A, BitMapManager.getInstance().A_DEAD);
		enumMap_attackBitmap.put(BitMapManager.getInstance().A, BitMapManager.getInstance().A_ATTACK);
		enumMap_monsterproperty.put(MonsterWizard.A, copyToMonster(MonsterWizard.A));
		
		enumMap_bitmap.put(MonsterWizard.B, BitMapManager.getInstance().B);
		enumMap_deadBitmap.put(BitMapManager.getInstance().B, BitMapManager.getInstance().B_DEAD);
		enumMap_attackBitmap.put(BitMapManager.getInstance().B, BitMapManager.getInstance().B_ATTACK);
		enumMap_monsterproperty.put(MonsterWizard.B, copyToMonster(MonsterWizard.B));
		
		enumMap_bitmap.put(MonsterWizard.C, BitMapManager.getInstance().C);
		enumMap_deadBitmap.put(BitMapManager.getInstance().C, BitMapManager.getInstance().C_DEAD);
		enumMap_attackBitmap.put(BitMapManager.getInstance().C, BitMapManager.getInstance().C_ATTACK);
		enumMap_monsterproperty.put(MonsterWizard.C, copyToMonster(MonsterWizard.C));
		
		enumMap_bitmap.put(MonsterWizard.D, BitMapManager.getInstance().D);
		enumMap_deadBitmap.put(BitMapManager.getInstance().D, BitMapManager.getInstance().D_DEAD);
		enumMap_attackBitmap.put(BitMapManager.getInstance().D, BitMapManager.getInstance().D_ATTACK);
		enumMap_monsterproperty.put(MonsterWizard.D, copyToMonster(MonsterWizard.D));
		
		
		enumMap_bitmap.put(MonsterWizard.E, BitMapManager.getInstance().E);
		enumMap_deadBitmap.put(BitMapManager.getInstance().E, BitMapManager.getInstance().E_DEAD);
		enumMap_attackBitmap.put(BitMapManager.getInstance().E, BitMapManager.getInstance().E_ATTACK);
		enumMap_monsterproperty.put(MonsterWizard.E, copyToMonster(MonsterWizard.E));
		
		enumMap_bitmap.put(MonsterWizard.F, BitMapManager.getInstance().F);
		enumMap_deadBitmap.put(BitMapManager.getInstance().F, BitMapManager.getInstance().F_DEAD);
		enumMap_attackBitmap.put(BitMapManager.getInstance().F, BitMapManager.getInstance().F_ATTACK);
		enumMap_monsterproperty.put(MonsterWizard.F, copyToMonster(MonsterWizard.F));
		
	}
}
