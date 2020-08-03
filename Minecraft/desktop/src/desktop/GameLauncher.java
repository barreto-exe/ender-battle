package desktop;

import basedatos.DBUsuario;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import game.MainGame;
import tools.Constant;

/**
 *
 * @author luisb
 */
public class GameLauncher
{
    private DBUsuario usuario;

    public GameLauncher(DBUsuario usuario)
    {
        this.usuario = usuario;
        
        
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        //DEFININENDO TAMAÑO DEL FRAME
        config.height = Constant.FRAME_HEIGHT;
        config.width = Constant.FRAME_WIDTH;
        config.resizable = true;
        //LLAMANDO AL FRAME
		new LwjglApplication(new MainGame(), config);
    }

    public DBUsuario getUsuario()
    {
        return usuario;
    }

    public void setUsuario(DBUsuario usuario)
    {
        this.usuario = usuario;
    }
}
