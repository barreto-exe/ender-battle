package game.actors.farming.plants;

import game.screens.GameScreen;
import game.tools.Constant;

/**
 *
 * @author Karen
 */
public class Tree extends Plant
{
    public Tree(GameScreen screen, Constant.Farming type, float x, float y)
    {
        super(screen, type, x, y);

        int random = (int) (Math.random() * 3) + 1;

        if (setFrame(type, random))
        {
            setBounds(0, 0, width / Constant.PPM, height / Constant.PPM);
            createBody(x, y);
            setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        }
    }

    private boolean setFrame(Constant.Farming type, int random)
    {
        String fruit;

        switch (type)
        {
            case APPLE:
                fruit = "_manzana";
                fruitTexture = atlasFruit.findRegion("manzana");
                break;
            case PEAR:
                fruit = "_pera";
                fruitTexture = atlasFruit.findRegion("pera");
                break;
            default:
                return false;
        }

        String tree = "arbol";

        switch (random)
        {
            case 1:
                width = 210;
                height = 256;
                setRegion(atlasPlants.findRegion(tree + fruit + "1"));
                plantEmpty = atlasPlants.findRegion("arbol1");
                return true;
            case 2:
                width = 256;
                height = 256;
                setRegion(atlasPlants.findRegion(tree + fruit + "2"));
                plantEmpty = atlasPlants.findRegion("arbol2");
                return true;
            case 3:
                width = 191;
                height = 256;
                setRegion(atlasPlants.findRegion(tree + fruit + "3"));
                plantEmpty = atlasPlants.findRegion("arbol3");
                return true;
            default:
                width = 0;
                height = 0;
                return false;
        }
    }
}
