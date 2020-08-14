package game.actors.monster;

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
public class Enderman extends MonsterMob
{

    public Enderman(GameScreen screen, float x, float y, boolean isBoss)
    {
        super
        (
            screen, 
            screen.getAtlas().findRegion("caminar_enderman"),
            x, 
            y, 
            81,     //Ancho
            192,    //Alto
            1,      //Velocidad
            75,     //Vida
            40,     //Puntos de ataque
            isBoss, 
            new Arm(Constant.BattleObjectEnum.SWORD, Constant.Material.DIAMOND), 
            "enderman"
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
        TextureRegion texture = screen.getAtlas().findRegion("caminar_enderman");
        TextureRegion[][] region = texture.split(81, 192);
        frames = new Array<>();

        for (TextureRegion[] regionF : region)
        {
            for (TextureRegion regionC : regionF)
            {
                frames.add(regionC);
            }
        }

        animation = new Animation(0.15f, frames);
        //</editor-fold>
        
        changeDirection();
    }

    @Override
    public void specialAttack(Player player)
    {
        body.setLinearVelocity(player.getBody().getPosition().x + 80 * player.getDirection(), 0);
    }
    
}
