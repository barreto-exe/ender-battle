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
    private static FrmPrincipal ventanaOrigen;

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
        config.fullscreen = true;
        
        MainGame juego = new MainGame();
        juego.setUsuario(usuario);
        juego.setVentanaOrigen(ventanaOrigen);
        
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

    public static FrmPrincipal getVentanaOrigen()
    {
        return ventanaOrigen;
    }

    public static void setVentanaOrigen(FrmPrincipal ventanaOrigen)
    {
        GameLauncher.ventanaOrigen = ventanaOrigen;
    }
    
}
