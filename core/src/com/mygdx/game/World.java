package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class World {
	private ShabuGame shabuGame;
	private FoodList foodList;
	private int score;
	private int LIFE;
	private float LAST_DISAPPEAR = 0;
	private Chopstick chopstick;
	
	public World(ShabuGame shabuGame) {
		this.shabuGame = shabuGame;
		chopstick = new Chopstick();
		foodList = new FoodList(this);
		LIFE = 3;
		score = 0;
	}
	
	public void increaseScore() {
		score++;
	}
	
	public void decreaseLife() {
		LIFE--;
	}
	
	public FoodList getFoodList() {
		return foodList;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getLife() {
		return LIFE;
	}
	
	public Chopstick getChopstick() {
		return chopstick;
	}
	
	public void update(float delta) {
		foodList.foodDisappearDependDuration();
		foodList.releaseFood(delta);
		LAST_DISAPPEAR += delta;
		chopstick.moveAroundPot();
	}

	public void setDisappear(float set) {
		LAST_DISAPPEAR = set;
	}
	
	public float getLAST_DISAPPEAR(){
		return LAST_DISAPPEAR;
	}
	
	public float getScreenWidth () {
		return shabuGame.WIDTH;
	}
	
	public float getScreenHeight () {
		return shabuGame.HEIGHT;
	}
}

