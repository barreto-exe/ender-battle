/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kdl.minecraft.basedatos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *Es una partida que un jugador puede hostear o a la que se puede unir.
 * 
 * @author luisb
 */
public class DBPartida implements Serializable
{
    private String nombre, descripcion;
    private ArrayList<DBUsuario> jugadores;
    private int limiteJugadores;
    private int id;

    /**
     * Crea un nuevo objeto de partida.
     * @param nombre el nombre con el que se identificará la partida.
     * @param descripcion algún mensaje que el jugador desee que se vea en el lobby.
     * @param creador es el jugador que creó la partida.
     * @param limiteJugadores es la cantidad de jugadores para la partida.
     */
    public DBPartida(String nombre, String descripcion, DBUsuario creador, int limiteJugadores)
    {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.jugadores = new ArrayList<DBUsuario>();
        this.jugadores.add(creador);
        this.limiteJugadores = limiteJugadores;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    
    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public int getLimiteJugadores()
    {
        return limiteJugadores;
    }

    public void setLimiteJugadores(int limiteJugadores)
    {
        this.limiteJugadores = limiteJugadores;
    }

    public DBUsuario getCreador()
    {
        return (DBUsuario)jugadores.toArray()[0];
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    
    
}
