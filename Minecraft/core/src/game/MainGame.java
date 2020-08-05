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
import javax.swing.JOptionPane;

public class MainGame extends Game implements UsesSocket
{
    private static DBUsuario usuario;
    
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
        Gdx.graphics.setTitle
        (
            "Minecraft: La Batalla del Ender"
            + " || FPS: " + Gdx.graphics.getFramesPerSecond()
            //+ " || " + usuario.getUsuario()
        );
    }

    @Override
    public void recibirRespuestaServer(final Socket socket, final MetodosSocket.UsesSocket ventanaOrigen)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    //Leer respuesta del servidor
                    ObjectInputStream paqueteRecibido = new ObjectInputStream(socket.getInputStream());
                    PaqueteResultado resultado = (PaqueteResultado) paqueteRecibido.readObject();
                    
                    //<editor-fold defaultstate="collapsed" desc="Resultados de salir de partida">
                    if(resultado.getResultado() == PaqueteOperacion.ResultadoOperacion.SALIR_PARTIDA_EXITOSO)
                    {
                        JOptionPane.showMessageDialog(null, "Has salido de la partida.");
                    }
                    //</editor-fold>
                    
                    socket.close();
                }
                catch (IOException | ClassNotFoundException ex)
                {
                    System.out.println(ex.getMessage());
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        }        
        ).start();
    }
    
    
    public static DBUsuario getUsuario()
    {
        return usuario;
    }
    
    public static void setUsuario(DBUsuario usuario)
    {
        MainGame.usuario = usuario;
    }
    
    public SpriteBatch getBatch()
    {
        return batch;
    }
}
