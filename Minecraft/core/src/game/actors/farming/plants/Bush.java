package game.actors.farming.plants;

import game.screens.GameScreen;
import game.tools.Constant;

/**
 *
 * @author Karen
 */
public class Bush extends Plant
{
    /**
     * Representa un arbusto en el juego.
     * @param screen es la pantalla donde se despliega.
     * @param type es el tipo de cosecha que suelta.
     * @param x posición horizontal.
     * @param y posición vertical.
     */
    public Bush(GameScreen screen, Constant.Farming type, float x, float y)
    {
        super(screen, type, x, y);
        width = 80;
        height = 125;
        
        setBounds(0, 0, width / Constant.PPM, height / Constant.PPM);
        createBody(x, y);
        setFrame(type);
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y- getHeight() / 2);
    }
    
    private boolean setFrame(Constant.Farming type)
    {
        String bush = "mata";
        
        switch (type)
        {
            case CARROT:
                setRegion(atlasPlants.findRegion(bush + "_zanahoria"));
                fruitTexture = atlasFruit.findRegion("zanahoria");
                break;
            case WATERMELON:
                setRegion(atlasPlants.findRegion(bush + "_sandia"));
                fruitTexture = atlasFruit.findRegion("sandia");
                break;
            case POTATO:
                setRegion(atlasPlants.findRegion(bush + "_papa"));
                fruitTexture = atlasFruit.findRegion("papa");
                break;
            case BERRY:
                setRegion(atlasPlants.findRegion(bush + "_bayas"));
                fruitTexture = atlasFruit.findRegion("bayas");
                break;
            default:
                return false;
        }
        
        plantEmpty = atlasPlants.findRegion(bush);
        return true;
    }
}
