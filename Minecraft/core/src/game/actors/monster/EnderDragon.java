package game.actors.monster;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.utils.Array;
import game.actors.Player;
import game.inventario.Arm;
import game.screens.GameScreen;
import game.tools.Constant;
import game.tools.Constant.*;
import static game.tools.Sonido.soundManager;

/**
 *
 * @author luisb
 */
public class EnderDragon extends MonsterMob
{
    public EnderDragon(GameScreen screen, float x, float y)
    {
        super
        (
            screen,
            screen.getAtlas().findRegion("enderdragon"),
            x,
            y,
            160,    //Ancho
            123,    //Alto
            1,      //Velocidad
            90,     //Vida
            60,     //Ataque
            true,   //Es jefe
            
            /**
             * Sí, el enderdragon arroja una simple pala de madera cuando muere. 
             * Lo hice así para dejar una moraleja. Para demostrar que obtener un logro tan grande
             * no necesariamente significa que la retribución va a ser tan grata o valiosa.
             * La enseñanza que te deja es que debes hacer las cosas por la pasión y 
             * no por lo que puedas obtener a cambio.
             */
            new Arm(BattleObjectEnum.SHOVEL, Material.WOOD), 
            
            "enderdragon"   //Sonido
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
        
        //<editor-fold defaultstate="collapsed" desc="Definición de animación">
        TextureRegion texture = screen.getAtlas().findRegion("enderdragon");
        TextureRegion[][] region = texture.split(160, 123);
        frames = new Array<>();

        for (TextureRegion[] regionF : region)
        {
            for (TextureRegion regionC : regionF)
            {
                frames.add(regionC);
            }
        }

        animation = new Animation(0.13f, frames);
        //</editor-fold>
    }

    private boolean sonando = false;
    
    @Override
    public void act(float delta)
    {
        super.act(delta);
        
        if(sonando)
            return;
        
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    sonando = true;
                    Thread.sleep(2000);
                    soundManager.get("sonidos/mobs/enderdragon_alas.ogg", Sound.class).play();
                    sonando = false;
                }
                catch (InterruptedException ex)
                {
                }
            }
        }).start();
    }
    
    

    @Override
    public void specialAttack(Player player)
    {
    }
}
