package game.actors.monster;

import game.actors.Mob;
import game.actors.Player;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.utils.Array;
import game.inventario.Arm;
import game.screens.GameScreen;
import game.tools.Constant;

/**
 *
 * @author Diego
 */
public class Creeper extends MonsterMob
{

    private Array<TextureRegion> explosionFrames;
    private Animation explosionAnimation;

    public Creeper(GameScreen screen, float x, float y, boolean isBoss)
    {
        super
        (
            screen, 
            screen.getAtlas().findRegion("caminar_creeper"),
            x, 
            y, 
            55,     //Ancho
            128,    //Alto
            1.5f,   //Velocidad
            50,      //Vida
            40,     //Puntos de ataque
            isBoss, 
            new Arm(Constant.BattleObjectEnum.AX, Constant.Material.DIAMOND), 
            "creeper"
        );
        //<editor-fold defaultstate="collapsed" desc="Definición de Sensores">
        EdgeShape sensor = new EdgeShape();
        fixtureD.shape = sensor;
        fixtureD.isSensor = true;
        fixtureD.filter.categoryBits = Constant.MOB_SENSOR_BIT;
        fixtureD.filter.maskBits = Constant.GROUND_BIT | Constant.MOB_BIT | Constant.PLAYER_BIT;

        //DERECHA
        sensor.set(getWidth() / 2, getHeight() / -2 + 0.1f, getWidth() / 2, getHeight() / 2 - 0.1f);
        body.createFixture(fixtureD).setUserData(this);

        //IZQUIERDA
        sensor.set(getWidth() / -2, getHeight() / -2 + 0.1f, getWidth() / -2, getHeight() / 2 - 0.1f);
        body.createFixture(fixtureD).setUserData(this);
        //</editor-fold>    

        //<editor-fold defaultstate="collapsed" desc="Definición de Animación">
        TextureRegion texture = screen.getAtlas().findRegion("caminar_creeper");
        TextureRegion[][] region = texture.split(55, 128);   
        frames = new Array<>();

        //APLANANDO ARREGLO DE TEXTURES
        for (TextureRegion[] regionF : region)
        {
            for (TextureRegion regionC : regionF)
            {
                frames.add(regionC);
            }
        }

        animation = new Animation(0.18f, frames);    

        
        texture = screen.getAtlas().findRegion("explotar_creeper");
        region = texture.split(55, 128);
        explosionFrames = new Array<>();

        //APLANANDO ARREGLO DE TEXTURES
        for (TextureRegion[] regionF : region)
        {
            for (TextureRegion regionC : regionF)
            {
                explosionFrames.add(regionC);
            }
        }

        explosionAnimation = new Animation(0.18f, explosionFrames);
        //</editor-fold>    
    }

    @Override
    public void specialAttack(Player player)
    {
        final Creeper creeper = this;
        final Mob mob = player.getEnemy();
        final float segundos = 3;

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(((long) (segundos)) * 1000);
                }
                catch (InterruptedException ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
        }).start();
        if (player.getEnemy().equals(this))
        {
            System.out.println("Exploto");
        }
        else
        {
            System.out.println("No exploto");
        }
    }

}
