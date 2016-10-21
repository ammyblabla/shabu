package com.mygdx.game;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
public class GameScreen extends ScreenAdapter {
	private ShabuGame shabuGame;
	private Texture shabuImg;
	private Food food;
	private Texture foodImg;
	
	public GameScreen(ShabuGame shabugame){
		this.shabuGame = shabugame;
		shabuImg = new Texture("background.png");
		food = new Food("pork1raw");
	}
	

	public void render(float delta){
		SpriteBatch batch = shabuGame.batch;
		batch.begin();
		batch.draw(shabuImg,0,0);
		Vector2 foodPosition = food.getPosition();
		batch.draw(food.getFoodImg(),foodPosition.x,foodPosition.y);
		batch.end();
	}

}
