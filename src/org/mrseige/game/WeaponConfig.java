package org.mrseige.game;

import java.util.ArrayList;

public class WeaponConfig {
	private static ArrayList<WeaponProperty> crossBows = new ArrayList<WeaponProperty>(){
		{
			add(new WeaponProperty(1, 1.5, 1));
			add(new WeaponProperty(2, 2.5, 2));
			add(new WeaponProperty(2, 2.5, 2));
			add(new WeaponProperty(7, 1.5, 4));
			add(new WeaponProperty(11, 1, 5));
			add(new WeaponProperty(16, 0.6,5));
		}
	};

	public static ArrayList<WeaponProperty> getCrossBows() {
		return crossBows;
	}
	
}
