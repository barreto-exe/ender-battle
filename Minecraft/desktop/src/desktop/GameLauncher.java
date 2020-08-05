package desktop;

import basedatos.DBUsuario;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import desktop.menu.FrmPrincipal;
import game.MainGame;
import tools.Constant;

/**
 *
 * @author luisb
 */
public class GameLauncher
{
    private static DBUsuario usuario;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        
        //DEFININENDO TAMAÑO DEL FRAME
        config.height = Constant.FRAME_HEIGHT;
        config.width = Constant.FRAME_WIDTH;
        config.resizable = true;
        //config.fullscreen = true;
        
        MainGame juego = new MainGame();
        MainGame.setUsuario(usuario);
        
        //LLAMANDO AL FRAME
		new LwjglApplication(juego, config);
    }

    public static DBUsuario getUsuario()
    {
        return usuario;
    }
    public static void setUsuario(DBUsuario usuario)
    {
        GameLauncher.usuario = usuario;
    }
}
