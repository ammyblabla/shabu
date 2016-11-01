package com.mygdx.game;


import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Food {
	private Vector2 position;
	private Texture foodImg;
	private SpriteBatch batch;
	private long bornTime; //millisecond
	private long DURATION = 5000;
	private float radius;
	private float originX;
	private float originY;
	
	public Food(String imagefile, long bornTime)
	{
		foodImg = new Texture(imagefile+".png");
		generatePosition();
		this.bornTime = bornTime;
//		System.out.println(bornTime);
//		System.out.println("x "+ position.x + " y "+ (720-position.y));
	}
	
	private void generatePosition()
	{
//		calculateCircleEquation(float xStart, float xEnd, float yStart, float yEnd);
		calculateCircleEquation(384,654,371,101);
		Random rand = new Random();
		float x = rand.nextInt(2*(int)radius+1) + originX - radius;
//		float y = rand.nextInt(radius+1) + 101;
//		float x = rand.nextInt(1) + 0;
		float power = rand.nextInt(2);
		double y = Math.pow(-1, power)*Math.sqrt(Math.abs(Math.pow(x-originX,2) - Math.pow(radius,2)))+originY; 
		position = new Vector2(x,(float) y);
//		System.out.println((int)radius+1+384);
//		System.out.println("x "+x+" y "+y);
	}
	
	public Vector2 getPosition()
	{
		return position;
	}
	
	public Texture getFoodImg()
	{
		return foodImg;
	}
	
	public void render(){
		batch.begin();
		batch.draw(foodImg,position.x,position.y);
		batch.end();
	}
	
	public void drawFood()
	{
        batch.draw(foodImg, position.x, position.y);    
	}
	
	public float getX(){
		return position.x;
	}
	
	public float getY(){
		return position.y;
	}
	
	public long getBornTime() {
		return bornTime;
	}
	
	public long getDuration() {
		return DURATION;
	}
	
	private void calculateCircleEquation(float xStart, float xEnd, float yStart, float yEnd){
		radius = Math.abs(xEnd-xStart)/2;
		originX = Math.min(xStart, xEnd) + radius - foodImg.getWidth()/2;
		originY = Math.min(yStart, yEnd) + radius - foodImg.getHeight()/2;
	}
	
}
