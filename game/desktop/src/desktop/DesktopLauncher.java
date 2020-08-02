package desktop; 

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import tools.Constant;
import game.MainGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                //DEFININENDO TAMAÑO DEL FRAME
                config.height = Constant.FRAME_HEIGHT;
                config.width = Constant.FRAME_WIDTH;
                config.resizable = false;
                //LLAMANDO AL FRAME
		new LwjglApplication(new MainGame(), config);
	} 
}
