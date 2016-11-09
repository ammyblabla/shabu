package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;

public class FoodList {
	private List<Food> foods = new ArrayList<Food>();
	public World world;
	private final int INIT_FOOD = 2;
	private float HOWLONGLASTFOOD;
	private final float DELAY = 1;

//	private float distanceBetween2food;

	public FoodList(World world) {
		this.world = world;
		initFood();
		System.out.println("foodList");
	}
	
	public boolean foodDisappear(int pointerX, int pointerY) {
		
		for (int i=foods.size()-1; i >= 0; i--)
		{
			Food food = foods.get(i);
			
			float foodX = food.getX();
			float foodY = world.getScreenHeight()-food.getY();
			
			float deltaX = pointerX-foodX;
			float deltaY = (-1)*(pointerY-foodY);
			
//			System.out.println(pointerX + " "+ pointerY+" foodx"+foodX+" foodY "+ food.getY() +" " +foodY);
			
			if (deltaX >= 0 && deltaX <= food.getFoodImg().getWidth() && deltaY >= 0 && deltaY <= food.getFoodImg().getHeight())
			{
				world.increaseScore();
				foods.remove(foods.get(i));
				return true;
			}
		}
		return false;
	}
	
	private void initFood(){
		for (int i=0; i<INIT_FOOD; i++) {
			Food newFood = new Food("meatball_pork",getTime());
			foods.add(newFood);
		}
	}
	
	public void foodDisappearDependDuration() {
		for (int i=0; i<foods.size(); i++) {
			Food food = foods.get(i);
			if (getTime()-food.getBornTime()>=food.getDuration()){
				foods.remove(foods.get(i));
			}
		}
	}
	
	private boolean checkNewFoodPosition(List<Food> foods, Food foodInput) {
		float xFoodInput = foodInput.getX();
		float yFoodInput = foodInput.getY();
		
		for (Food food: foods) {
			float xFoodLoop = food.getX();
			float yFoodLoop = food.getY();
			
			if(Math.abs(xFoodInput-xFoodLoop)>=20 || Math.abs(yFoodInput - yFoodLoop)>=20) {
				return false;
			}
		}
		
		return true;
	}
	
	private long getTime() {
		return System.currentTimeMillis();
	}
	
	public void releaseFood(float delta){
		HOWLONGLASTFOOD += delta;
		if(HOWLONGLASTFOOD >= DELAY)
		{
			Food food = new Food("ham_cheese",getTime());
//			while(!checkNewFoodPosition(foods,food)){
//				food.regeneratePosition();
//			}
			foods.add(food);
			HOWLONGLASTFOOD = 0; 
		}
	}
	
	public List<Food> getList() {
		return foods;
	}
	
//	private boolean checkNewFoodPosition(List<Food> foods, Food foodInput) {
//	float xFoodInput = foodInput.getX();
//	float yFoodInput = foodInput.getY();
//	
//	for (Food food: foods) {
//		float xFoodLoop = food.getX();
//		float yFoodLoop = food.getY();
//		
//		if(Math.abs(xFoodInput-xFoodLoop)>=20 || Math.abs(yFoodInput - yFoodLoop)>=20) {
//			return false;
//		}
//	}
//	
//	return true;
//}
}
