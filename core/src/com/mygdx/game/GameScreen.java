package com.mygdx.game;
import java.util.ArrayList;
import java.util.List;

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
	public float DELAY = 10;

	public GameScreen(ShabuGame shabugame){
		this.shabuGame = shabugame;
		shabuImg = new Texture("background.png");
		for(int i=0; i<11; i++){
            foods.add(new Food("meat1raw"));
		}
	}
	

	public void render(float delta){
		SpriteBatch batch = shabuGame.batch;
		batch.begin();
		batch.draw(shabuImg,0,0);
		drawFood(batch);
		batch.end();
	}
	
	public void drawFood(SpriteBatch batch)
	{
		for (Food food : foods) {
			Vector2 foodPosition = food.getPosition();
			batch.draw(food.getFoodImg(),foodPosition.x,foodPosition.y);
		}
	}

}
