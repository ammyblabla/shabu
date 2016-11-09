package com.mygdx.game;

import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class WorldRenderer {
	private World world;	
	private ShabuGame shabuGame;
	private Texture shabuImg;
	private BitmapFont scoreText;
	private BitmapFont lifeText;
	private final int SCALE_TEXT = 2;
	
	public WorldRenderer(ShabuGame shabuGame, World world) {
		this.world = world;
		this.shabuGame = shabuGame;
		shabuImg = new Texture("background3.png");
		
		scoreText = new BitmapFont();
		lifeText = new BitmapFont();
	}
	
	public void render(float delta){
		world.update(delta);
		SpriteBatch batch = shabuGame.batch;
		
		batch.begin();
		batch.draw(shabuImg,0,0);
		drawChopstick(batch);
		drawText(batch);
		drawFood(batch);
		batch.end();
	}
	
	public void drawFood(SpriteBatch batch) //create food once again
	{
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
	
	private long getTime() {
		return System.currentTimeMillis();
	}
	
	private void drawChopstick(SpriteBatch batch) {
	//	draw(TextureRegion region, float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation)
		Vector2 chopstickPosition = world.getChopstick().getPosition();
		Texture chopstickImg = world.getChopstick().getChopstickImg();
		
//		float width = chopstickImg.getWidth();
//		float height = chopstickImg.getWidth();
//		float[] origin = chopstick.getOrigin();
//		float[] middleChopstick = chopstick.getMiddle();
//		
//		TextureRegion chopstickTextureRegion = new TextureRegion(chopstick.getChopstickImg());
//		draw(Texture texture, float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation, int srcX, int srcY, int srcWidth, int srcHeight, boolean flipX, boolean flipY)
//		batch.draw(chopstick.getChopstickImg(),chopstickPosition.x,chopstickPosition.y,middleChopstick[0],middleChopstick[1],1,1,1,1,false,false);
//		batch.draw(chopstickTextureRegion,chopstickPosition.x,chopstickPosition.y);
		batch.draw(world.getChopstick().getChopstickImg(),chopstickPosition.x,chopstickPosition.y);
//	    batch.draw(chopstickTextureRegion, chopstickPosition.x, chopstickPosition.y,origin[0] ,origin[1] ,width , height, 1, 1, chopstick.getAngle(), true);
	
	}
	
	private void drawText(SpriteBatch batch) {
		setScoreText();
		scoreText.draw(batch, "score: " + world.getScore(),50, 100);
		setLifeText();
		lifeText.draw(batch, "life: " + world.getLife(),50, 50);
	}
}
