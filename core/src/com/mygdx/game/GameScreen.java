package com.mygdx.game;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class GameScreen extends ScreenAdapter {
	private ShabuGame shabuGame;
	private Texture shabuImg;
	private List<Food> foods = new ArrayList<Food>();
	public float DELAY = 5;
	public float HOWLONGLASTFOOD;

	public GameScreen(ShabuGame shabugame){
		this.shabuGame = shabugame;
		shabuImg = new Texture("background.png");
		for(int i=0; i<2; i++){
            foods.add(new Food("meat1raw"));
		}
	}
	

	public void render(float delta){
		update(delta);
		SpriteBatch batch = shabuGame.batch;
		batch.begin();
		batch.draw(shabuImg,0,0);
		drawFood(batch);
		System.out.println(HOWLONGLASTFOOD);
		System.out.println("delta "+delta);
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
