/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.actors.monster;

import game.actors.Player;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import game.inventario.Arm;
import game.screens.GameScreen;
import game.tools.Constant;
import game.tools.Sonido;

/**
 *
 * @author Diego
 */
public class Enderman extends MonsterMob
{

    public Enderman(GameScreen screen, int x, int y, boolean isBoss)
    {
        super(screen.getWorld(), screen.getAtlas().findRegion("caminar_enderman"),x,y, 1, 10, 20, isBoss, Sonido.ENDERMAN);

        if (isBoss)
        {
            setBounds(0, 0, (81 / Constant.PPM) * 2, (192 / Constant.PPM) * 2);
            this.attackPoints *= 2;
            this.life *= 2;
            this.prize = new Arm(Constant.BattleObject.SWORD, Constant.Material.DIAMOND);
        }
        else
        {
            setBounds(0, 0, 81 / Constant.PPM, 192 / Constant.PPM);
        }

        //<editor-fold defaultstate="collapsed" desc="Definición de Fixture">
        FixtureDef fixtureD = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() / 2 - 0.2f, getHeight() / 2);
        fixtureD.shape = shape;
        fixtureD.filter.categoryBits = Constant.MOB_BIT;
        fixtureD.filter.maskBits = Constant.GROUND_BIT | Constant.MOB_BIT | Constant.PLAYER_BIT;
        body.createFixture(fixtureD).setUserData(this);
        //</editor-fold>

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
    }

    @Override
    public void specialAttack(Player player)
    {
        body.setLinearVelocity(player.getBody().getPosition().x + 80 * player.getDirection(), 0);
    }

}
