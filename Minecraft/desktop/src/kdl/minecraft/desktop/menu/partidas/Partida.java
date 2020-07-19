/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kdl.minecraft.desktop.menu.partidas;

import java.util.ArrayList;

/**
 *Es una partida que un jugador puede hostear o a la que se puede unir.
 * 
 * @author luisb
 */
public class Partida
{
    private String nombre, descripcion;
    private ArrayList<Jugador> jugadores;
    private int limiteJugadores;

    /**
     * Crea un nuevo objeto de partida.
     * @param nombre el nombre con el que se identificará la partida.
     * @param descripcion algún mensaje que el jugador desee que se vea en el lobby.
     * @param creador es el jugador que creó la partida.
     * @param limiteJugadores es la cantidad de jugadores para la partida.
     */
    public Partida(String nombre, String descripcion, Jugador creador, int limiteJugadores)
    {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.jugadores = new ArrayList<Jugador>();
        this.jugadores.add(creador);
        this.limiteJugadores = limiteJugadores;
    }
    
    
    
}
