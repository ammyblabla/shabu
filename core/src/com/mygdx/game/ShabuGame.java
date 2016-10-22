package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShabuGame extends Game {
	public SpriteBatch batch;
	Texture img;
	public int HEIGHT;
	public int WIDTH;
	
//	@Override
	
	public ShabuGame() {
		HEIGHT = 720;
		WIDTH = 1280;
	}
	
	
	public void create () {
        batch = new SpriteBatch();
        setScreen(new GameScreen(this));
	}


	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
