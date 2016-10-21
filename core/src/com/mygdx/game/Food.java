package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Food {
	private Vector2 position;
	private Texture foodImg;
	private SpriteBatch batch;
	
	public Food(String imagefile)
	{
		foodImg = new Texture(imagefile+".png");
		generatePosition();
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
}
