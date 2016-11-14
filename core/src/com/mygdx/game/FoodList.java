package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FoodList {
	private List<Food> foods = new ArrayList<Food>();
	public World world;
	private final int INIT_FOOD = 2;
	private float HOWLONGLASTFOOD;
	private final float DELAY = 0.8f;
	private List<String> listOfFood;

//	private float distanceBetween2food;

	public FoodList(World world) {
		this.world = world;
		initFood();
	}
	
	public void initListOfFood() {
		listOfFood = new ArrayList<String>();
		listOfFood.add("ham_cheese");
//		listOfFood.add("cucumber");
//		listOfFood.add("corn");
//		listOfFood.add("meat1");
//		listOfFood.add("meat2");
//		listOfFood.add("pork1");
//		listOfFood.add("pork2");
//		listOfFood.add("meatball_cheese");
//		listOfFood.add("meatball_crystalFisEgg");
//		listOfFood.add("meatball_pork");
//		listOfFood.add("tofu_fish");
	
	}
	public boolean foodDisappear(int pointerX, int pointerY) {
		
		for (int i=foods.size()-1; i >= 0; i--)
		{
			Food food = foods.get(i);
			
			float foodX = food.getX();
			float foodY = world.getScreenHeight() - food.getY();
			
			float deltaX = pointerX - foodX;
			float deltaY = (-1) * (pointerY - foodY);
					
			if (deltaX >= 0 && deltaX <= food.getFoodImg().getWidth() && 
				deltaY >= 0 && deltaY <= food.getFoodImg().getHeight()) {
				world.increaseScore();
				foods.remove(foods.get(i));
				return true;
			}
		}
		return false;
	}
	
	private void initFood() {
		for (int i = 0; i < INIT_FOOD; i++) {
			Food newFood = new Food("meat1",world.getTime(),this.world);
			foods.add(newFood);
		}
	}
	
	public void foodDisappearDependDuration() {
		for (int i = 0; i < foods.size(); i++) {
			Food food = foods.get(i);
			if (world.getTime() - food.getBornTime() >= food.getDuration()) {
				foods.remove(foods.get(i));
			}
		}
	}
	
	public void releaseFood(float delta) {
		HOWLONGLASTFOOD += delta;
		if(HOWLONGLASTFOOD >= DELAY) {
			Food food = new Food("meat2",world.getTime(),this.world);
//			food.setSook();
//			System.out.println(checkNewFoodPosition(food));
//			while(!checkNewFoodPosition(food)) {
//				food.regeneratePosition();
//			}
			
			foods.add(food);
			HOWLONGLASTFOOD = 0; 
		}
	}
	
	public List<Food> getList() {
		return foods;
	}
	
	private boolean checkNewFoodPosition(Food foodInput) {
		float xFoodInput = foodInput.getX();
		float yFoodInput = foodInput.getY();
		
		for (Food food: foods) {
			float xFoodLoop = food.getX();
			float yFoodLoop = food.getY();
			
			if (Math.abs(xFoodInput-xFoodLoop) < food.getFoodImg().getWidth() || 
				Math.abs(yFoodInput - yFoodLoop) < food.getFoodImg().getHeight()) {
				
				return false;
			}
		}
		
		return true;
	}
	
	private String randomFood() {
		Random rand = new Random();
		int i = rand.nextInt(listOfFood.size());
		return listOfFood.get(i);
		
	}
}
