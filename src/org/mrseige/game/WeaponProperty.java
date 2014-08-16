package org.mrseige.game;

public class WeaponProperty {
	private double fireEnhancer;
	private double shootEnhancer;
	private double multiEnhancer;
	public double getFireEnhancer() {
		return fireEnhancer;
	}
	public void setFireEnhancer(double fireEnhancer) {
		this.fireEnhancer = fireEnhancer;
	}
	public double getShootEnhancer() {
		return shootEnhancer;
	}
	public void setShootEnhancer(double shootEnhancer) {
		this.shootEnhancer = shootEnhancer;
	}
	public double getMultiEnhancer() {
		return multiEnhancer;
	}
	public void setMultiEnhancer(double multiEnhancer) {
		this.multiEnhancer = multiEnhancer;
	}
	public WeaponProperty(double fireEnhancer, double shootEnhancer,
			double multiEnhancer) {
		super();
		this.fireEnhancer = fireEnhancer;
		this.shootEnhancer = shootEnhancer;
		this.multiEnhancer = multiEnhancer;
	}
	
	
}
