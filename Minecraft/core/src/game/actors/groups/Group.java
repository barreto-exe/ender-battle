package game.actors.groups;

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
     * Elimina un actor del grupo.
     * @param actor es el objeto a remover.
     */
    public void removeActor(Actor actor)
    {
        actors.remove(actor);
    }
    
    
    /**
     * Ejecuta los métodos act de todos los Actores o Grupos dentro del grupo.
     */
    @Override
    public void act(float delta)
    {
        for(int i = 0; i < actors.toArray().length; i++)
        {
            ((Actor)(actors.toArray()[i])).act(delta);
        }
    }

    /**
     * Ejecuta los métodos draw de todos los Actores o Grupos dentro del grupo.
     */
    @Override
    public void draw(Batch batch)
    {
        for(int i = 0; i < actors.toArray().length; i++)
        {
            ((Actor)(actors.toArray()[i])).draw(batch);
        }
    }
    
}
