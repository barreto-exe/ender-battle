/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kdl.minecraft.comunicacion;

import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Entidad para empaquetar los datos de un jugador que participa en partidas.
 *
 * @author luisb
 */
public class Jugador implements Serializable
{

    private String nombre;
    private String ip;
    private int personajeSeleccionado;

    /**
     * Entidad para empaquetar los datos de un jugador que participa en
     * partidas.
     *
     * @param nombre es el nick del jugador.
     * @param personajeSeleccionado el número del skin seleccionado por el
     * jugador. Es un rango del 0 al 5.
     */
    public Jugador(String nombre, int personajeSeleccionado)
    {
        this.nombre = nombre;
        this.personajeSeleccionado = personajeSeleccionado;
        
        try
        {
            //Consulta ip local
            this.ip = java.net.InetAddress.getLocalHost() + "";
        } 
        catch (UnknownHostException ex)
        {
            System.out.println(ex.getMessage());
            
            //Asigna vacío si no es posible consultar
            this.ip = "";
        }
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public int getPersonajeSeleccionado()
    {
        return personajeSeleccionado;
    }

    public void setPersonajeSeleccionado(int personajeSeleccionado)
    {
        this.personajeSeleccionado = personajeSeleccionado;
    }

}
