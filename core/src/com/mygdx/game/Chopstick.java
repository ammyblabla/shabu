package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Chopstick {
	private Texture chopstickImg;
	private Vector2 position;
	
	public Chopstick() {
		chopstickImg = new Texture("chopstick03.png");
		position = new Vector2(510,482);
	}
	
	public float getX() {
		return position.x;
	}
	
	public float getY() {
		return position.y;
	}
	
	public Texture getChopstickImg() {
		return chopstickImg;
	}
	
	public Vector2 getPosition() {
		return position;
	}
}
