package game;

import actors.Player;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.screens.GameScreen;
import basedatos.DBUsuario;
import com.badlogic.gdx.Gdx;
import comunicacion.Closer;

public class MainGame extends Game
{
    private SpriteBatch batch;
    private Player player;
    
    private DBUsuario usuario;
    private Closer ventanaOrigen;
    
    public SpriteBatch getBatch()
    {
        return batch;
    }

    @Override
    public void create()
    {
        batch = new SpriteBatch();
        player = new Player("normal");
        setScreen(new GameScreen(this, "bioma1.tmx", player));          //MOSTRANDO LA PANTALLA DE JUEGO
    }

    @Override
    public void render()
    {
        super.render();
        Gdx.graphics.setTitle
        (
            "Minecraft: La Batalla del Ender"
            + " || FPS: " + Gdx.graphics.getFramesPerSecond()
        );
    }

    @Override
    public void dispose()
    {
        super.dispose();
        ventanaOrigen.cerrarPartida();
    }

    
    public DBUsuario getUsuario()
    {
        return usuario;
    }

    public void setUsuario(DBUsuario usuario)
    {
        this.usuario = usuario;
    }

    public Closer getVentanaOrigen()
    {
        return ventanaOrigen;
    }

    public void setVentanaOrigen(Closer ventanaOrigen)
    {
        this.ventanaOrigen = ventanaOrigen;
    }
}
