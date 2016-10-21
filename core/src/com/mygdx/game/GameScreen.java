package com.mygdx.game;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends ScreenAdapter {
	private ShabuGame shabuGame;
	private Texture shabuImg;
//	private Food food;
	private Texture foodImg;    
	private List<Food> foods = new ArrayList<Food>();

	
	
	public GameScreen(ShabuGame shabugame){
		this.shabuGame = shabugame;
		shabuImg = new Texture("background.png");
//		food = new Food("pork1raw");
		for (int i = 0; i<10 ; i++ ) {
            foods.add(new Food("meat1raw"));
		}
	}
	

	public void render(float delta){
		SpriteBatch batch = shabuGame.batch;
		batch.begin();
		batch.draw(shabuImg,0,0);
//		Vector2 foodPosition = food.getPosition();
		for (Food food : foods) {
			Vector2 foodPosition = food.getPosition();
			batch.draw(food.getFoodImg(),foodPosition.x,foodPosition.y);
        }
//		batch.draw(food.getFoodImg(),foodPosition.x,foodPosition.y);
		batch.end();
	}

}
