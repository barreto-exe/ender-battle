package game.actors;

import game.actors.Player;
import game.actors.groups.Actor;
import game.actors.groups.Group;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import game.tools.Constant;

public abstract class Mob extends Sprite implements Actor
{

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    //Atributos de Box2D
    protected Body body;
    protected World world;
    protected Group actors;

    //Propiedades del MOB
    protected boolean isDead;
    protected boolean setToDie;
    protected float life;
    protected float speed;
    //</editor-fold>

    public Mob(World world, TextureRegion region, float life)
    {
        super(region);
        this.world = world;
        this.life = life;
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

    public void toRecibeAttack(Player player, float hit)
    {

        life -= hit;
        
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
        body.applyLinearImpulse(0, 8f, body.getWorldCenter().x, body.getWorldCenter().y, true);

        if (life <= 0)
        {
            life = 0;
            setToDie = true;
        }
    }

    public abstract void changeDirection();

    protected void delete()
    {
        for (Fixture f : body.getFixtureList())
        {
            body.destroyFixture(f);
        }
        world.destroyBody(body);
    }
}
