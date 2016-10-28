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
	private float DELAY = 0.5f;
	private float HOWLONGLASTFOOD;
	private int score;
	private BitmapFont scoreText;
	private BitmapFont lifeText;
	private int INIT_FOOD = 2;
	private int LIFE = 3;
	private float LAST_DISAPPEAR = 0;

	public GameScreen(ShabuGame shabugame){
		this.shabuGame = shabugame;
		shabuImg = new Texture("background.png");
		initFood();
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
		setScoreText();
		scoreText.draw(batch, "" + score,100, 100);
		setLifeText();
		lifeText.draw(batch, "" + LIFE,100, 200);
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
		//จะปล่อยเนื้อทุกๆ 5 วิ
		if(HOWLONGLASTFOOD>=DELAY)
		{
			//release new food 
			foods.add(new Food("pork1raw"));
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
			float foodY = 720-food.getY();
			
			float deltaX = pointerX-foodX;
			float deltaY = (-1)*(pointerY-foodY);
			
			if(deltaX >= 0 && deltaX <= food.getFoodImg().getWidth() && deltaY >= 0 && deltaY <= food.getFoodImg().getHeight())
			{
				foods.remove(foods.get(i));
				score++;
				System.out.println("score " + score);
				return true;
			}
		}
		return false;
	}

	public void setScoreText() {
		
		scoreText.getData().setScale(3, 3);
	}
	
	public void setLifeText() {
		lifeText.setColor(Color.RED);
		lifeText.getData().setScale(3, 3);
	}
	
	private void initFood(){
		for(int i=0; i<INIT_FOOD; i++)
		{
			foods.add(new Food("cucumber"));
		}
	}
	
}