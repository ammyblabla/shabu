package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;

public class GameScreen extends ScreenAdapter {
	private ShabuGame shabuGame;
	private World world;
	private WorldRenderer worldRenderer;
	private float clickDelay = 0.5f;
	
	public GameScreen(ShabuGame shabugame) {
		this.shabuGame = shabugame;
		world = new World(shabugame);
		worldRenderer = new WorldRenderer(shabugame,world);
	}
	
	public void render(float delta) {
		update(delta);
		worldRenderer.render(delta);
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
}