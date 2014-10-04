package com.Peak.GameTest.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.peak.GameTest.GameTest;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Fighting Game";
		config.width = GameTest.window_width;
		config.height = GameTest.window_height;
		
		
		new LwjglApplication(new GameTest(), config);
	}
}
