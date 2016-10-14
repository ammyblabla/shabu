package com.mygdx.game;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
public class GameScreen extends ScreenAdapter {
	private ShabuGame shabuGame;
	private Texture shabuImg;
	private Pork pork;
	private Texture porkImg;
	public GameScreen(ShabuGame shabugame){
		this.shabuGame = shabugame;
		shabuImg = new Texture("background.png");
		porkImg = new Texture("pork1raw.png");
	}
	

	public void render(float delta){
		System.out.println(delta);	
		//Pork pork = new Pork(porkImg);
		SpriteBatch batch = shabuGame.batch;
		batch.begin();
		batch.draw(shabuImg,0,0);
		batch.draw(porkImg,100,100);
		batch.end();

//		pork.render();
		
		//for(int i=0; i<5; i++){
//			Pork pork;
//			Texture porkImg;
//			pork = new Pork();
//			Vector2 porkPosition = pork.getPosition();
//			
//			batch.begin();
//			batch.draw(pork.getPorkImg(),porkPosition.x,porkPosition.y);
//			batch.end();

		//}
		
	}
//	public void render(){
//		for(int i=0; i<5; i++){
//			Pork pork;
//			Texture porkImg = null;
//			pork = new Pork(porkImg);
//			Vector2 porkPosition = pork.getPosition();
//			SpriteBatch batch;
//			batch.begin();
//			batch.draw(pork.getPorkImg(),porkPosition.x,porkPosition.y);
//			batch.end();
//
//		}
//		
//	}

}
