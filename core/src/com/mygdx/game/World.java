package com.mygdx.game;

import java.util.List;

public class World {
	private ShabuGame shabuGame;
	private FoodList foodList;
	private int score;
	private int LIFE;
	private float LAST_DISAPPEAR = 0;
	private float LAST_CHOPSTICKCHECKED = 0;
	private Chopstick chopstick;
	private boolean isChopstickClicked = false;
	public boolean wasChopstickClicked = false;
	
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
	
	public boolean getChopstickClicked() {
		return isChopstickClicked;
	}
	
	public void setChopstickClickedTrue() {
		isChopstickClicked = true;
	}
	
	public void setChopstickClickedFalse() {
		isChopstickClicked = false;
	}
	
	public void update(float delta) {
		foodList.foodDisappearDependDuration();
		foodList.releaseFood(delta);
		updateFood();
		LAST_DISAPPEAR += delta;
		LAST_CHOPSTICKCHECKED += delta;
		chopstick.randomSpeed();
		chopstick.moveAroundPot();
//		Food release
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
	
	public void setLAST_CHOPSTICKCHECKED(float set) {
		LAST_CHOPSTICKCHECKED = set;
	}
	
	public float getLAST_CHOPSTICKCHECKED(){
		return LAST_CHOPSTICKCHECKED;
	}
	
	public long getTime() {
		return System.currentTimeMillis();
	}
	
	private void updateFood() {
		List<Food> list = foodList.getList();
		for(Food food : list) {
			food.update();
		}
	}
}

