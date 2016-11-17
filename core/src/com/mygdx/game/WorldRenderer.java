package com.mygdx.game;

import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class WorldRenderer {
	private World world;	
	private ShabuGame shabuGame;
	private Texture shabuImg;
	private BitmapFont scoreText;
	private BitmapFont lifeText;
	private BitmapFont stageText;
	private final int SCALE_TEXT = 2;
	
	public WorldRenderer(ShabuGame shabuGame, World world) {
		this.world = world;
		this.shabuGame = shabuGame;
		shabuImg = new Texture("background3.png");
		
		scoreText = new BitmapFont();
		lifeText = new BitmapFont();
		stageText = new BitmapFont();
	}
	
	public void render(float delta) {
		world.update(delta);
		SpriteBatch batch = shabuGame.batch;
		
		batch.begin();
		batch.draw(shabuImg,0,0);
//		drawChopstickBySprite(batch);
		drawText(batch);
		drawFood(batch);
		batch.end();
		
//		if(world.getLife() <= 0){
//			shabuGame.setScreen(new GameOverScreen(shabuGame));
//	        shabugame.dispose();
//	    }
	}
	
	public void drawFood(SpriteBatch batch) {
		List<Food> foods = world.getFoodList().getList();
		for (Food food : foods) {
			Vector2 foodPosition = food.getPosition();
			batch.draw(food.getFoodImg(),foodPosition.x,foodPosition.y);
		}
	}
	
	public void setScoreText() {
		scoreText.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		scoreText.getData().setScale(SCALE_TEXT, SCALE_TEXT);
	}
	
	public void setLifeText() {
		lifeText.setColor(Color.RED);
		lifeText.getData().setScale(SCALE_TEXT, SCALE_TEXT);
	}
	
	public  void setStageText() {
		stageText.getData().setScale(SCALE_TEXT, SCALE_TEXT);

	}
	
	private long getTime() {
		return System.currentTimeMillis();
	}
	
	private void drawText(SpriteBatch batch) {
		setScoreText();
		scoreText.draw(batch, "score: " + world.getScore(),50, 100);
		setLifeText();
		lifeText.draw(batch, "life: " + world.getLife(),50, 50);
		setStageText();
		stageText.draw(batch, "stage: " + world.getStage(),50, 200);
	}
	
//	private void drawChopstickBySprite(SpriteBatch batch) {
//		Texture chopstickImg = world.getChopstick().getChopstickImg();		
//		float[] origin = world.getChopstick().getOrigin();
//		float[] middleChopstick = world.getChopstick().getMiddle();		
//		float width = world.getChopstick().getChopstickImg().getWidth();
//		float height = world.getChopstick().getChopstickImg().getHeight();
//		
//		Sprite sprite = new Sprite(chopstickImg);
//		sprite.setPosition(origin[0] - 0.5f * width, origin[1] - 0.5f * height);
//		if(world.getChopstickClicked()) {
//			world.getChopstick().moveChopstickWhenClicked(sprite);
//		} else {
//			world.getChopstick().moveChopstickWhenUnClicked(sprite);
//		}
//		sprite.setOrigin(middleChopstick[0], middleChopstick[1]);
//		sprite.setRotation(world.getChopstick().getAngle());
//		sprite.draw(batch);	
//		
//	}
}
