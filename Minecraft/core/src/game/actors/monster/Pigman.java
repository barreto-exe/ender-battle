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
public class Pigman extends MonsterMob
{

    public Pigman(GameScreen screen, int x, int y, boolean isBoss)
    {
        super
        (
            screen, 
            screen.getAtlas().findRegion("caminar_pigman"),
            x, 
            y, 
            64,     //Ancho
            128,    //Alto
            0.8f,   //Velocidad
            8,      //Vida
            10,     //Puntos de ataque
            isBoss, 
            new Protection(Constant.BattleObject.HELMET, Constant.Material.DIAMOND), 
            Sonido.PIGMAN
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
        TextureRegion texture = screen.getAtlas().findRegion("caminar_pigman");
        TextureRegion[][] region = texture.split(256 / 4, 128);   //DIVIDIENDO LA TEXTURE-REGION EN UN ARREGLO DE TEXTURES
        frames = new Array<>();

        //APLANANDO ARREGLO DE TEXTURES
        for (TextureRegion[] regionF : region)
        {
            for (TextureRegion regionC : regionF)
            {
                frames.add(regionC);
            }
        }

        animation = new Animation(0.18f, frames);    //CREANDO ANIMACION DE CAMINAR
        //</editor-fold>
    }

    @Override
    public void specialAttack(Player player)
    {
        if (attackOportunity(3))
        {
            player.setCondition(PlayerCondition.BURNED, 5);
            System.out.println("Is burned");
            return;
        }
        System.out.println("Failed");
    }

    @Override
    protected void toDie() {
        if (isBoss)
        {
            System.out.println("suelta un arma");
        }
        else
        {
            super.toDie();
        }
    }

}
