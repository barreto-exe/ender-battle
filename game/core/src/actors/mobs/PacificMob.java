/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actors.mobs;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author Karen
 */
public abstract class PacificMob extends Mob{
    protected int cantAlimento;
    protected float duration;
    protected TextureRegion[] frames;
    protected TextureRegion texture;
    protected Animation animation;
    
    public PacificMob(World world) {
        super(world);
        duration = 0;
    }
    
}
