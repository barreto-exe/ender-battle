package game.actors.farming.meats;

import game.actors.groups.Actor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import game.tools.Constant;

/**
 *
 * @author Karen
 */
public class ObjectCollectible extends Sprite implements Actor
{
    //<editor-fold defaultstate="collapsed" desc="Atributos">
    //Atributos de Box2D
    protected World world;
    protected Body body;
    
    //Atributos de control
    protected boolean isCollected;
    
    //Atributos de información de la carne
    protected Constant.Farming type;
    //</editor-fold>

    public ObjectCollectible(Constant.Farming type, World world, TextureRegion region, Vector2 posicion) {
        super(region);
        this.world = world;
        this.type = type;
        
        //Colocar posición
        setBounds(0, 0, 32 / Constant.PPM, 32 / Constant.PPM);

        //<editor-fold defaultstate="collapsed" desc="Definición de Body">
        BodyDef bodyD = new BodyDef();
        bodyD.position.set(posicion);
        bodyD.type = BodyDef.BodyType.DynamicBody;
        body = this.world.createBody(bodyD);
        //</editor-fold>

        FixtureDef fixtureD = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getHeight() / 2, getHeight() / 2);
        fixtureD.shape = shape;
        fixtureD.isSensor = true;
        fixtureD.filter.categoryBits = Constant.FOOD_BIT;
        fixtureD.filter.maskBits = Constant.PLAYER_BIT;
        body.createFixture(fixtureD).setUserData(this);

        fixtureD.isSensor = false;
        fixtureD.filter.categoryBits = Constant.FOOD_BIT;
        fixtureD.filter.maskBits = Constant.GROUND_BIT;
        body.createFixture(fixtureD).setUserData(this);
        
        isCollected = false;
    }

    public void setIsCollected(boolean isCollected) {
        this.isCollected = isCollected;
    }

    public Constant.Farming getType() {
        return type;
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
        //world.destroyBody(body);
    }
}
