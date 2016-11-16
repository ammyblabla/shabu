package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverScreen extends ScreenAdapter {
	private ShabuGame shabuGame;
	private BitmapFont pauseFont;
	
	public GameOverScreen(ShabuGame shabuGame) {
		this.shabuGame = shabuGame;
		pauseFont = new BitmapFont();
		pauseFont.getData().setScale(4, 4);
	}
	
	@Override
	public void render(float delta) {
		SpriteBatch batch = shabuGame.batch;	
		batch = new SpriteBatch();
		batch.begin();
		pauseFont.draw(batch,"Game Over",375 ,300);
		batch.end();
	}

}
