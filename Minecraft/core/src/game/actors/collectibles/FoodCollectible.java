package game.actors.collectibles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import game.tools.Constant;

/**
 *
 * @author Karen
 */
public class FoodCollectible extends ObjectCollectible
{
    //<editor-fold defaultstate="collapsed" desc="Atributos">
    //Atributos de información del alimento
    protected Constant.Farming type;
    //</editor-fold>

    public FoodCollectible(Constant.Farming type, World world, TextureRegion region, Vector2 posicion) {
        super(world,posicion);
        this.type = type;
        setRegion(region);
    }

    public Constant.Farming getType() {
        return type;
    }
}
