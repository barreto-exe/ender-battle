/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actors.pacific;

import actors.Player;
import actors.groups.Actor;
import com.badlogic.gdx.graphics.Color;
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

    public float getLife() {
        return life;
    }
    
    public void toRecibeAttack(Player player, float hit)
    {
        life -= hit;
        
        //El mob que será herido
        final Mob mob = this;
        
        //Cantidad de segudos que permanece coloreado de rojo
        final float segundos = 1;
        
        //Colorear de rojo por haber sido herido
        mob.setColor(Color.CORAL);
        
        //Lanzar thread que espera un segundo y lo colorea a la normalidad
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(((long) (segundos)) * 1000);
                    mob.setColor(Color.WHITE);
                } 
                catch (InterruptedException ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
        }).start();
        
        if (life <= 0)
        {
            life = 0;
            System.out.println("el mob ha muerto");
        }
    }
}
