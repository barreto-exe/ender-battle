/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import game.screens.GameScreen;
import tools.Constant;

/**
 *
 * @author Karen
 */
public class Chicken extends PacificMob{

    public Chicken(GameScreen screen, int x, int y) {
        super(screen.getWorld(), screen.getAtlas().findRegion("chicken"));
        setBounds(0, 0, 128 / 3 / Constant.PPM, 32 / Constant.PPM);
        
        BodyDef bodyD = new BodyDef();
        bodyD.position.set(x, y);
        bodyD.type = BodyDef.BodyType.DynamicBody;
        body = this.world.createBody(bodyD);

        FixtureDef fixtureD = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() / 2, getHeight() / 2);
        fixtureD.shape = shape;
        fixtureD.filter.categoryBits = Constant.MOB_BIT;
        fixtureD.filter.maskBits = Constant.GROUND_BIT | Constant.MOB_BIT;
        body.createFixture(fixtureD).setUserData("PacificMob");
        
        TextureRegion texture = screen.getAtlas().findRegion("chicken");         
        TextureRegion[][] region = texture.split(128 / 3, 32);   //DIVIDIENDO LA TEXTURE-REGION EN UN ARREGLO DE TEXTURES
        frames = new TextureRegion[3];  //CREANDO ARREGLO DE UNA DIMENSIÓN
        int index = 0;

        //APLANANDO ARREGLO DE TEXTURES
        for (int i = 0; i < 3; i++)
            frames[index++] = region[0][i];

        animation = new Animation(0.15f, frames);    //CREANDO ANIMACION DE CAMINAR          
        cantAlimento = 15; //por ejemplo
    }
    
    public void act(float delta){
        body.setLinearVelocity(speed, 0);
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        duration += delta;
        setRegion((TextureRegion) animation.getKeyFrame(duration, true));
    }
}
