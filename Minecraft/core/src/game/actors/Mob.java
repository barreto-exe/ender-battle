package game.actors;

import com.badlogic.gdx.audio.Sound;
import game.actors.groups.Actor;
import game.actors.groups.Group;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import comunicacion.ProgresoJugador;
import game.actors.monster.Creeper;
import game.actors.monster.EnderDragon;
import game.actors.monster.MonsterMob;
import game.actors.pacific.PacificMob;
import game.screens.GameScreen;
import game.screens.worlds.Room;
import game.tools.Constant;
import game.tools.Sonido;
import static game.tools.Sonido.soundManager;

public abstract class Mob extends Sprite implements Actor
{

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    //Atributos de Box2D
    protected Body body;
    protected World world;
    protected Group actors;
    protected GameScreen screen;
    
    //Atributos de animacion
    protected float duration;
    protected Array<TextureRegion> frames;
    protected Animation animation;
    protected TextureAtlas.AtlasRegion textureEsmereald;

    //Propiedades del MOB
    protected boolean isDead;
    protected boolean setToDie;
    protected float life;
    protected float speed;
    private final String sonido;
    private int contadorSonidos;
    
    //Propiedades del juego
    private ProgresoJugador progreso;
    //</editor-fold>

    public Mob(GameScreen screen, TextureRegion region, float life, String sonido)
    {
        super(region);
        world = screen.getWorld();
        progreso = screen.getGame().getProgreso();
        textureEsmereald = screen.getAtlas().findRegion("esmeralda");
        actors = screen.getActors();
        
        this.life = life;
        this.screen = screen;
        this.sonido = sonido;
        this.contadorSonidos = 0;
        duration = 0;
        
        isDead = setToDie = false;
    }

    public float getLife()
    {
        return life;
    }

    public Body getBody()
    {
        return body;
    }

    
    /**
     * Recibe un ataque del jugador.
     * @param player el jugador que ataca al mob.
     * @param hit cantidad de vida que se le restará al mob.
     */
    public void toRecibeAttack(Player player, float hit)
    {
        life -= hit;
        
        System.out.println("Vida: " + life);
        
        if(contadorSonidos == 0)
        {
            Sonido.soundManager.get("sonidos/mobs/"+sonido+".ogg", Sound.class).play(Sonido.volumen);
        }
        
        contadorSonidos++;
        if(contadorSonidos > 3)
        {
            contadorSonidos = 0;
        }
        
        //El mob que será herido
        final Mob mob = this;

        //Cantidad de segudos que permanece coloreado de rojo
        final float segundos = 1;

        //Colorear de rojo por haber sido herido
        mob.setColor(Color.CORAL);

        //Lanzar thread que espera un segundo y lo colorea a la normalidad
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(((long) (segundos)) * 1000);
                    mob.setColor(Color.WHITE);
                } catch (InterruptedException ex)
                {
                    System.out.println(ex.getMessage());
                }
            }

        }).start();

        //Saltar por el golpe
        body.applyLinearImpulse(0, 1, body.getWorldCenter().x, body.getWorldCenter().y, true);

        if (life <= 0)
        {
            life = 0;
            setToDie = true;
            
            //Contar las muertes en el progreso
            if(this instanceof PacificMob)
            {
                progreso.setAnimalesMatados(progreso.getAnimalesMatados()+1);
            }
            else if(this instanceof MonsterMob)
            {
                MonsterMob monster = (MonsterMob) this;
                if(monster.isIsBoss())
                {
                    progreso.setJefesMatados(progreso.getJefesMatados()+1);
                }
                else
                {
                    progreso.setMonstruosMatados(progreso.getMonstruosMatados()+1);
                }
                
                if(this instanceof Creeper)
                    soundManager.get("sonidos/mobs/explosion.ogg", Sound.class).play(Sonido.volumen);
                
                if(this instanceof EnderDragon)
                {
                    soundManager.get("sonidos/mobs/enderdragon_die.ogg", Sound.class).play(Sonido.volumen);
                }
            }
        }
    }
    
    /**
     * Acciones que realiza el mob al morir. Usualmente 
     * es arrojar objetos al suelo.
     */
    protected abstract void toDie();
    
    @Override
    public void act(float delta)
    {
        if (setToDie && !isDead)
        {
            toDie();
            delete();
            isDead = true;
            actors.removeActor(this);
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

    /**
     * Cambiar dirección del mob.
     */
    public void changeDirection()
    {
        speed = speed * -1;

        for (TextureRegion frame : frames)
        {
            frame.flip(true, false);
        }
    }

    /**
     * Borra al mob del mapa.
     */
    protected void delete()
    {
        for (Fixture f : body.getFixtureList())
        {
            body.destroyFixture(f);
        }
        world.destroyBody(body);
    }
}
