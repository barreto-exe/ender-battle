package game.tools;

import java.io.BufferedInputStream;
import java.io.InputStream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author luisb
 */
public class Reproductor implements Runnable
{

    private Sonido sonido;
    private boolean bucle;
    private boolean estaReproduciendo;
    private Player player;
    
    /**
     * Objeto que reproduce sonido en otro hilo.
     * 
     * @param sonido Es el objeto sonido a reproducir.
     * @param bucle Indica si se reproduce en bucle o no.
     */
    public Reproductor(Sonido sonido, boolean bucle)
    {
        this.sonido = sonido;
        this.bucle = bucle;
        
        estaReproduciendo = true;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public Sonido getSonido()
    {
        return sonido;
    }
    
    public void setSonido(Sonido sonido)
    {
        this.sonido = sonido;
    }
    
    public void detenerReproduccion()
    {
        this.estaReproduciendo = false;
        player.close();
    }
    //</editor-fold>
    
    @Override
    public void run()
    {
        try
        {
            do
            {
                if(!estaReproduciendo)
                {
                    return;
                }
                
                estaReproduciendo = true;
                
                InputStream stream = this.getClass().getResourceAsStream(this.sonido.getUbicacionSonido());
                BufferedInputStream bstream = new BufferedInputStream(stream);
                player = new Player(bstream);
                
                player.play();
            } while (bucle);

        } catch (JavaLayerException e)
        {
            System.out.println("" + e);
        }
    }

}
