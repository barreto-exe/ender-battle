package game;

import actors.Player;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.screens.GameScreen;
import basedatos.DBUsuario;
import com.badlogic.gdx.Gdx;
import comunicacion.MetodosSocket;
import comunicacion.MetodosSocket.UsesSocket;
import comunicacion.PaqueteOperacion;
import comunicacion.PaqueteResultado;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainGame extends Game
{
    private DBUsuario usuario;
    private JFrame ventanaOrigen;

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
    
    
    private SpriteBatch batch;
    private Player player;
    
    @Override
    public void create()
    {
        batch = new SpriteBatch();
        player = new Player("normal");
        setScreen(new GameScreen(this, "bioma1.tmx", player));          
    }

    @Override
    public void render()
    {
        super.render();
        ventanaOrigen.setTitle
        (
            "Minecraft: La Batalla del Ender"
            + " || FPS: " + Gdx.graphics.getFramesPerSecond()
            + " || " + usuario.getUsuario()
        );
    }    
    
    public SpriteBatch getBatch()
    {
        return batch;
    }
}
