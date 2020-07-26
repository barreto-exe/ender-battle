/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actors.mobs;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.minecraft.game.Constant;

/**
 *
 * @author Karen
 */
public class Chicken extends PacificMob{

    public Chicken(World world, TextureRegion texture, int x, int y) {
        super(world);
        setSize(0.67f * Constant.PPM, 0.5f * Constant.PPM); 
        defineMob(x, y);
        TextureRegion[][] region = texture.split(128 / 3, 32);   //DIVIDIENDO LA TEXTURE-REGION EN UN ARREGLO DE TEXTURES
        frames = new TextureRegion[3];  //CREANDO ARREGLO DE UNA DIMENSIÓN
        int index = 0;

        //APLANANDO ARREGLO DE TEXTURES
        for (int i = 0; i < 3; i++)
            frames[index++] = region[0][i];

        animation = new Animation(0.4f, frames);    //CREANDO ANIMACION DE CAMINAR          
        cantAlimento = 15; //por ejemplo
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        duration += delta;
        texture = (TextureRegion) animation.getKeyFrame(duration, true);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x - (getWidth() / Constant.PPM) / 2) * Constant.PPM,
                    (body.getPosition().y - (getHeight()/ Constant.PPM) / 2) * Constant.PPM);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
    

    @Override
    protected void defineMob(int x, int y) {
        BodyDef bodyD = new BodyDef();
        bodyD.position.set(new Vector2(x, y));
        bodyD.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyD);

        FixtureDef fixtureD = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() / Constant.PPM / 2, getHeight() / Constant.PPM / 2);
        fixtureD.shape = shape;
        fixtureD.filter.categoryBits = Constant.MOB_BIT;
        fixtureD.filter.maskBits = Constant.GROUND_BIT | Constant.MOB_BIT;
        body.createFixture(fixtureD).setUserData("PacificMob");
    }
    
}
