package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.ShabuGame;

public class DesktopLauncher {
	private static ShabuGame shabuGame;
	public static void main (String[] arg) {
		shabuGame = new ShabuGame();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width= shabuGame.WIDTH;
		config.height= shabuGame.HEIGHT;
		new LwjglApplication(new ShabuGame(), config);
	}
	
	
}
