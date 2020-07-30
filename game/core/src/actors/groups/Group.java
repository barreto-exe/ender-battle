/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actors.groups;

import com.badlogic.gdx.graphics.g2d.Batch;
import java.util.ArrayList;

/**
 * Las instancias de esta clase pueden agrupar elementos
 * del tipo Actor y del tipo Group. Al llamar a un método del grupo,
 * se llama recursivamente a todos los métodos de los elementos pertenecientes.
 * 
 * @author luisb
 */
public class Group implements Actor
{
    private ArrayList<Actor> actors;
    
    /**
     * Objeto que puede agrupar elementos
     * del tipo Actor y del tipo Group. Al llamar a un método del grupo,
     * se llama recursivamente a todos los métodos de los elementos pertenecientes.
     */
    public Group()
    {
        this.actors = new ArrayList<>();
    }
    
    /**
     * Añade un actor al grupo.
     * @param actor es el objeto a añadir.
     */
    public void addActor(Actor actor)
    {
        actors.add(actor);
    }
    
    
    /**
     * Ejecuta los métodos act de todos los Actores o Grupos dentro del grupo.
     */
    @Override
    public void act(float delta)
    {
        for(Actor actor : actors)
        {
            actor.act(delta);
        }
    }

    /**
     * Ejecuta los métodos draw de todos los Actores o Grupos dentro del grupo.
     */
    @Override
    public void draw(Batch batch)
    {
        for(Actor actor : actors)
        {
            actor.draw(batch);
        }
    }
    
}
