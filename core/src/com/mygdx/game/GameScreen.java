package com.mygdx.game;
import com.badlogic.gdx.ScreenAdapter;;
public class GameScreen extends ScreenAdapter {
	private ShabuGame shabuGame;
	
	public GameScreen(ShabuGame shabugame){
		this.shabuGame = shabugame;
	}
	

	public void render(float delta){
		System.out.println(delta);
	}
}
