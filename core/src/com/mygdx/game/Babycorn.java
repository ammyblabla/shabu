package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Babycorn extends Food{
	public Babycorn(String imagefile, long bornTime, World world, Vector2 position) {
		super(imagefile, bornTime, world, position);
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
