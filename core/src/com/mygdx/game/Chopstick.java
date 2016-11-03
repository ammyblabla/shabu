package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Chopstick {
	private Texture chopstickImg;
	private Vector2 position;
	private float originX, originY, radius;
	
	public Chopstick() {
		chopstickImg = new Texture("chopstick04.png");
		randomChopstickPosition();
//		position = new Vector2(510,482);
//		private void calculateCircleEquation(float xStart, float xEnd, float yStart, float yEnd)
		calculateCircleEquation(284,736,58,510);
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
	
	public void moveAroundPot() {
		
	}
	
	private void calculateCircleEquation(float xStart, float xEnd, float yStart, float yEnd){
		radius = Math.abs(xEnd-xStart)/2;
		originX = Math.min(xStart, xEnd) + radius - chopstickImg.getWidth()/2;
		originY = Math.min(yStart, yEnd) + radius - chopstickImg.getHeight()/2;
	}
	
	private void randomChopstickPosition() {
		Random rand = new Random();
		float x = rand.nextInt(2*(int)radius+1) + originX - radius;
		float power = rand.nextInt(2);
		double y = Math.pow(-1, power)*Math.sqrt(Math.abs(Math.pow(x-originX,2) - Math.pow(radius,2)))+originY; 
		position = new Vector2(x,(float) y);
	}
}
