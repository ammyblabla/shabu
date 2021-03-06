package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class FoodList {
	private List<Food> foods = new ArrayList<Food>();
	public World world;
	private final int INIT_FOOD = 2;
	private float HOWLONGLASTFOOD;
	private float DELAY = 2;
	private List<String> listOfFood;

	public FoodList(World world) {
		this.world = world;
		listOfFood = new ArrayList<String>();
		initListOfFood();
	}
	
	public void initListOfFood() {
		listOfFood.add("meat1");
		listOfFood.add("meat2");
		listOfFood.add("pork1");
		listOfFood.add("pork2");
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
				if(tmp.getIsSook() == false) {
					world.decreaseScore(tmp.getDecreaseScore());
				} else {
					tmp.eated();
				}
				world.positionFood[tmp.positionNumber] = false;
				foods.remove(foods.get(i));
				return true;
			}
		}
		return false;
	}
	
	private void initFood() {
		for (int i = 0; i < INIT_FOOD; i++) {
			Food newFood = new Food("meat1",world.getTime(),this.world,new Vector2((float)300,(float)300));
			foods.add(newFood);
		}
	}
	
	public void foodDisappearDependDuration() {
		for (int i = 0; i < foods.size(); i++) {
			Food food = foods.get(i);
			if (world.getTime() - food.getBornTime() >= food.getDuration()) {
				world.positionFood[food.positionNumber] = false;
				foods.remove(foods.get(i));
			}
		}
	}
	
	public void releaseFood(float delta) {
		HOWLONGLASTFOOD += delta;
		if(HOWLONGLASTFOOD >= DELAY) {
			String nameFood = randomFood();
			int random = world.randomNum();			
			Vector2 foodPosition = world.generatePosition(new Texture(nameFood + ".png"), random);

			if(!foodPosition.isZero()) {			
				Food food = null;
				food = chooseConstructor(food,nameFood, foodPosition, random);
				foods.add(food);
				HOWLONGLASTFOOD = 0; 
			} 
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
	
	private Food chooseConstructor(Food food, String nameFood, Vector2 foodPosition, int random) {
		System.out.println(nameFood);
		if(nameFood == "corn") {
			food = new Corn(nameFood, world.getTime(), this.world, foodPosition);
		} else if(nameFood == "babycorn") {
			food = new Babycorn(nameFood, world.getTime(), this.world, foodPosition);
		} 
		else if (nameFood == "ham_cheese") {
			food = new HamCheese(nameFood, world.getTime(), this.world, foodPosition);
		} 
		else if (nameFood == "coriander") {
			food = new Coriander(nameFood, world.getTime(), this.world, foodPosition);
		}
		else {
			food = new Food(nameFood,world.getTime(),this.world, foodPosition);
		}
		food.positionNumber = random;
		return food;
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
