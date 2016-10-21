package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Pork {
	private Vector2 position;
//	private String imageRaw;
//	private String imageSook;
//	private static final int TIME = 10;
	private Texture porkImg;
	private SpriteBatch batch;
	
	public Pork(){
		porkImg = new Texture("pork1raw.png");
//		this.porkImg=porkImg;
		generatePosition();
		System.out.println("pork generated");
		
	}
	public void generatePosition(){
		Random rand = new Random();
		int x = rand.nextInt(750) + 250;
		int y = rand.nextInt(250) + 250;
		position = new Vector2(x,y);
		System.out.printf("{0} {1}",position.x,position.y);
	}
	public Texture getPorkImg(){
		return porkImg;
	}
	public Vector2 getPosition(){
		return position;
	}
	public void render(){
		batch.begin();
		batch.draw(porkImg,position.x,position.y);
		batch.end();
	}
}
