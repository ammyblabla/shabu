package com.mygdx.game;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class GameScreen extends ScreenAdapter {
	private ShabuGame shabuGame;
	private Texture shabuImg;
	private List<Food> foods = new ArrayList<Food>();
	public float DELAY = 2;
	public float HOWLONGLASTFOOD;
	private int INIT_FOOD = 1;


	public GameScreen(ShabuGame shabugame){
		this.shabuGame = shabugame;
		shabuImg = new Texture("background.png");
		for(int i=0; i<INIT_FOOD; i++){
            foods.add(new Food("pork1raw"));
		}
	}
	

	public void render(float delta){
		update(delta);
		SpriteBatch batch = shabuGame.batch;
		batch.begin();
		batch.draw(shabuImg,0,0);
		drawFood(batch);
		batch.end();
	}
	
	public void drawFood(SpriteBatch batch) //create food once again
	{
		for (Food food : foods) {
			Vector2 foodPosition = food.getPosition();
			batch.draw(food.getFoodImg(),foodPosition.x,foodPosition.y);
		}
	}
	
	public void update(float delta) 
	{
//		releaseFood(delta);
		
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			int pointerX = Gdx.input.getX();
			int pointerY = Gdx.input.getY();
			System.out.println("X " + pointerX + " Y "+pointerY);
			for(int i=0; i < foods.size(); i++)
			{
				Food food = foods.get(i);
				System.out.println("foodX "+food.getX()+" foodY "+food.getY());
				
				float deltaX = Math.abs(pointerX-food.getX());
				float deltaY = Math.abs(pointerY-food.getY()+food.getFoodImg().getHeight());
				
				System.out.println("deltaX " + deltaX + " deltaY " + deltaY);
				if(deltaX >= 0 && deltaX <= food.getFoodImg().getWidth() && deltaY >= 0 && deltaY <= food.getFoodImg().getHeight())
				{
					//
					//remove foods
					System.out.println("remove "+"foodX "+food.getX()+" foodY "+food.getY()+" cursorX "+pointerX+" cursorY "+pointerY);
					foods.remove(foods.get(i));
				}
				System.out.println();
			}
			
		}
	}
		
	
	
	public void releaseFood(float delta){
		HOWLONGLASTFOOD +=delta;
		//จะปล่อยเนื้อทุกๆ 5 วิ
		if(HOWLONGLASTFOOD>=DELAY)
		{
			//release new food 
			foods.add(new Food("pork1raw"));
			HOWLONGLASTFOOD = 0; 
		}
	}

}
