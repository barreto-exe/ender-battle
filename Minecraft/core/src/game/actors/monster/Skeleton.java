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

/**
 *
 * @author Diego
 */
public class Skeleton extends MonsterMob
{
    
    float direction;
    public Skeleton(GameScreen screen, int x, int y,boolean isBoss)
    {
        super(screen.getWorld(), screen.getAtlas().findRegion("caminar_esqueleto"), 1, 8, 10,isBoss);
        
        if(isBoss){
         setBounds(0, 0, (70 / Constant.PPM)*2, (128/ Constant.PPM)*2);
         this.attackPoints *=2;
         this.life *=2;
         this.prize = new Protection(Constant.BattleObject.LEGGING,Constant.Material.DIAMOND);
        }else{
            setBounds(0, 0, 70 / Constant.PPM, 128 / Constant.PPM);
        }
        
        BodyDef bodyD = new BodyDef();
        bodyD.position.set(x, y);
        bodyD.type = BodyDef.BodyType.DynamicBody;
        body = this.world.createBody(bodyD);

        FixtureDef fixtureD = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() / 2 , getHeight() / 2);
        fixtureD.shape = shape;
        fixtureD.filter.categoryBits = Constant.MOB_BIT;
        fixtureD.filter.maskBits = Constant.GROUND_BIT | Constant.MOB_BIT | Constant.PLAYER_BIT;
        body.createFixture(fixtureD).setUserData(this);

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
        //<editor-fold defaultstate="collapsed" desc="Definición de Animación">
        TextureRegion texture = screen.getAtlas().findRegion("caminar_esqueleto");
        TextureRegion[][] region = texture.split(70, 128);   //DIVIDIENDO LA TEXTURE-REGION EN UN ARREGLO DE TEXTURES
        frames = new Array<>();

        //APLANANDO ARREGLO DE TEXTURES
        for (TextureRegion[] regionF : region)
        {
            for (TextureRegion regionC : regionF)
            {
                frames.add(regionC);
            }
        }

        animation = new Animation(0.14f, frames);    //CREANDO ANIMACION DE CAMINAR

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
//arrow.set(getWidth() / -2 - 4, getHeight() / -2 + 0.5f, getWidth() / -2 - 4, getHeight() / 2 - 0.1f);
