/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actors.pacific;

import actors.groups.Actor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author Karen
 */
public abstract class Mob extends Sprite implements Actor
{
    //Atributos de Box2D
    protected World world;
    protected Body body;
    
    //Propiedades del MOB
    protected float life;
    

    public Mob(World world, TextureRegion region, float life)
    {
        super(region);
        this.world = world;
        this.life = life;
    }
    
    public void toRecibeAttack(float hit)
    {
        life -= hit;
        
        if (life <= 0)
        {
            life = 0;
            System.out.println("el mob ha muerto");
        }
    }
}
