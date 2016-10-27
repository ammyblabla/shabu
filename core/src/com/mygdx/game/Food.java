package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Food {
	private Vector2 position;
	private Texture foodImg;
	private SpriteBatch batch;
	public static float DELAY = 10;
	public Food(String imagefile)
	{
		foodImg = new Texture(imagefile+".png");
		generatePosition();
//		System.out.println("x "+ position.x + " y "+ (720-position.y));
	}
	
	private void generatePosition()
	{
		Random rand = new Random();
		int x = rand.nextInt(750) + 250;
		int y = rand.nextInt(250) + 250;
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
}
