package com.mygdx.game;

public class Babycorn extends Food{
	public Babycorn(String imagefile, long bornTime, World world) {
		super(imagefile, bornTime, world);
		setSook(true);
	}
	
	@Override
	public void eated() {
		getWorld().increaseScore();
		getWorld().increaseLife();
	}
	
	@Override
	public void setSook() {
		setSook(true);
	}
}
