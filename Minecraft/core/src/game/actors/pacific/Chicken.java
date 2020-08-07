package game.actors.pacific;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import game.screens.GameScreen;
import game.tools.Constant;

/**
 *
 * @author Karen
 */
public class Chicken extends PacificMob 
{
    public Chicken(GameScreen screen, float x, float y)
    {
        super(screen, (TextureRegion)screen.getAtlas().findRegion("chicken"), 5, x, y, 96 / 3, 25);
        textureMeat = screen.getAtlas().findRegion("pollo_carne");
        
        speed = 1.2f;
        type = Constant.Farming.CHICKEN;
    }

    //<editor-fold defaultstate="collapsed" desc="Definición de Vertices del Top">
    @Override
    protected Vector2[] getVerticesTop()
    {
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-12, 14).scl(1 / Constant.PPM);
        vertice[1] = new Vector2(12, 14).scl(1 / Constant.PPM);
        vertice[2] = new Vector2(-12, 3).scl(1 / Constant.PPM);
        vertice[3] = new Vector2(12, 3).scl(1 / Constant.PPM);
        return vertice;
    }
    //</editor-fold>    
}
