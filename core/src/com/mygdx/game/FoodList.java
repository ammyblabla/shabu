package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FoodList {
	private List<Food> foods = new ArrayList<Food>();
	public World world;
	private final int INIT_FOOD = 2;
	private float HOWLONGLASTFOOD;
	private float DELAY = 2;
	private List<String> listOfFood;
//	private float distanceBetween2food;

	public FoodList(World world) {
		this.world = world;
		listOfFood = new ArrayList<String>();
		initListOfFood();
		initFood();
//		world.printArr(world.getPositionFood());
	}
	
	public void initListOfFood() {
		listOfFood.add("meat1");
		listOfFood.add("meat2");
		listOfFood.add("ham_cheese");
		listOfFood.add("pork1");
		listOfFood.add("pork2");
//		listOfFood.add("babycorn");
		listOfFood.add("corn");
	}
	
	public boolean foodDisappear(int pointerX, int pointerY) {
		
		for (int i = foods.size()-1; i >= 0; i--)
		{
			Food food = foods.get(i);
			
			float foodX = food.getX();
			float foodY = world.getScreenHeight() - food.getY();
			
			float deltaX = pointerX - foodX;
//			float deltaY = (-1) * (pointerY - foodY);
			float deltaY = Math.abs(pointerY - foodY);

			if (deltaX >= 0 && deltaX <= food.getFoodImg().getWidth() && 
				deltaY >= 0 && deltaY <= food.getFoodImg().getHeight()) {
				Food tmp = foods.get(i);
//				System.out.println(tmp.getIsSook());				
				if(tmp.getIsSook() == false) {
					world.decreaseScore(tmp.getDecreaseScore());
				} else {
					tmp.eated();
				}
				
//				world.positionFood[tmp.positionNumber] = false;
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
			String nameFood = randomFood();
			Food food = null;
			if(nameFood == "corn") {
				food = new Corn(nameFood, world.getTime(), this.world);
			} else if(nameFood == "babycorn") {
				food = new Babycorn(nameFood, world.getTime(), this.world);
			}else {
				food = new Food(nameFood,world.getTime(),this.world);
			}
//			chooseConstructor(food,nameFood);
			foods.add(food);
			HOWLONGLASTFOOD = 0; 
		}
	}
	
	public List<Food> getList() {
		return foods;
	}
	
	private String randomFood() {
		Random rand = new Random();
		int i = rand.nextInt(listOfFood.size());
		return listOfFood.get(i);
		
	}
	
	public void setDelay(float i) {
		DELAY = i;
	}
	
	private void chooseConstructor(Food food, String nameFood) {
		if(nameFood == "corn") {
			food = new Corn(nameFood, world.getTime(), this.world);
		} else {
			food = new Food(nameFood,world.getTime(),this.world);
		}
	}
	
	public void addListFood(String nameFood) {
		listOfFood.add(nameFood);
		printList();
	}
	
	public void printList() {
		for(String i : listOfFood) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
	
	public boolean isInList(String nameFood) {
		for(String i : listOfFood) {
			if(i == nameFood)
				return true;
		}
		return false;
	}
}
