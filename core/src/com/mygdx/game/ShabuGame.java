package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShabuGame extends Game {
	public SpriteBatch batch;
	public Sprite sprite;
	public final int HEIGHT;
	public final int WIDTH;

	public ShabuGame() {
		HEIGHT = 540;
		WIDTH = 960;
	}
	
	public void create () {
        batch = new SpriteBatch();
        sprite = new Sprite();
        GameScreen gameScreen = new GameScreen(this);
        setScreen(gameScreen);
	}

	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
