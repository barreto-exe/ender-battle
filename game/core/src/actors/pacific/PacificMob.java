/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actors.pacific;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author Karen
 */
public abstract class PacificMob extends Mob
{
    protected int cantAlimento;
    protected float duration;
    protected Array<TextureRegion> frames;
    protected Animation animation;
    protected float speed;

    public PacificMob(World world, TextureRegion region, float speed, float life)
    {
        super(world, region, life);
        duration = 0;
        this.speed = speed;
    }

    public void changeDirection(){
        speed = speed * -1;
        
        for (TextureRegion frame : frames)
        {
            frame.flip(true, false);
        }
    }
    
    @Override
    public void act(float delta)
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
