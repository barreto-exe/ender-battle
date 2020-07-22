/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actors;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.minecraft.game.Constant;

/**
 *
 * @author Karen
 */
public class Player extends Actor{
    private Body body;
    private World world;
    private TextureRegion texture;
    private TextureRegion[] frames; 
    private Animation animation;
    private float duration = 0;    

    public Player(World world, TextureRegion textureColor) {
        TextureRegion[][] region= textureColor.split(Constant.PLAYER_WIDTH/4, Constant.PLAYER_HEIGHT);   //DIVIDIENDO LA TEXTURE-REGION EN UN ARREGLO DE TEXTURES
        frames = new TextureRegion[region.length * region[0].length];  //CREANDO ARREGLO DE UNA DIMENSIÓN
        int index = 0;
        
        //APLANANDO ARREGLO DE TEXTURES
        /*lo hice de esta forma porque pienso estructurar los sprites de una manera no lineal*/
        for (int i=0 ; i < region.length ; i++)
            for (int j=0 ; j < region[i].length ; j++)
                frames[index++] = region[i][j];
        
        animation = new Animation(0.15f, frames);    //CREANDO ANIMACION DE CAMINAR 
        
        this.world = world;
        
        BodyDef bodyD= new BodyDef();
        bodyD.position.set(60 / Constant.PPM, 60 / Constant.PPM);
        bodyD.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyD);
        
        FixtureDef fixtureD = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(2.5f / Constant.PPM, 2.5f / Constant.PPM);
        fixtureD.shape = shape;
        body.createFixture(fixtureD);
    }

    public Body getBody() {
        return body;
    }
    
    
}
