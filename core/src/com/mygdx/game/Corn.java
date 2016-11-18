package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Corn extends Food {
	public Corn(String imagefile, long bornTime, World world,Vector2 position) {
		super(imagefile, bornTime, world,position);
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
