/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import actors.Player;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 *
 * @author Karen
 */
public class WorldContactListener implements ContactListener{
    private final Player player;

    public WorldContactListener(Player player) {
        this.player = player;
    }
    
    
    //FUNCIÓN QUE INDICA SI DOS FIXTURES ESTÁN EN CONTACTO
    private boolean inContact(Contact contact, Object a, Object b)
    {
        return (contact.getFixtureA().getUserData().equals(a) && contact.getFixtureB().getUserData().equals(b)
        || contact.getFixtureA().getUserData().equals(b) && contact.getFixtureB().getUserData().equals(a));
    }

    @Override
    public void beginContact(Contact contact) {
        boolean tocandoBloque = inContact(contact, "feet", "overfloor") || inContact(contact, "player", "overfloor");

                if (tocandoBloque || player.isJumping())
                {
                    player.setIsJumping(false);
                }
    }

    @Override
    public void endContact(Contact contact) {
        boolean tocandoSueloPies = inContact(contact, "feet", "overfloor");
        boolean caminando = player.isWalking();

        if (caminando && tocandoSueloPies)
        {
            player.setIsJumping(true);
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {    }
    
}
