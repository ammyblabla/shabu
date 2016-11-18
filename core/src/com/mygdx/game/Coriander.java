package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Coriander extends Food{
	public Coriander(String imagefile, long bornTime, World world, Vector2 position) {
		super(imagefile, bornTime, world, position);
		setSook(true);
		System.out.println("pakcheee");
	}

	@Override
	public void eated() {
		getWorld().setGameOver();
		System.out.println("eated pakchee");
	}
	
	@Override
	public void setSook() {
		setSook(true);
	}
}
