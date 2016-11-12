package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Chopstick {
	private static Texture chopstickImg;
	private Vector2 position;
	private static float originX, originY, radius, angle, originalOriginX, originalOriginY;
	private float move = 10;
	private static float speed = 0.2f;

	
	public Chopstick() {
		chopstickImg = new Texture("chopstick06.png");
//		randomChopstickPosition();
		position = new Vector2(510,482);
//		private void calculateCircleEquation(float xStart, float xEnd, float yStart, float yEnd)
		calculateCircleEquation(284,736,58,510);
		angle = 0;
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
		angle += speed;
		float x = (float)(originX + Math.cos(angle*Math.PI/180)*radius);
		float y  = (float)(originY + Math.sin(angle*Math.PI/180)*radius);
		setPos(x, y);
	}
	
	private void calculateCircleEquation(float xStart, float xEnd, float yStart, float yEnd) {
		radius = Math.abs(xEnd-xStart)/2;
		originX = Math.min(xStart, xEnd) + radius;
		originY = Math.min(yStart, yEnd) + radius;
		originalOriginX = originX;
		originalOriginY = originY;
//		System.out.println(radius*2);
	}
	
	private void randomChopstickPosition() {
		Random rand = new Random();
		float x = rand.nextInt(2*(int)radius+1) + originX - radius;
		float power = rand.nextInt(2);
		double y = Math.pow(-1, power)*Math.sqrt(Math.abs(Math.pow(x-originX,2) - Math.pow(radius,2)))+originY; 
		position = new Vector2(x,(float) y);
	}
	
	private void setPos(float x, float y) {
		Vector2 tmp = new Vector2(x,y);
		this.position = tmp.cpy();
	}
	
	public float getAngle() {
		return angle;
	}
	
	public float[] getOrigin() {
		float[] origin = new float[2];
		origin[0] = originalOriginX;
		origin[1] = originalOriginY;
		return origin;
	}
	
	public float[] getMiddle() {
		float[] middle = new float[2];
		middle[0] = chopstickImg.getWidth()/2;
		middle[1] = chopstickImg.getHeight()/2;
		return middle;
	}
	
	public void randomSpeed() {
		Random rand = new Random();
		speed = (float)(rand.nextInt(250))/100f;
	}
	
	public void moveChopstickWhenClicked(Sprite sprite) {
		float x = move;
		float y = x * (float)Math.tan(angle);
		if(originX < originalOriginX+x) {
			originX += x;
			originY += y;
		}
		sprite.setPosition(originX - 0.5f * chopstickImg.getWidth() + x, originY - 0.5f * chopstickImg.getHeight() + y);
//		sprite.setOrigin(getMiddle()[0], getMiddle()[1]);
	}
	
	public void moveChopstickWhenUnClicked(Sprite sprite) {
		if(originX != originalOriginX)
		{
			originX = originalOriginX;
			originY = originalOriginY;
		}
		sprite.setPosition(originX - 0.5f * chopstickImg.getWidth(), originY - 0.5f * chopstickImg.getHeight());
		sprite.setOrigin(getMiddle()[0], getMiddle()[1]);
	}
}
