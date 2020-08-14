package game.actors;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import game.actors.groups.Actor;
import game.tools.Constant;

/**
 *
 * @author Karen
 */
public class Villager extends Sprite implements Actor
{
    //<editor-fold defaultstate="collapsed" desc="Atributos">
    //Atributos de Box2D
    protected World world;
    protected Body body;
    
    //Atributos de animacion
    protected float duration;
    protected Array<TextureRegion> frames;
    protected Animation animation;
    //</editor-fold>

    public Villager(World world, TextureRegion spriteSheet, float x, float y)
    {
        super(spriteSheet);
        this.world = world;
        
        //<editor-fold defaultstate="collapsed" desc="Definición de Animación">
        TextureRegion[][] region = spriteSheet.split(64, 128);
        frames = new Array<>();

        //APLANANDO ARREGLO DE TEXTURES
        for (TextureRegion[] regionF : region)
        {
            for (TextureRegion regionC : regionF)
            {
                frames.add(regionC);
            }
        }

        animation = new Animation(0.2f, frames);
        setBounds(0, 0, 64 / Constant.PPM, 128 / Constant.PPM);
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Definición de Body">
        BodyDef bodyD = new BodyDef();
        bodyD.position.set(x, y);
        body = this.world.createBody(bodyD);
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Definición de Fixture">
        FixtureDef fixtureD = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() / 2, getHeight() / 2);
        fixtureD.shape = shape;
        fixtureD.filter.categoryBits = Constant.VILLAGER_BIT;
        fixtureD.filter.maskBits = Constant.GROUND_BIT ;
        body.createFixture(fixtureD).setUserData(this);
        
        fixtureD.isSensor = true;
        fixtureD.filter.categoryBits = Constant.VILLAGER_BIT;
        fixtureD.filter.maskBits = Constant.PLAYER_BIT;
        body.createFixture(fixtureD).setUserData(this);
        //</editor-fold>
        
        setPosition(body.getPosition().x - 32 / Constant.PPM, body.getPosition().y - 66 / Constant.PPM);
        duration = 0;
    }
    
    @Override
    public void act(float delta)
    {
        duration += delta;
        setRegion((TextureRegion) animation.getKeyFrame(duration, true));
    }

    @Override
    public void draw(Batch batch)
    {
        super.draw(batch);
    }
}
