/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author Karen
 */
public abstract class Mob extends Sprite
{
    protected World world;
    protected Body body;

    public Mob(World world, TextureRegion region)
    {
        super(region);
        this.world = world;
    }
}
