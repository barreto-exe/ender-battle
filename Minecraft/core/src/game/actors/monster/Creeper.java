/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.actors.monster;

import game.actors.Mob;
import game.actors.Player;
import com.badlogic.gdx.graphics.Color;
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

/**
 *
 * @author Diego
 */
public class Creeper extends MonsterMob{
    
    private Array<TextureRegion> explosionFrames;
    private Animation explosionAnimation;
    
    public Creeper(GameScreen screen, int x, int y,boolean isBoss) {
        super(screen.getWorld(),  screen.getAtlas().findRegion("caminar_creeper"), 1.5f, 8, 20,isBoss);
         
        if(isBoss){
         setBounds(0, 0, (55 / Constant.PPM)*2, (128/ Constant.PPM)*2);
         this.attackPoints *=2;
         this.life *=2;
         this.prize = new Arm("hacha","diamante");
        }else{
            setBounds(0, 0, 55 / Constant.PPM, 128 / Constant.PPM);
        }
        
        BodyDef bodyD = new BodyDef();
        bodyD.position.set(x, y);
        bodyD.type = BodyDef.BodyType.DynamicBody;
        body = this.world.createBody(bodyD);
        
        FixtureDef fixtureD = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() / 2, getHeight() / 2);
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
        sensor.set(getWidth() / 2, getHeight() / -2 + 0.1f, getWidth() / 2, getHeight() / 2 -0.1f);
        body.createFixture(fixtureD).setUserData(this);
        
        //IZQUIERDA
         sensor.set(getWidth() / -2, getHeight() / -2 + 0.1f, getWidth() / -2, getHeight() / 2 -0.1f);
        body.createFixture(fixtureD).setUserData(this);
        
         //<editor-fold defaultstate="collapsed" desc="Definición de Animación">
        TextureRegion texture = screen.getAtlas().findRegion("caminar_creeper");
        TextureRegion[][] region = texture.split(55, 128);   //DIVIDIENDO LA TEXTURE-REGION EN UN ARREGLO DE TEXTURES
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
    }

    @Override
    public void specialAttack(Player player) {
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
        if(player.getEnemy().equals(this)){
            System.out.println("Exploto");
        }else{
            System.out.println("No exploto");
        }
    }
    
}
