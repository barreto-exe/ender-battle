package game.actors.monster;

import game.actors.Player;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.utils.Array;
import game.inventario.Protection;
import game.screens.GameScreen;
import game.tools.Constant;

/**
 *
 * @author Diego
 */
public class Skeleton extends MonsterMob
{

    float direction;

    public Skeleton(GameScreen screen, float x, float y, boolean isBoss)
    {
        super
        (
            screen, 
            screen.getAtlas().findRegion("caminar_esqueleto"),
            x, 
            y, 
            70,     //Ancho
            128,    //Alto
            1,      //Velocidad
            50,      //Vida
            40,     //Puntos de ataque
            isBoss, 
            new Protection(Constant.BattleObjectEnum.LEGGING, Constant.Material.DIAMOND), 
            "skeleton"
        );

        //<editor-fold defaultstate="collapsed" desc="Definición Sensores">
        EdgeShape sensor = new EdgeShape();
        fixtureD.shape = sensor;
        fixtureD.isSensor = true;
        fixtureD.filter.categoryBits = Constant.MOB_SENSOR_BIT;
        fixtureD.filter.maskBits = Constant.GROUND_BIT | Constant.MOB_BIT | Constant.PLAYER_BIT;

        //DERECHA
        sensor.set(getWidth() / 2, getHeight() / -2 + 0.5f, getWidth() / 2, getHeight() / 2 - 0.1f);
        body.createFixture(fixtureD).setUserData(this);

        //IZQUIERDA
        sensor.set(getWidth() / -2, getHeight() / -2 + 0.5f, getWidth() / -2, getHeight() / 2 - 0.1f);
        body.createFixture(fixtureD).setUserData(this);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Definición de Flecha">
        EdgeShape arrow = new EdgeShape();
        fixtureD.shape = arrow;
        fixtureD.isSensor = true;
        fixtureD.filter.categoryBits = Constant.ARROW_SENSOR_BIT;
        fixtureD.filter.maskBits = Constant.PLAYER_BIT;

        //Arrow por la derecha
        arrow.set(getWidth() / 2 + 4, getHeight() / -2 + 0.5f, getWidth() / 2 + 4, getHeight() / 2 - 0.1f);
        body.createFixture(fixtureD).setUserData(this);

        //Arrow por la izquierda
        arrow.set(getWidth() / -2 - 4, getHeight() / -2 + 0.5f, getWidth() / -2 - 4, getHeight() / 2 - 0.1f);
        body.createFixture(fixtureD).setUserData(this);

        direction = 1;
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Definición de Animación">
        TextureRegion texture = screen.getAtlas().findRegion("caminar_esqueleto");
        TextureRegion[][] region = texture.split(70, 128);   
        frames = new Array<>();

        //APLANANDO ARREGLO DE TEXTURES
        for (TextureRegion[] regionF : region)
        {
            for (TextureRegion regionC : regionF)
            {
                frames.add(regionC);
            }
        }

        animation = new Animation(0.14f, frames);    
        //</editor-fold>
    }

    @Override
    public void specialAttack(Player player)
    {
        if (direction * player.getDirection() == -1)
        {
            System.out.println("Flecha");
        }

    }

    @Override
    public void changeDirection()
    {
        super.changeDirection();
        direction = direction * -1;
        System.out.println("Direction: " + direction);
    }
    
}
