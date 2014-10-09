package org.mrseige.game;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import org.mrseige.base.entity.MonsterEnum;
import org.mrseige.base.entity.MonsterList;
import org.mrseige.base.entity.MonsterProperty;
import org.mrseige.common.BitMapManager;

import android.graphics.Bitmap;

public class MonsterWizardRule {
	public static EnumMap<MonsterEnum, Bitmap[]> enumMap_bitmap = new EnumMap<MonsterEnum, Bitmap[]>(MonsterEnum.class) ;
	public static Map<Bitmap[], Bitmap[]> enumMap_deadBitmap = new HashMap<Bitmap[], Bitmap[]>() ;
	public static Map<Bitmap[], Bitmap[]> enumMap_attackBitmap = new HashMap<Bitmap[], Bitmap[]>() ;
	public static EnumMap<MonsterEnum, MonsterProperty> enumMap_monsterproperty = new EnumMap<MonsterEnum, MonsterProperty>(MonsterEnum.class);
	
	static{
		enumMap_bitmap.put(MonsterEnum.A, BitMapManager.getInstance().A);
		enumMap_deadBitmap.put(BitMapManager.getInstance().A, BitMapManager.getInstance().A_DEAD);
		enumMap_attackBitmap.put(BitMapManager.getInstance().A, BitMapManager.getInstance().A_ATTACK);
		enumMap_monsterproperty.put(MonsterEnum.A, MonsterList.monsterList.get(MonsterEnum.A));
		
		enumMap_bitmap.put(MonsterEnum.B, BitMapManager.getInstance().B);
		enumMap_deadBitmap.put(BitMapManager.getInstance().B, BitMapManager.getInstance().B_DEAD);
		enumMap_attackBitmap.put(BitMapManager.getInstance().B, BitMapManager.getInstance().B_ATTACK);
		enumMap_monsterproperty.put(MonsterEnum.B, MonsterList.monsterList.get(MonsterEnum.B));
		
		enumMap_bitmap.put(MonsterEnum.C, BitMapManager.getInstance().C);
		enumMap_deadBitmap.put(BitMapManager.getInstance().C, BitMapManager.getInstance().C_DEAD);
		enumMap_attackBitmap.put(BitMapManager.getInstance().C, BitMapManager.getInstance().C_ATTACK);
		enumMap_monsterproperty.put(MonsterEnum.C, MonsterList.monsterList.get(MonsterEnum.C));
		
		enumMap_bitmap.put(MonsterEnum.D, BitMapManager.getInstance().D);
		enumMap_deadBitmap.put(BitMapManager.getInstance().D, BitMapManager.getInstance().D_DEAD);
		enumMap_attackBitmap.put(BitMapManager.getInstance().D, BitMapManager.getInstance().D_ATTACK);
		enumMap_monsterproperty.put(MonsterEnum.D, MonsterList.monsterList.get(MonsterEnum.D));
		
		
		enumMap_bitmap.put(MonsterEnum.E, BitMapManager.getInstance().E);
		enumMap_deadBitmap.put(BitMapManager.getInstance().E, BitMapManager.getInstance().E_DEAD);
		enumMap_attackBitmap.put(BitMapManager.getInstance().E, BitMapManager.getInstance().E_ATTACK);
		enumMap_monsterproperty.put(MonsterEnum.E, MonsterList.monsterList.get(MonsterEnum.E));
		
		enumMap_bitmap.put(MonsterEnum.F, BitMapManager.getInstance().F);
		enumMap_deadBitmap.put(BitMapManager.getInstance().F, BitMapManager.getInstance().F_DEAD);
		enumMap_attackBitmap.put(BitMapManager.getInstance().F, BitMapManager.getInstance().F_ATTACK);
		enumMap_monsterproperty.put(MonsterEnum.F, MonsterList.monsterList.get(MonsterEnum.F));
		
	}
}
