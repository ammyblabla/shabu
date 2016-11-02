package com.mygdx.game;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class GameScreen extends ScreenAdapter {
	private ShabuGame shabuGame;
	private Texture shabuImg;
	private List<Food> foods = new ArrayList<Food>();
	private final float DELAY = 0.5f;
	private float HOWLONGLASTFOOD;
	private int score;
	private BitmapFont scoreText;
	private BitmapFont lifeText;
	private final int INIT_FOOD = 2;
	private int LIFE = 3;
	private float LAST_DISAPPEAR = 0;
	private final int SCALE_TEXT = 2;
	private Chopstick chopstick;

	public GameScreen(ShabuGame shabugame){
		this.shabuGame = shabugame;
		shabuImg = new Texture("background3.png");
		initFood();
		chopstick = new Chopstick();
		score = 0;
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
		scoreText.draw(batch, "score: " + score,50, 100);
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
		foodDisappearDependDuration();
		releaseFood(delta);
		LAST_DISAPPEAR += delta;

		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && LAST_DISAPPEAR >=0.1){
			if(!foodDisappear()) {
				LIFE--;
			}
			LAST_DISAPPEAR=0;
		}
		
	}
		
	public void releaseFood(float delta){
		HOWLONGLASTFOOD +=delta;
		if(HOWLONGLASTFOOD>=DELAY)
		{
			foods.add(new Food("corn",getTime()));
			HOWLONGLASTFOOD = 0; 
		}
	}
	
	public boolean foodDisappear() {
		int pointerX = Gdx.input.getX();
		int pointerY = Gdx.input.getY();
		for(int i=foods.size()-1; i >= 0; i--)
		{
			Food food = foods.get(i);
			
			float foodX = food.getX();
			float foodY = shabuGame.HEIGHT-food.getY();
			
			float deltaX = pointerX-foodX;
			float deltaY = (-1)*(pointerY-foodY);
			
			if(deltaX >= 0 && deltaX <= food.getFoodImg().getWidth() && deltaY >= 0 && deltaY <= food.getFoodImg().getHeight())
			{
				foods.remove(foods.get(i));
				score++;
//				System.out.println("score " + score);
				return true;
			}
		}
		return false;
	}

	public void setScoreText() {
		
		scoreText.getData().setScale(SCALE_TEXT, SCALE_TEXT);
	}
	
	public void setLifeText() {
		lifeText.setColor(Color.RED);
		lifeText.getData().setScale(SCALE_TEXT, SCALE_TEXT);
	}
	
	private void initFood(){
		for(int i=0; i<INIT_FOOD; i++)
		{
			Food newFood = new Food("meatball_pork",getTime());
			foods.add(newFood);
		}
	}
	
	private void foodDisappearDependDuration() {
		for (int i=0; i<foods.size(); i++) {
			Food food = foods.get(i);
			if(getTime()-food.getBornTime()>=food.getDuration()){
				foods.remove(foods.get(i));
			}
		}
	}
	
	private long getTime(){
		return System.currentTimeMillis();
	}
	
	private void drawChopstick(SpriteBatch batch) {
		Vector2 chopstickPosition = chopstick.getPosition();
		batch.draw(chopstick.getChopstickImg(),chopstickPosition.x,chopstickPosition.y);
	}
	
}