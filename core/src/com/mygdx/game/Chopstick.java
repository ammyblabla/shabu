package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Chopstick {
	private static Texture chopstickImg;
	private static Vector2 position;
	private static float originX, originY, radius, angle;
	
	public Chopstick() {
		chopstickImg = new Texture("chopstick04.png");
		randomChopstickPosition();
//		position = new Vector2(510,482);
//		private void calculateCircleEquation(float xStart, float xEnd, float yStart, float yEnd)
		calculateCircleEquation(284,736,58,510);
		angle=0;
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
		angle += 0.30;
		float x = (float)(originX + Math.cos(angle*Math.PI/180)*radius);
		float y  = (float)(originY + Math.sin(angle*Math.PI/180)*radius);
		setPos(x, y);
		System.out.println(position.x+" "+position.y);
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
	
	private float setY(float x,int power) {
		return (float) (Math.pow(-1, power)*Math.sqrt(Math.abs(Math.pow(x-originX,2) - Math.pow(radius,2)))+originY); 
	}
	
	private void setPos(float x, float y) {
		Vector2 tmp = new Vector2(x,y);
		this.position = tmp.cpy();
	}
}
