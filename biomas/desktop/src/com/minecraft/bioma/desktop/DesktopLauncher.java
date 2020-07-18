package com.minecraft.bioma.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.minecraft.bioma.MainGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                //DEFININENDO TAMAÑO DEL FRAME
                config.width = 1080;
                config.height = 720;
                config.resizable = false;
                //LLAMANDO AL FRAME
		new LwjglApplication(new MainGame(), config);
	} 
} 
 