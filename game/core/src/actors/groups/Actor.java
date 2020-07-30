/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actors.groups;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 *Es una interfaz que implementan todas aquellas
 * clases que refresentan elementos dinámicos del juego, 
 * como los jugadores y mobs. 
 * 
 * El fin es poder agrupar a todos los elementos
 * <code>Actor</code> dentro de un objeto <code>Group</code>. Luego, si se llama a una
 * llama a un método del grupo, se estaría llamando al método con el mismo nombre de 
 * todos los actores dentro de dicho grupo.
 * 
 * @author luisb
 */
public interface Actor
{
    /**
     * Actualiza una nueva acción del Actor, dependiendo
     * de la entrada del usuario o de los sucesos ocurridos en el Box2d.
     * Es un método que se debe llamar en cada render de la pantalla en la que
     * se encuentra el player. 
     * @param delta tiempo transcurrido desde la última vez que se ejecutó
     * la función.
     */
    public void act(float delta);
    
    /**
     * Dibuja al actor en un batch dado.
     * Es un método que se debe llamar en cada render de la pantalla en la que
     * se encuentra el player.
     * @param batch es el batch que se usará para dibujar al actor.
     */
    public void draw(Batch batch);
}
