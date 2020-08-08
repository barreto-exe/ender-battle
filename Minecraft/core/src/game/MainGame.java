package game;

import game.actors.Player;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.screens.GameScreen;
import basedatos.DBUsuario;
import com.badlogic.gdx.Gdx;
import javax.swing.JFrame;

public class MainGame extends Game
{
    private DBUsuario usuario;
    private Player player;
    private JFrame ventanaOrigen;
    private SpriteBatch batch;

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
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
        player = new Player("normal");
        setScreen(new GameScreen(this, "bioma_01.tmx", player));          
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
    
    public SpriteBatch getBatch()
    {
        return batch;
    }
}
