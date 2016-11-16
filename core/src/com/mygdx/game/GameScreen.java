package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;

public class GameScreen extends ScreenAdapter {
	private ShabuGame shabuGame;
	private World world;
	private WorldRenderer worldRenderer;
	private float clickDelay = 0.5f;
	private float clickDelayChopstick = 1;
	private PauseScreen pauseScreen;
	private GameOverScreen gameOverScreen;
	private static long dieTime = 0;
	private boolean isPause = false;
	
	public GameScreen(ShabuGame shabugame) {
		this.shabuGame = shabugame;
		world = new World(shabugame);
		worldRenderer = new WorldRenderer(shabugame,world);
		pauseScreen = new PauseScreen(shabugame);
		gameOverScreen = new GameOverScreen(shabugame);
	}
	
	public void render(float delta) {
		update(delta);
		chooseScreen(delta);
	}
	
	public void update(float delta) {
		world.update(delta);	
		disappearByClick();
		
	}

	public void disappearByClick() {
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && world.getLAST_DISAPPEAR() >= clickDelay) {
			int pointerX = Gdx.input.getX();
			int pointerY = Gdx.input.getY();
		
			if(!world.getFoodList().foodDisappear(pointerX, pointerY)) {
				world.decreaseLife();
			}
			world.setDisappear(0);
			
		}
	}
	
	public void chooseScreen(float delta) {
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && isPause == false) {
			System.out.println("pause");
			isPause = true;
			pauseScreen.render(delta); 
		} else if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && isPause == true) {
			isPause = false;
			worldRenderer.render(delta);
		}else if (isPause == true){
			pauseScreen.render(delta); 
		} else if(dieTime < world.getTime() && dieTime > 0) {
			gameOverScreen.render(delta);
			System.out.println("game over");
		} else if(world.getLife() == 0 && dieTime <= world.getTime()) {
			dieTime = world.getTime();
			worldRenderer.render(delta);
		}
		else {
			worldRenderer.render(delta);
		}
	}
}