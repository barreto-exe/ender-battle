package game;

import game.actors.Player;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.screens.GameScreen;
import basedatos.DBUsuario;
import com.badlogic.gdx.Gdx;
import comunicacion.ProgresoJugador;
import game.screens.worlds.Room;
import game.tools.Constant;
import javax.swing.JFrame;

public class MainGame extends Game 
{
    private DBUsuario usuario;
    private Player player;
    private JFrame ventanaOrigen;
    private SpriteBatch batch;
    private ProgresoJugador progreso;

    public MainGame(DBUsuario usuario)
    {
        this.usuario = usuario;
        
        if(usuario != null)
            progreso = new ProgresoJugador(usuario);
        else
            progreso = new ProgresoJugador();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public SpriteBatch getBatch()
    {
        return batch;
    }
    public ProgresoJugador getProgreso()
    {
        return progreso;
    }
    
    public Player getPlayer()
    {
        return player;
    }

    public DBUsuario getUsuario()
    {
        return usuario;
    }
    
    public void setUsuario(DBUsuario usuario)
    {
        this.usuario = usuario;
    }
    
    public JFrame getVentanaOrigen()
    {
        return ventanaOrigen;
    }
    
    public void setVentanaOrigen(JFrame ventanaOrigen)
    {
        this.ventanaOrigen = ventanaOrigen;
    }
    
    //</editor-fold>
    
    @Override
    public void create()
    {
        batch = new SpriteBatch();
        
        String skin = "normal";
        if(usuario != null)
        {
            skin = usuario.getPersonajeSeleccionadoString();
        }
        
        player = new Player(skin);
        setScreen(new GameScreen(this, (new Room(1, Constant.MapType.FIGHT)), player)); 
    }

    @Override
    public void render()
    {
        String nombreUser = "";
        
        if(usuario != null)
        {
            
            nombreUser = " || " + usuario.getUsuario();
        }
        
        super.render();
        ventanaOrigen.setTitle
        (
            "Minecraft: La Batalla del Ender"
            + " || FPS: " + Gdx.graphics.getFramesPerSecond()
            + nombreUser
        );
    }    
    
}
