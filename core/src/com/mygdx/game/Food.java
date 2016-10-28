package com.mygdx.game;


import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Food {
	private Vector2 position;
	private Texture foodImg;
	private SpriteBatch batch;
	private float bornTime;
	private float DURATION = 5;

	
	public Food(String imagefile, float bornTime)
	{
		foodImg = new Texture(imagefile+".png");
		generatePosition();
		this.bornTime = bornTime;
		System.out.println(bornTime);
//		System.out.println("x "+ position.x + " y "+ (720-position.y));
	}
	
	private void generatePosition()
	{
		Random rand = new Random();
		int x = rand.nextInt(750) + 250;
		int y = rand.nextInt(250) + 150;
		position = new Vector2(x,y);
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
	
	public float getBornTime() {
		return bornTime;
	}
	
	public float getDuration() {
		return DURATION;
	}
	
}
