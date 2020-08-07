/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.actors.monster;

import game.actors.Player;
import game.actors.Mob;
import game.actors.farming.meats.ObjectCollectible;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import game.inventario.BattleObject;

/**
 *
 * @author Diego
 */
public abstract class MonsterMob extends Mob
{

    //Atributos de animacion
    protected float duration;
    protected Array<TextureRegion> frames;
    protected Animation animation;

    //Propiedades de los Mobs
    protected boolean isBoss;
    protected int dificulty;
    protected int attackPoints;
    protected String attackDescription;
    protected float speed;
    protected BattleObject prize; 
    protected TextureRegion prizeTexture;
    
    public MonsterMob(World world, TextureRegion region, float speed, float life, int attackPoints,boolean isBoss)
    {
        super(world, region, life);
        this.speed = speed;
        this.attackPoints = attackPoints;
        this.isBoss = isBoss;
        duration = 0;
        
        
        
//        MassData mass = new MassData();
//        mass.center.set(new Vector2(this.getWidth()/2,this.getHeight()/2));
//        mass.mass = 1;
//        this.body.setMassData(mass);
    }

    @Override
    public void changeDirection()
    {
        speed = speed * -1;

        for (TextureRegion frame : frames)
        {
            frame.flip(true, false);
        }
    }

    @Override
    public void act(float delta)
    {
        if (setToDie && !isDead)
        {
            delete();
            isDead = true;
        } 
        else if (!isDead)
        {
            body.setLinearVelocity(speed, body.getLinearVelocity().y);

            if (body.getLinearVelocity().y < 0)
            {
                body.applyForceToCenter(0, -10, true);
            }

            setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
            duration += delta;
            setRegion((TextureRegion) animation.getKeyFrame(duration, true));
        }
    }
        
    @Override
    public void draw (Batch batch)
    {
        if (life > 0)
        {
            super.draw(batch);
        }
    }
    
    public void attackPlayer(Player player)
    {
        if (attackOportunity(3))
        {
            player.toRecibeAttack(attackPoints);
            specialAttack(player);
        }
    }

    public void approachPlayer(Player player)
    {

    }

    public boolean attackOportunity(int oportunity)
    {
        int aux;
        aux = oportunity - 1;
        int chance = (int) (Math.random() * oportunity);
        System.out.println(chance);
        if (chance == aux)
        {
            return true;
        }
        return false;
    }

    public abstract void specialAttack(Player player);
    

}
