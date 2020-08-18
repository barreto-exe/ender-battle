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
