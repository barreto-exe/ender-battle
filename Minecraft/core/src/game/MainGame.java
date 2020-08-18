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
    private boolean juegoTerminado;
    private boolean reiniciandoJuego;
    
    public MainGame(DBUsuario usuario)
    {
        this.usuario = usuario;
        this.juegoTerminado = this.reiniciandoJuego = false;
        
        if(usuario != null)
            progreso = new ProgresoJugador(usuario);
        else
            progreso = new ProgresoJugador();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">

    public boolean isJuegoTerminado()
    {
        return juegoTerminado;
    }

    public void setJuegoTerminado(boolean juegoTerminado)
    {
        this.juegoTerminado = juegoTerminado;
    }
    
    
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
        
        player = new Player(this, skin);
        setScreen(new GameScreen(this, (new Room(1, Constant.MapType.BIOME)), player)); 
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
    
    /**
     * Resetea la vida del jugador a 100, así como su inventario y sus esmeraldas.
     */
    public void reiniciarJuego()
    {
        if(reiniciandoJuego)
            return;
        
        reiniciandoJuego = true;
        
        player.setAlpha(0);
        player.getInventory().addEsmeraldas(-player.getInventory().getEsmeraldas());
        player.getInventory().vaciar();
        progreso.setJefesMatados(0);
        
        final MainGame game = this;
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(5000);
                    reiniciandoJuego = false;
                    ((GameScreen)game.getScreen()).cambiarHabitacion(new Room(1,Constant.MapType.BIOME));
                    Thread.sleep(7000);
                    player.setLife(100);
                    player.setAvisarReinicioJuego(false);
                }
                catch (InterruptedException ex)
                {
                }
            }
            
        }).start();

    }
}
