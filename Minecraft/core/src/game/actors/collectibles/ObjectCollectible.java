package game.actors.collectibles;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import game.actors.groups.Actor;
import game.tools.Constant;

/**
 *
 * @author Karen
 */
public abstract class ObjectCollectible extends Sprite implements Actor
{

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    //Atributos de Box2D
    protected World world;
    protected Body body;

    //Atributos de control
    protected boolean isCollected;
    //</editor-fold>

    public ObjectCollectible(World world, Vector2 posicion)
    {
        this.world = world;

        setBounds(0, 0, 32 / Constant.PPM, 32 / Constant.PPM);

        //<editor-fold defaultstate="collapsed" desc="Definición de Body">
        BodyDef bodyD = new BodyDef();
        bodyD.position.set(posicion);
        bodyD.type = BodyDef.BodyType.DynamicBody;
        body = this.world.createBody(bodyD);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Definición de Fixture">
        FixtureDef fixtureD = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getHeight() / 2, getHeight() / 2);
        fixtureD.shape = shape;
        fixtureD.isSensor = true;
        fixtureD.filter.categoryBits = Constant.FOOD_BIT;
        fixtureD.filter.maskBits = Constant.PLAYER_BIT;
        body.createFixture(fixtureD).setUserData(this);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Definición de Sensor">
        fixtureD.isSensor = false;
        fixtureD.filter.categoryBits = Constant.FOOD_BIT;
        fixtureD.filter.maskBits = Constant.GROUND_BIT;
        body.createFixture(fixtureD).setUserData(this);
        //</editor-fold>

        isCollected = false;
    }

    public void setIsCollected(boolean isCollected)
    {
        this.isCollected = isCollected;
    }

    @Override
    public void act(float delta)
    {
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);

        if (isCollected)
        {
            delete();
        }
    }

    @Override
    public void draw(Batch batch)
    {
        if (!isCollected)
        {
            super.draw(batch);
        }
    }

    private void delete()
    {
        for (Fixture f : body.getFixtureList())
        {
            body.destroyFixture(f);
        }
    }
}
