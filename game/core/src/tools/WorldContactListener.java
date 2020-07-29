/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import actors.Player;
import actors.pacific.PacificMob;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 *
 * @author Karen
 */
public class WorldContactListener implements ContactListener
{
    private final Player player;

    public WorldContactListener(Player player)
    {
        this.player = player;
    }

    @Override
    public void beginContact(Contact contact)
    {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();
        
        int colision = a.getFilterData().categoryBits | b.getFilterData().categoryBits;
        
        switch (colision)
        {
            case Constant.PLAYER_FEET_BIT | Constant.GROUND_BIT:
                player.setIsJumping(false);
                break;
            case Constant.MOB_SENSOR_BIT | Constant.GROUND_BIT:
                if (a.getFilterData().categoryBits == Constant.MOB_SENSOR_BIT)
                {
                    ((PacificMob) a.getUserData()).changeDirection();
                }
                else 
                {
                    ((PacificMob) b.getUserData()).changeDirection();
                }
                break;
        }
    }

    @Override
    public void endContact(Contact contact)
    {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold)
    {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse)
    {
    }

}
