/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actors.mobs;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author Karen
 */
public abstract class Mob extends Actor{
    protected World world;
    protected Body body;
    
    public Mob(World world) {
        this.world = world;
    }
    
    protected abstract void defineMob(int x, int y);
}
