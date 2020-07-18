/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kdl.minecraft.recursos;

import java.io.BufferedInputStream;
import java.io.InputStream;
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
     * Objeto que reproduce un sonido en otro hilo.
     *
     * @param sonido Objeto sonido a reproducir.
     * @param bucle Indica si se reproduce en bucle o no.
     */
    public Reproductor(Sonido sonido, boolean bucle)
    {
        this.sonido = sonido;
        this.bucle = bucle;
        
        estaReproduciendo = true;
    }

    public void detenerReproduccion()
    {
        this.estaReproduciendo = false;
        player.close();
    }
    
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

        } catch (Exception e)
        {
            System.out.println("" + e);
        }
    }

}