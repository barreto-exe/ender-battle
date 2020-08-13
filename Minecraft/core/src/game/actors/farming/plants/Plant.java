package game.actors.farming.plants;

import game.actors.Player;
import game.actors.collectibles.FoodCollectible;
import game.actors.groups.Actor;
import game.actors.groups.Group;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import game.screens.GameScreen;
import game.tools.Constant;

/**
 *
 * @author Karen
 */
public abstract class Plant extends Sprite implements Actor
{

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    protected TextureAtlas atlasPlants;
    protected TextureAtlas atlasFruit;
    protected Group actors;

    //Atributos de Box2D
    protected World world;
    protected Body body;
    protected int width;
    protected int height;

    //Atributos de control
    protected boolean isEmpty;
    protected boolean setToCrop;

    //Atributos de información del árbol
    protected float life;
    protected TextureAtlas.AtlasRegion plantEmpty;
    protected Constant.Farming type;
    protected FoodCollectible[] fruits;
    protected TextureAtlas.AtlasRegion fruitTexture;
    
    private GameScreen screen;
    //</editor-fold>

    public Plant(GameScreen screen, Constant.Farming type, float x, float y)
    {
        world = screen.getWorld();
        atlasPlants = screen.getPlantas();
        atlasFruit = screen.getAtlas();
        actors = screen.getActors();
        this.screen = screen;
        isEmpty = setToCrop = false;
        life = 75;
        this.type = type;
    }

    public void setIsEmpty(boolean isEmpty)
    {
        this.isEmpty = isEmpty;
    }

    
    @Override
    public void act(float delta)
    {
        if (setToCrop && !isEmpty)
        {
            setRegion(plantEmpty);

            fruits = new FoodCollectible[5];
            fruits[0] = new FoodCollectible(type, world, fruitTexture, new Vector2(body.getPosition().x - getHeight() / 2, body.getPosition().y));
            fruits[1] = new FoodCollectible(type, world, fruitTexture, new Vector2(body.getPosition().x - getHeight() / 4, body.getPosition().y));
            fruits[2] = new FoodCollectible(type, world, fruitTexture, new Vector2(body.getPosition().x + getHeight() / 4, body.getPosition().y));
            fruits[3] = new FoodCollectible(type, world, fruitTexture, new Vector2(body.getPosition().x + getHeight() / 2, body.getPosition().y));
            fruits[4] = new FoodCollectible(type, world, fruitTexture, new Vector2(body.getPosition().x, body.getPosition().y));

            for (FoodCollectible fruit : fruits)
            {
                actors.addActor(fruit);
            }

            delete();
            isEmpty = true;
        }

    }

    private void delete()
    {
        for (Fixture f : body.getFixtureList())
        {
            body.destroyFixture(f);
        }
        world.destroyBody(body);
    }

    @Override
    public void draw(Batch batch)
    {
        super.draw(batch);
    }

    protected void createBody(float x, float y)
    {
        //<editor-fold defaultstate="collapsed" desc="Definición de Body">
        BodyDef bodyD = new BodyDef();
        bodyD.position.set(x, y + (height / 2 / Constant.PPM));
        body = world.createBody(bodyD);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Definición de Fixture">
        FixtureDef fixtureD = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() / 2, getHeight() / 2);
        fixtureD.shape = shape;
        fixtureD.isSensor = true;
        fixtureD.filter.categoryBits = Constant.TREE_BIT;
        fixtureD.filter.maskBits = Constant.PLAYER_BIT;
        body.createFixture(fixtureD).setUserData(this);

        fixtureD.isSensor = false;
        fixtureD.filter.categoryBits = Constant.TREE_BIT;
        fixtureD.filter.maskBits = Constant.GROUND_BIT;
        body.createFixture(fixtureD).setUserData(this);

        //</editor-fold> 
    }

    public void toRecibeAttack(Player player, float hit)
    {
        //ver si el hit también depende del arma del player
        life -= hit;

        if (life <= 0)
        {
            life = 0;
            setToCrop = true;

            //Mostrar mensaje conservacionista por 5 segundos
            screen.setMostrarMensajeConservacion(true);
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        Thread.sleep(5000);
                        screen.setMostrarMensajeConservacion(false);
                    }
                    catch (InterruptedException ex)
                    {
                    }
                }
                
            }).start();
        }
    }

}
