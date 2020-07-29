/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actors.pacific;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import tools.Constant;

/**
 *
 * @author Karen
 */
public abstract class PacificMob extends Mob
{
    protected int cantAlimento;
    protected float duration;
    protected TextureRegion[] frames;
    protected Animation animation;
    protected float speed;

    public PacificMob(World world, TextureRegion region)
    {
        super(world, region);
        duration = 0;
        speed = Constant.SPEED_MOBS;
    }

}
