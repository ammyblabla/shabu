package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class HamCheese extends Food {
//	int DURATION = 3000;
	public HamCheese(String imagefile, long bornTime, World world, Vector2 position) {
		super(imagefile, bornTime, world, position);
		System.out.println("ham cheeze");
		super.setDuration(3000);
	}	
	
	
	@Override
	public void eated() {
		getWorld().increaseScore(2);
		System.out.println("eated");
	}
	
	

}
