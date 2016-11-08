package com.mygdx.game;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;


public class GameScreen extends ScreenAdapter {
	private ShabuGame shabuGame;
	private Texture shabuImg;
	private List<Food> foods = new ArrayList<Food>();
	private FoodList foodList;
//	private final float DELAY = 0.5f;
//	private float HOWLONGLASTFOOD;
//	public int score;
	private BitmapFont scoreText;
	private BitmapFont lifeText;
//	private final int INIT_FOOD = 2;
	private int LIFE = 3;
	private float LAST_DISAPPEAR = 0;
	private final int SCALE_TEXT = 2;
	private Chopstick chopstick;
//	World world;

	public GameScreen(ShabuGame shabugame){
		this.shabuGame = shabugame;
		shabuImg = new Texture("background3.png");
//		world = new World(shabugame);
//		world.getFoodList().initFood();
		foodList = new FoodList();
		foods = foodList.getList();
		chopstick = new Chopstick();
//		score = 0;
		scoreText = new BitmapFont();
		scoreText.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		lifeText = new BitmapFont();
	}
	
	public void render(float delta){
		update(delta);
		SpriteBatch batch = shabuGame.batch;
		batch.begin();
		batch.draw(shabuImg,0,0);
//		batch.draw(chopstick.getChopstickImg(),chopstick.getX(),chopstick.getY());
//		batch.draw(chopstick.getChopstickImg(),chopstick.getPosition().x,chopstick.getPosition().y);
		drawChopstick(batch);
		setScoreText();
		scoreText.draw(batch, "score: " + foodList.score,50, 100);
		setLifeText();
		lifeText.draw(batch, "life: " + LIFE,50, 50);
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
		foodList.foodDisappearDependDuration();
		foodList.releaseFood(delta);
		LAST_DISAPPEAR += delta;
		chopstick.moveAroundPot();
		
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && LAST_DISAPPEAR >=0.3){
			int pointerX = Gdx.input.getX();
			int pointerY = Gdx.input.getY();
			if(!foodList.foodDisappear(pointerX, pointerY)) {
				LIFE--;
			}
			LAST_DISAPPEAR=0;
		}
		
	}

	public void setScoreText() {
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
		Vector2 chopstickPosition = chopstick.getPosition();
		Texture chopstickImg = chopstick.getChopstickImg();
		float width = chopstickImg.getWidth();
		float height = chopstickImg.getWidth();
		float[] origin = chopstick.getOrigin();
		float[] middleChopstick = chopstick.getMiddle();
		TextureRegion chopstickTextureRegion = new TextureRegion(chopstick.getChopstickImg());
//		draw(Texture texture, float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation, int srcX, int srcY, int srcWidth, int srcHeight, boolean flipX, boolean flipY)
		batch.draw(chopstick.getChopstickImg(),chopstickPosition.x,chopstickPosition.y,middleChopstick[0],middleChopstick[1],1,1,1,1,false,false);
//		batch.draw(chopstickTextureRegion,chopstickPosition.x,chopstickPosition.y);
//		batch.draw(chopstick.getChopstickImg(),chopstickPosition.x,chopstickPosition.y);
//	    batch.draw(chopstickTextureRegion, chopstickPosition.x, chopstickPosition.y,origin[0] ,origin[1] ,width , height, 1, 1, chopstick.getAngle(), true);
	}
	
}