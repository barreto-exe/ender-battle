package game.actors.pacific;

import game.actors.Mob;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import game.screens.GameScreen;
import game.tools.Constant;
import game.tools.Sonido;

/**
 *
 * @author Karen
 */
public abstract class PacificMob extends Mob
{
    //Atributos de animacion
    protected float duration;
    protected Array<TextureRegion> frames;
    protected Animation animation;
    protected TextureRegion textureMeat;
    protected TextureAtlas.AtlasRegion textureEsmereald;
    
    //Propiedades del MOB
    protected Constant.Farming type;

    /**
     * Representa a una criatura pacífica que no ataca al jugador. Al morir arroja comida y esmeraldas.
     * @param screen pantalla del juego.
     * @param texture textura del mob.
     * @param life vida del mob.
     * @param x ubicación horizontal en el mapa.
     * @param y ubicación vertical en el mapa.
     * @param width ancho del mob.
     * @param height alto del mob.
     * @param sonido es el que se reproduce al ser golpeado
     */
    public PacificMob(GameScreen screen, TextureRegion texture, float life, float x, float y, int width, int height, Sonido sonido)
    {
        super(screen.getWorld(), texture, life, sonido);
        actors = screen.getActors();
        duration = 0;
        textureEsmereald = screen.getAtlas().findRegion("esmeralda");
        
        setBounds(0, 0, width / Constant.PPM, height / Constant.PPM);

        //<editor-fold defaultstate="collapsed" desc="Definición de Body">
        BodyDef bodyD = new BodyDef();
        bodyD.position.set(x, y);
        bodyD.type = BodyDef.BodyType.DynamicBody;
        body = this.world.createBody(bodyD);
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Definición de Fixture">
        FixtureDef fixtureD = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() / 2, getHeight() / 2);
        fixtureD.shape = shape;
        fixtureD.filter.categoryBits = Constant.MOB_BIT;
        fixtureD.filter.maskBits = Constant.GROUND_BIT | Constant.MOB_BIT | Constant.PLAYER_BIT;
        body.createFixture(fixtureD).setUserData(this);
        
        //SENSORES DEL MOB
        EdgeShape sensor = new EdgeShape();
        fixtureD.shape = sensor;
        fixtureD.isSensor = true;
        fixtureD.filter.categoryBits = Constant.MOB_SENSOR_BIT;
        fixtureD.filter.maskBits = Constant.GROUND_BIT | Constant.MOB_SENSOR_BIT | Constant.PLAYER_BIT;
        
        //DERECHA
        sensor.set(getWidth() / 2, getHeight() / -2 + 0.1f, getWidth() / 2, getHeight() / 2 -0.1f);
        body.createFixture(fixtureD).setUserData(this);
        
        //IZQUIERDA
        sensor.set(getWidth() / -2, getHeight() / -2 + 0.1f, getWidth() / -2, getHeight() / 2 -0.1f);
        body.createFixture(fixtureD).setUserData(this);
        
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Definición de Top">
        PolygonShape top = new PolygonShape();
        top.set(getVerticesTop());
        
        fixtureD.isSensor = false;
        fixtureD.shape = top;
        fixtureD.restitution = 1f;
        fixtureD.filter.categoryBits = Constant.MOB_TOP_BIT;
        body.createFixture(fixtureD).setUserData(this);  
        
    //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Definición de Animación">
        TextureRegion[][] region = texture.split(width, height);   //DIVIDIENDO LA TEXTURE-REGION EN UN ARREGLO DE TEXTURES
        frames = new Array<>();

        //APLANANDO ARREGLO DE TEXTURES
        for (TextureRegion[] regionF : region)
        {
            for (TextureRegion regionC : regionF)
            {
                frames.add(regionC);
            }
        }
        
        animation = new Animation(0.15f, frames);    //CREANDO ANIMACION DE CAMINAR
                
        //</editor-fold> 
        
        //<editor-fold defaultstate="collapsed" desc="Definición de Masa">
        //Inicializar masa en 1kg para todos los Mobs
        //Esto es para el efecto del salto cuando los atacan
        MassData mass = new MassData();
        mass.center.set(new Vector2(this.getWidth()/2,this.getHeight()/2));
        mass.mass = 1;
        this.body.setMassData(mass);
        //</editor-fold>
    }

    protected abstract Vector2[] getVerticesTop();
    
    /**
     * Acciones que realiza el mob al morir. Usualmente 
     * es arrojar objetos al suelo.
     */
    protected abstract void toDie();
    
    @Override
    public void changeDirection(){
        speed = speed * -1;
        
        for (TextureRegion frame : frames)
        {
            frame.flip(true, false);
        }
    }
    
    @Override
    public void act(float delta)
    {
        if (setToDie && !isDead)
        {
            toDie();
            delete();
            isDead = true;
            //actors.removeActor(this);
        }
        else if (!isDead)
        {
            body.setLinearVelocity(speed, body.getLinearVelocity().y);

            if (body.getLinearVelocity().y < 0)
            {
                body.applyForceToCenter(0, -10, true);
            }

            setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
            duration += delta;
            setRegion((TextureRegion) animation.getKeyFrame(duration, true));
        }
    }
    
    @Override
    public void draw (Batch batch)
    {
        if (life > 0)
        {
            super.draw(batch);
        }
    }
}
