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
	private static Vector2 zero  = new Vector2();
//	private float distanceBetween2food;

	public FoodList(World world) {
		this.world = world;
		listOfFood = new ArrayList<String>();
		initListOfFood();
//		initFood();
//		world.printArr(world.getPositionFood());
	}
	
	public void initListOfFood() {
		listOfFood.add("meat1");
		listOfFood.add("meat2");
		listOfFood.add("ham_cheese");
		listOfFood.add("pork1");
		listOfFood.add("pork2");
//		listOfFood.add("babycorn");
//		listOfFood.add("corn");
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
				System.out.println(tmp.positionNumber + " " + tmp.getPosition().x);
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
				foods.remove(foods.get(i));
			}
		}
	}
	
	public void releaseFood(float delta) {
		HOWLONGLASTFOOD += delta;
		if(HOWLONGLASTFOOD >= DELAY) {
//			String nameFood = randomFood();
			int random = world.randomNum();			
//			System.out.println("zero" + zero.x + zero.y);
			Vector2 foodPosition = world.generatePosition(new Texture("meat1.png"), random);
			System.out.println(foodPosition.isZero());
			if(!foodPosition.isZero()) {			
	//			Food food = null;
				Food food = new Food("meat1", world.getTime(), this.world, foodPosition);
				food.positionNumber = random;
				System.out.println("food " + food.getPosition().x + " " +food.getPosition().y);

	//			if(nameFood == "corn") {
	//				food = new Corn(nameFood, world.getTime(), this.world);
	//			} else if(nameFood == "babycorn") {
	//				food = new Babycorn(nameFood, world.getTime(), this.world);
	//			}else {
	//				food = new Food(nameFood,world.getTime(),this.world);
	//			}
	//			chooseConstructor(food,nameFood);
				foods.add(food);
				HOWLONGLASTFOOD = 0; 
			} else {
				System.out.println("regen position");
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
	
	private void chooseConstructor(Food food, String nameFood) {
		Texture tmpTexture = new Texture("meat1");
//		if(nameFood == "corn") {
//			food = new Corn(nameFood, world.getTime(), this.world,world);
//		} else {
//			food = new Food("meat1",world.getTime(),this.world,world.generatePosition(tmpTexture));
//		}
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
