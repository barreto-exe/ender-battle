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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import game.actors.monster.Creeper;
import game.actors.monster.Enderman;
import game.actors.monster.MonsterMob;
import game.actors.monster.Pigman;
import game.actors.monster.Skeleton;
import game.actors.monster.Spider;
import game.actors.monster.Zombie;
import game.tools.Constant;
import game.screens.GameScreen;
import game.tools.Constant.MapType;

/**
 * @author Karen
 */
public class BiomeAssemblerClass
{
    //Mobs
    private final Array<PacificMob> pacificMobs;
    private final Array<MonsterMob> monsterMobs;
    private final Array<Plant> farming;
    private final Vector2 villagerPosition;
    private final Vector2 playerPosition;
        
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
        for (MapObject object : screen.getMap().getLayers().get(2).getObjects().getByType(RectangleMapObject.class))
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
        PacificMob mobP;

        //<editor-fold defaultstate="collapsed" desc="Ubicar Mobs Pacíficos">
        //Ubicando mobs pacíficos pequeños [2]
        for (MapObject object : screen.getMap().getLayers().get(3).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            mobP = getLittleMob(screen, rectangle.getX(), rectangle.getY());

            if (mobP != null)
            {
                pacificMobs.add(mobP);
            }
        }

        //Ubicando mobs pacíficos grandes [3]
        for (MapObject object : screen.getMap().getLayers().get(4).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            mobP = getBigMob(screen, rectangle.getX(), rectangle.getY());

            if (mobP != null)
            {
                pacificMobs.add(mobP);
            }
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Ubicar Plantas">
        farming = new Array<>();
        Plant plant;
        
        //Ubicando arboles [4]
        for (MapObject object : screen.getMap().getLayers().get(5).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            
            plant = getTree(screen, rectangle.getX(), rectangle.getY());
            
            if (plant != null)
            {
                farming.add(plant);
            }
        }
        
        //Ubicando arbustos [5]
        for (MapObject object : screen.getMap().getLayers().get(6).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            
            plant = getBush(screen, rectangle.getX(), rectangle.getY());
            
            if (plant != null)
            {
                farming.add(plant);
            }
        }
        //</editor-fold>
        
        monsterMobs = new Array<>();
        MonsterMob mobM;
        
        //<editor-fold defaultstate="collapsed" desc="Ubicar Monstruos">
        for (MapObject object : screen.getMap().getLayers().get(7).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            mobM = getMonster(screen, rectangle.getX(), rectangle.getY());

            if (mobM != null)
            {
                monsterMobs.add(mobM);
            }
        }
        //</editor-fold>
        
        
        
        Rectangle rectangle = new Rectangle();        

        //<editor-fold defaultstate="collapsed" desc="Ubicar Aldeano">
        //Ubicación del Aldeano
        for (MapObject object : screen.getMap().getLayers().get(8).getObjects().getByType(RectangleMapObject.class))
        {
            rectangle = ((RectangleMapObject) object).getRectangle();
        }

        villagerPosition = new Vector2(rectangle.getX() / Constant.PPM, rectangle.getY() * 2 / Constant.PPM);
        //</editor-fold>
        
        //Ubicación del Jugador
        for (MapObject object : screen.getMap().getLayers().get(9).getObjects().getByType(RectangleMapObject.class))
        {
            rectangle = ((RectangleMapObject) object).getRectangle();
        }
        playerPosition = new Vector2(rectangle.getX() / Constant.PPM, rectangle.getY() / Constant.PPM);
    }

    //<editor-fold defaultstate="collapsed" desc="Getters">
    public Array<PacificMob> getPacificMobs()
    {
        return pacificMobs;
    }
    
    public Array<Plant> getPlants()
    {
        return farming;
    }
    
    public Array<MonsterMob> getMonsters()
    {
        return monsterMobs;
    }

    public Vector2 getVillagerPosition() {
        return villagerPosition;
    }

    public Vector2 getPlayerPosition() {
        return playerPosition;
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
    
    //<editor-fold defaultstate="collapsed" desc="Randomize Monsters">
    private MonsterMob getMonster(GameScreen screen, float x, float y)
    {
        //Si es el mundo del ender, sólo habra Endermen
        if(screen.getRoom().getMapNum() == 3)
        {
            return new Enderman(screen, x / Constant.PPM, y / Constant.PPM, false);
        }
        
        int random = (int) (Math.random() * 6) + 1;
        switch (random)
        {
            case 1:
                return new Creeper(screen, x / Constant.PPM, y / Constant.PPM, false);
            case 2:
                return new Enderman(screen, x / Constant.PPM, y / Constant.PPM, false);
            case 3:
                return new Pigman(screen, x / Constant.PPM, y / Constant.PPM, false);
            case 4:
                return new Spider(screen, x / Constant.PPM, y / Constant.PPM, false);
            case 5:
                return new Skeleton(screen, x / Constant.PPM, y / Constant.PPM, false);
            case 6:
                return new Zombie(screen, x / Constant.PPM, y / Constant.PPM, false);
            default:
                return null;
        }
    }
    //</editor-fold>
}
