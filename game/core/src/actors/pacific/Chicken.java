/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actors.pacific;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import game.screens.GameScreen;
import tools.Constant;

/**
 *
 * @author Karen
 */
public class Chicken extends PacificMob 
{

    public Chicken(GameScreen screen, int x, int y)
    {
        super(screen.getWorld(), screen.getAtlas().findRegion("chicken"), 1.2f, 5);
        
        //Colocar posición
        setBounds(0, 0, 96 / 3 / Constant.PPM, 25 / Constant.PPM);

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
        sensor.set(getWidth() / 2, getHeight() / -2 + 0.1f, getWidth() / 2, getHeight() / 2 -0.1f);
        body.createFixture(fixtureD).setUserData(this);
        
        //IZQUIERDA
        sensor.set(getWidth() / -2, getHeight() / -2 + 0.1f, getWidth() / -2, getHeight() / 2 -0.1f);
        body.createFixture(fixtureD).setUserData(this);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Definición de Animación">
        TextureRegion texture = screen.getAtlas().findRegion("chicken");
        TextureRegion[][] region = texture.split(96 / 3, 25);   //DIVIDIENDO LA TEXTURE-REGION EN UN ARREGLO DE TEXTURES
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
        cantAlimento = 15; //por ejemplo
    }

    
}
