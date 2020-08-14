package game.actors.pacific;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import game.actors.collectibles.EsmeraldCollective;
import game.actors.collectibles.FoodCollectible;
import game.actors.collectibles.ObjectCollectible;
import game.screens.GameScreen;
import game.tools.Constant;

/**
 *
 * @author Karen
 */
public class Sheep extends PacificMob
{
    public Sheep(GameScreen screen, float x, float y)
    {
        super
        (
            screen, 
            (TextureRegion) screen.getAtlas().findRegion("oveja"),
            35,
            x,
            y, 
            267 / 3, 
            64, 
            "sheep"
        );
        textureMeat = screen.getAtlas().findRegion("ganado_carne");

        speed = 1.2f;
        type = Constant.Farming.BEEF;
    }

    //<editor-fold defaultstate="collapsed" desc="Definición de Vertices del Top">
    @Override
    protected Vector2[] getVerticesTop()
    {
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-42, 34).scl(1 / Constant.PPM);
        vertice[1] = new Vector2(42, 34).scl(1 / Constant.PPM);
        vertice[2] = new Vector2(-42, 10).scl(1 / Constant.PPM);
        vertice[3] = new Vector2(42, 10).scl(1 / Constant.PPM);
        return vertice;
    }
    //</editor-fold>  

    @Override
    protected void toDie()
    {
        toDieBigMob();
    }
}
