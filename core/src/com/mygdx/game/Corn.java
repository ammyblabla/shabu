package com.mygdx.game;

public class Corn extends Food{
	public Corn(String imagefile, long bornTime, World world) {
		super(imagefile, bornTime, world);
		setSook(true);
	}
	
	@Override
	public void eated() {
		getWorld().increaseScore();
		getWorld().setLife(3);
	}
	
	@Override
	public void setSook() {
		setSook(true);
	}

}
