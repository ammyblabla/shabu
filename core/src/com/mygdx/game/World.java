package com.mygdx.game;

import java.util.List;

public class World {
	private ShabuGame shabuGame;
	private FoodList foodList;
	private int score;
	private int LIFE;
	private float LAST_DISAPPEAR = 0;
	private float LAST_CHOPSTICKCHECKED = 0;
//	private Chopstick chopstick;
//	private boolean isChopstickClicked = false;
//	public boolean wasChopstickClicked = false;
	public boolean[] positionFood;
	public boolean isGameOver = false;
	private int stage = 1;

	public World(ShabuGame shabuGame) {
		this.shabuGame = shabuGame;
//		chopstick = new Chopstick();
		positionFood = new boolean[5];
		initPositionFood();
		foodList = new FoodList(this);
		LIFE = 3;
		score = 0;
	}
	
	public void increaseScore() {
		score++;
	}
	
	public void decreaseScore(int i) {
		score -= i;
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
	
	public void setLife(int i) {
		LIFE = i;
	}
	
//	public Chopstick getChopstick() {
//		return chopstick;
//	}
//	
//	public boolean getChopstickClicked() {
//		return isChopstickClicked;
//	}
//	
//	public void setChopstickClickedTrue() {
//		isChopstickClicked = true;
//	}
//	
//	public void setChopstickClickedFalse() {
//		isChopstickClicked = false;
//	}
	
	public void update(float delta) {
//		foodList.foodDisappearDependDuration();
		foodList.releaseFood(delta);
		updateFood();
		LAST_DISAPPEAR += delta;
		checkStage();
//		LAST_CHOPSTICKCHECKED += delta;
//		chopstick.randomSpeed();
//		chopstick.moveAroundPot();
		
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
	
	private void initPositionFood() {
		for(int i=0; i<positionFood.length; i++) {
			positionFood[i] = false;
		}
	}
	
	public boolean[] getPositionFood() {
		return positionFood;
	}
	
	public boolean isPositionFood(int i) {
		return positionFood[i];
	}
	
	public void setPositionFoodFalse(int i) {
		positionFood[i] = false;
		System.out.println(positionFood[i]);
	}
	
	public void setPositionFoodTrue(int i) {
		positionFood[i] = true;
		System.out.println(positionFood[i]);
	}
	
	public void printArr(boolean[] arr) {
		for(int i=0; i<arr.length; i++) {
			System.out.print(i+" ");
		}
		System.out.println();
	}
	
	public void setStage() {
		if(stage == 2) {
			foodList.setDelay(2.25f);
		}
	}
	
	public void checkStage() {
		if(score > 10) {
			stage = 2;
			setStage();
			System.out.println("stage 2");
		}
		
	}
}

