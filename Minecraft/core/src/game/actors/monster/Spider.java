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
public class Spider extends MonsterMob
{

    public Spider(GameScreen screen, int x, int y, boolean isBoss)
    {
        super
        (
            screen, 
            screen.getAtlas().findRegion("spider"),
            x, 
            y, 
            60,     //Ancho
            40,     //Alto
            1.1f,   //velocidad
            8,      //Vida
            10,     //Puntos de ataque
            isBoss, 
            new Protection(Constant.BattleObject.SHIRTFRONT, Constant.Material.DIAMOND), 
            Sonido.SPIDER
        );
        
        //<editor-fold defaultstate="collapsed" desc="Definición de Sensores">
        //SENSORES DEL MOB
        EdgeShape sensor = new EdgeShape();
        fixtureD.shape = sensor;
        fixtureD.isSensor = true;
        fixtureD.filter.categoryBits = Constant.MOB_SENSOR_BIT;
        fixtureD.filter.maskBits = Constant.GROUND_BIT | Constant.MOB_BIT | Constant.PLAYER_BIT;

        //DERECHA
        sensor.set(getWidth() / 2, getHeight() / -2, getWidth() / 2, getHeight() / 2);
        body.createFixture(fixtureD).setUserData(this);

        //IZQUIERDA
        sensor.set(getWidth() / -2, getHeight() / -2, getWidth() / -2, getHeight() / 2);
        body.createFixture(fixtureD).setUserData(this);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Definición de Animación">
        TextureRegion texture = screen.getAtlas().findRegion("spider");
        TextureRegion[][] region = texture.split(60, 40);   //DIVIDIENDO LA TEXTURE-REGION EN UN ARREGLO DE TEXTURES
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
    }

    @Override
    public void specialAttack(Player player)
    {
        player.setCondition(PlayerCondition.ENTANGLED, 5);
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
