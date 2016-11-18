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
	public static boolean isGameOver = false;
	private float xStart = 384;
	private int stage = 1;
	private float radius;
	private float originX;
	private float originY;
	private boolean isGameStart = false;

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
		isGameStart = true;

	}
	
	public void increaseScore(int i) {
		score += i;
		isGameStart = true;

	}
	
	public void decreaseScore(int i) {
		score -= i;
		isGameStart = true;
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
	
	public void update(float delta) {
		System.out.println("game over " + isGameOver);
		if(LIFE <= 0) {
			isGameOver = true;
			System.out.println("over");
		}
//		isGameStart = true;
		foodList.foodDisappearDependDuration();
		foodList.releaseFood(delta);
		updateFood();
		LAST_DISAPPEAR += delta;
		if(score % 5 == 0) {
			checkStage();
		}
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
	
	public void printArr(boolean[] arr) {
		for(int i=0; i<arr.length; i++) {
			System.out.print(i+" ");
		}
		System.out.println();
	}
	
	public void setStage() {
		if(stage == 2) {
			foodList.setDelay(1.5f);
			if(!foodList.isInList("coriander")) {
				foodList.addListFood("coriander");
			}
		} else if (stage == 3) {
			if(!foodList.isInList("babycorn")) {
				foodList.addListFood("babycorn");
			}
			foodList.setDelay(1.25f);
		} else if (stage == 4) {
			if(!foodList.isInList("ham_cheese")) {
				foodList.addListFood("ham_cheese");
			}
			foodList.setDelay(1f);
		} else if (stage == 5) {
			foodList.setDelay(0.5f);
			if(!foodList.isInList("corn")) {
				foodList.addListFood("corn");
			}
		}
	}
	
	public void checkStage() {
		if(score == 20){
			stage = 5;
		} else if(score == 15) {
			stage = 4;
		} else if(score == 10) {
			stage = 3;
		} else if(score == 5) {
			stage = 2;
		} 
		setStage();
		
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
//		System.out.println(positionFood[random]+ " " + random);
		float x = ((randomX-1) * radius)/2 + xStart + foodImg.getWidth()/2;
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
	
	public void setGameOver() {
		isGameOver = true;
	}
}

