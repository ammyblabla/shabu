package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class World {
	private ShabuGame shabugame;
	private ShabuGame shabuGame;
	private Texture shabuImg;
	private List<Food> foods = new ArrayList<Food>();
	private final float DELAY = 0.5f;
	private float HOWLONGLASTFOOD;
	private int score;
	private BitmapFont scoreText;
	private BitmapFont lifeText;
	private final int INIT_FOOD = 2;
	private int LIFE = 3;
	private float LAST_DISAPPEAR = 0;
	private final int SCALE_TEXT = 2;
	private Chopstick chopstick;

	public World(ShabuGame shabuGame){
		this.shabugame=shabugame;
		initFood();
		shabuImg = new Texture("background3.png");
		initFood();
		chopstick = new Chopstick();
		score = 0;
		scoreText = new BitmapFont();
		scoreText.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		lifeText = new BitmapFont();
	}
	
	private void initFood(){
		for(int i=0; i<INIT_FOOD; i++)
		{
			Food newFood = new Food("meatball_pork",getTime());
			foods.add(newFood);
		}
	}
	
	private long getTime(){
		return System.currentTimeMillis();
	}
	
}

