package game.actors.monster;

import game.actors.Player;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.utils.Array;
import game.inventario.Protection;
import game.screens.GameScreen;
import game.tools.Constant;
import game.tools.Constant.PlayerCondition;
import game.tools.Sonido;

/**
 *
 * @author Diego
 */
public class Zombie extends MonsterMob
{
    public Zombie(GameScreen screen, int x, int y, boolean isBoss)
    {
        super
        (
            screen, 
            screen.getAtlas().findRegion("zombie"),
            x, 
            y, 
            128, //Ancho
            128, //Alto
            1,   //velocidad
            50,   //Vida
            30,  //Puntos de ataque
            isBoss, 
            new Protection(Constant.BattleObjectEnum.BOOTS, Constant.Material.DIAMOND), 
            "zombie"
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
        TextureRegion texture = screen.getAtlas().findRegion("zombie");
        TextureRegion[][] region = texture.split(128, 128);
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
        player.setCondition(PlayerCondition.POISONED, 5);
    }

    @Override
    protected void toDie() {
        if (isBoss)
        {
            toDropPrize();
        }
        else
        {
            super.toDie();
        }
    }
    
}
