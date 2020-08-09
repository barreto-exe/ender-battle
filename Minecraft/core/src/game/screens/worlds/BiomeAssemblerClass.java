package game.screens.worlds;

import game.actors.farming.plants.Bush;
import game.actors.farming.plants.Plant;
import game.actors.farming.plants.Tree;
import game.actors.pacific.Chicken;
import game.actors.pacific.Cow;
import game.actors.pacific.PacificMob;
import game.actors.pacific.Pig;
import game.actors.pacific.Rabbit;
import game.actors.pacific.Sheep;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import game.tools.Constant;
import game.screens.GameScreen;

/**
 * @author Karen
 */
public class BiomeAssemblerClass
{
    //Mobs
    Array<PacificMob> pacificMobs;
    Array<Plant> farming;
        
    /**
     * Se encarga de ensamblar el bioma en la bantalla.
     * @param screen la pantalla en la que se construirá el bioma.
     */
    public BiomeAssemblerClass(GameScreen screen)
    {
        BodyDef def = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixture = new FixtureDef();
        Body body;
        
        //Creando fixtures bioma [1]
        for (MapObject object : screen.getMap().getLayers().get(1).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            
            def.type = BodyDef.BodyType.StaticBody;
            def.position.set((rectangle.getX() + rectangle.getWidth() / 2) / Constant.PPM, (rectangle.getY() + rectangle.getHeight() / 2) / Constant.PPM);
            body = screen.getWorld().createBody(def);
            
            shape.setAsBox(rectangle.getWidth() / 2 / Constant.PPM, rectangle.getHeight() / 2 / Constant.PPM);
            fixture.shape = shape;
            fixture.filter.categoryBits = Constant.GROUND_BIT;
            fixture.filter.maskBits = Constant.PLAYER_BIT | Constant.PLAYER_FEET_BIT | Constant.MOB_BIT | Constant.MOB_SENSOR_BIT | Constant.OBJECT_BIT | Constant.TREE_BIT;
            body.createFixture(fixture).setUserData("overfloor");
        }
        
        pacificMobs = new Array<>();
        PacificMob mob;

        //<editor-fold defaultstate="collapsed" desc="Ubicar Mobs Pacíficos">
        //Ubicando mobs pacíficos pequeños [2]
        for (MapObject object : screen.getMap().getLayers().get(2).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            mob = getLittleMob(screen, rectangle.getX(), rectangle.getY());

            if (mob != null)
            {
                pacificMobs.add(mob);
            }
        }

        //Ubicando mobs pacíficos grandes [3]
        for (MapObject object : screen.getMap().getLayers().get(3).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            mob = getBigMob(screen, rectangle.getX(), rectangle.getY());

            if (mob != null)
            {
                pacificMobs.add(mob);
            }
        }
        //</editor-fold>

        farming = new Array<>();
        Plant plant;
        
        //Ubicando arboles [4]
        for (MapObject object : screen.getMap().getLayers().get(4).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            
            plant = getTree(screen, rectangle.getX(), rectangle.getY());
            
            if (plant != null)
            {
                farming.add(plant);
            }
        }
        
        //Ubicando arbustos [5]
        for (MapObject object : screen.getMap().getLayers().get(5).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            
            plant = getBush(screen, rectangle.getX(), rectangle.getY());
            
            if (plant != null)
            {
                farming.add(plant);
            }
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public Array<PacificMob> getPacificMobs() {
        return pacificMobs;
    }
    public Array<Plant> getFarming() {
        return farming;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Randomize PacificMobs">
    private PacificMob getLittleMob(GameScreen screen, float x, float y)
    {
        int random = (int) (Math.random() * 2) + 1;
        
        switch (random)
        {
            case 1:
                return new Chicken(screen, x / Constant.PPM, y / Constant.PPM);
            case 2:
                return new Rabbit(screen, x / Constant.PPM, y / Constant.PPM);
            default:
                return null;
        }
    }
    
    private PacificMob getBigMob(GameScreen screen, float x, float y)
    {
        int random = (int) (Math.random() * 3) + 1;
        
        switch (random)
        {
            case 1:
                return new Sheep(screen, x / Constant.PPM, y / Constant.PPM);
            case 2:
                return new Pig(screen, x / Constant.PPM, y / Constant.PPM);
            case 3:
                return new Cow(screen, x / Constant.PPM, y / Constant.PPM);
            default:
                return null;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Randomize Plants">
    private Plant getTree(GameScreen screen, float x, float y)
    {
        int random = (int) (Math.random() * 2) + 1;
        
        switch (random)
        {
            case 1:
                return new Tree(screen, Constant.Farming.APPLE, x / Constant.PPM, y / Constant.PPM);
            case 2:
                return new Tree(screen, Constant.Farming.PEAR, x / Constant.PPM, y / Constant.PPM);
            default:
                return null;
        }
    }
    
    private Plant getBush(GameScreen screen, float x, float y)
    {
        int random = (int) (Math.random() * 4) + 1;
        
        switch (random)
        {
            case 1:
                return new Bush(screen, Constant.Farming.WATERMELON, x / Constant.PPM, y / Constant.PPM);
            case 2:
                return new Bush(screen, Constant.Farming.BERRY, x / Constant.PPM, y / Constant.PPM);
            case 3:
                return new Bush(screen, Constant.Farming.CARROT, x / Constant.PPM, y / Constant.PPM);
            case 4:
                return new Bush(screen, Constant.Farming.POTATO, x / Constant.PPM, y / Constant.PPM);
            default:
                return null;
        }
    }
    
    //</editor-fold>
}
