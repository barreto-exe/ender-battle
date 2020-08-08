/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.actors.collectibles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author Karen
 */
public class EsmeraldCollective extends ObjectCollectible
{
    public EsmeraldCollective(TextureRegion region, World world, Vector2 posicion)
    {
        super(world, posicion);
        
        setRegion(region);
    }
}
