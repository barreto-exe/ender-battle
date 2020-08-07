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
import game.inventario.Protection;
import game.screens.GameScreen;
import game.tools.Constant;
import game.tools.Constant.PlayerCondition;

/**
 *
 * @author Diego
 */
public class Spider extends MonsterMob
{

    public Spider(GameScreen screen, int x, int y,boolean isBoss)
    {

        super(screen.getWorld(), screen.getAtlas().findRegion("spider"), 1.1f, 8, 10,isBoss);
        
        if(isBoss){
            setBounds(0, 0, (60 / Constant.PPM)*2, (60 / Constant.PPM)*2);
            this.attackPoints *=2;
            this.life *=2;
            this.prize = new Protection("pecho","diamante");
        }else{
            setBounds(0, 0, 60 / Constant.PPM, 40 / Constant.PPM);
        }
        

        //<editor-fold defaultstate="collapsed" desc="Definición de Body">
        BodyDef bodyD = new BodyDef();
        bodyD.position.set(x, y);
        bodyD.type = BodyDef.BodyType.DynamicBody;
        body = this.world.createBody(bodyD);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Definición de Fixture">
        FixtureDef fixtureD = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() / 2, getHeight() / 2);
        fixtureD.shape = shape;
        fixtureD.filter.categoryBits = Constant.MOB_BIT;
        fixtureD.filter.maskBits = Constant.GROUND_BIT | Constant.MOB_BIT | Constant.PLAYER_BIT;
        body.createFixture(fixtureD).setUserData(this);

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
        if (attackOportunity(1))
        {
            player.setCondition(PlayerCondition.ENTANGLED, 5);
            System.out.println("Is entangled");
            return;
        }
        System.out.println("Failed");
    }

}
