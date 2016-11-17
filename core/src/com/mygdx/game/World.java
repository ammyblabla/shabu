package com.mygdx.game;

import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class World {
	private ShabuGame shabuGame;
	private FoodList foodList;
	private int score;
	private int LIFE;
	private float LAST_DISAPPEAR = 0;
	private float LAST_CHOPSTICKCHECKED = 0;
	private Chopstick chopstick;
//	private boolean isChopstickClicked = false;
//	public boolean wasChopstickClicked = false;
	public boolean[] positionFood;
	public boolean isGameOver = false;
	private float xStart = 384;
	private int stage = 1;
	private float radius;
	private float originX;
	private float originY;

	public World(ShabuGame shabuGame) {
		this.shabuGame = shabuGame;
		chopstick = new Chopstick();
		positionFood = new boolean[8];
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
	
	public void increaseLife() {
		LIFE++;
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
	
	public int getStage() {
		return stage;
	}
	
	public Chopstick getChopstick() {
		return chopstick;
	}
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
		foodList.foodDisappearDependDuration();
		foodList.releaseFood(delta);
		updateFood();
		LAST_DISAPPEAR += delta;
		if(score % 10 == 0) {
			checkStage();
		}
//		LAST_CHOPSTICKCHECKED += delta;
		chopstick.randomSpeed();
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
			if(!foodList.isInList("babycorn"))
				foodList.addListFood("babycorn");
		} else if (stage == 3) {
			foodList.setDelay(1.8f);
		}
	}
	
	public void checkStage() {
		if(score > 20) {
			stage = 3;
			setStage();
		} else if(score == 10) {
			stage = 2;
			setStage();
//			System.out.println("stage 2");
		}
		
	}
	
	public Vector2 generatePosition(Texture foodImg,int random) {
//		calculateCircleEquation(float xStart, float xEnd, float yStart, float yEnd);
		calculateCircleEquation(xStart,654,371,101, foodImg);
		
		Random rand = new Random();
//		float x = rand.nextInt(2*(int)radius+1) + originX - radius;
//		int random = rand.nextInt(5);
		if(positionFood[random] == true) {
			return new Vector2();
		}
		positionFood[random] = true;
		int randomX = random;
		int randomY = 0;
		if(randomX > 4) {
			randomX -= 4;
			randomY = 1;
		}
		System.out.println(positionFood[random]+ " " + random);
		float x = ((randomX-1) * radius)/2 + xStart + foodImg.getWidth()/2;
		float power = rand.nextInt(2);
		double y = Math.pow(-1, randomY) * Math.sqrt(Math.abs(Math.pow(x-originX,2) - Math.pow(radius,2)))+originY; 
		return new Vector2(x,(float) y);
	}
	
	public int randomNum() {
		Random rand = new Random();
		return rand.nextInt(8);
	}
	
	private void calculateCircleEquation(float xStart, float xEnd, float yStart, float yEnd, Texture foodImg) {
		radius = Math.abs(xEnd-xStart)/2;
		originX = Math.min(xStart, xEnd) + radius - foodImg.getWidth()/2;
		originY = Math.min(yStart, yEnd) + radius - foodImg.getHeight()/2;
	}
}

