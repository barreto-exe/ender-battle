/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minecraft.bioma.actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import static com.minecraft.bioma.Constants.HEIGHT;
import static com.minecraft.bioma.Constants.IMPULSE_JUMP;
import static com.minecraft.bioma.Constants.PIXELES_IN_METTER;
import static com.minecraft.bioma.Constants.SPEED_PLAYER;
import static com.minecraft.bioma.Constants.WIDTH;

/**
 *
 * @author Karen
 */
public class PlayerActor extends Actor{
    //ATTRIBUTES
    private TextureRegion texture;
    private TextureRegion[] frames;
    private World world;
    private Body body;
    private Fixture fixture;
    private boolean isJumping;
    private Animation animation;
    private float duration = 0;
    
    //BUILDER
    public PlayerActor(TextureRegion texture, World world, Vector2 position) {
        TextureRegion[][] region= texture.split(WIDTH/4, HEIGHT);   //DIVIDIENDO LA TEXTURE-REGION EN UN ARREGLO DE TEXTURES
        frames = new TextureRegion[region.length * region[0].length];  //CREANDO ARREGLO DE UNA DIMENSIÓN
        int index = 0;
        
        //APLANANDO ARREGLO DE TEXTURES
        /*lo hice de esta forma porque pienso estructurar los sprites de una manera no lineal*/
        for (int i=0 ; i < region.length ; i++)
            for (int j=0 ; j < region[i].length ; j++)
                frames[index++] = region[i][j];
        
        animation = new Animation(0.15f, frames);    //CREANDO ANIMACION DE CAMINAR
            
        this.world = world;
        
        //CREANDO BODY DINÁMICO DEL JUGAODR
        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(def);
        
        //CREANDO FIXTURE DEL JUGADOR
        /*por ahora le puse un fixture cuadrado, sin embargo hay que personalizarlo*/
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f, 0.5f);  
        fixture = body.createFixture(shape, 3);
        fixture.setUserData("player");
        shape.dispose();
        
        isJumping = false;        
        setSize(PIXELES_IN_METTER, PIXELES_IN_METTER);   //EXTABLECIENDO TAMAÑO DE 1 * 1 METRO
    }
    
    //GETTERS AND SETTERS
    public boolean isIsJumping() {
        return isJumping;
    }

    public void setIsJumping(boolean isJumping) {
        this.isJumping = isJumping;
    }
    

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //ACTUALIZANDO POSICION DEL PLAYER A LA POSICIÓN DEL BODY
        setPosition((body.getPosition().x - 0.5f) * PIXELES_IN_METTER, 
                    (body.getPosition().y - 0.5f) * PIXELES_IN_METTER);
        //DIBUJANDO TEXTURE DEL JUGADOR
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
      
        //SI ESTÁ SALTANDO SE REFLEJA LA ANIMACION
        if (isJumping){
            body.applyForceToCenter(0, IMPULSE_JUMP * -1.05f, true);
            jumpAnimation();
        }
        
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)){
            jump(); //SI PRESIONA LA TECLA UP SE PRODUCE EL MOVIMIENTO DE SALTAR
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)){
            walk(1);  //SI PRESIONA LA TECLA RIGHT SE PRODUCE EL MOVIMIENTO LINEAL Y SE REFLEJA LA ANIMACIÓN
            walkAnimation(delta);
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)){
            walk(-1);  //SI PRESIONA LA TECLA RIGHT SE PRODUCE EL MOVIMIENTO LINEAL Y SE REFLEJA LA ANIMACIÓN
            walkAnimation(delta);  /*hay que arreglar el stprite cuando va al sentido opuesto*/
        } else {
            if (!isJumping)
                body.setLinearVelocity(0, 0);
            repose();   //SI NO PRESIONA NINGUNA TECLA, LA ANIMACIÓN SE DETIENE
        }               
    }
    
        
    public void delete(){
        body.destroyFixture(fixture);   //ELIMINANDO FIXTURE
        world.destroyBody(body);        //ELIMINANDO BODY
    }

    private void jump() {
        if (!isJumping){
            isJumping = true;
            Vector2 position = body.getPosition(); //SE ACTUALIZA EL VECTOR POSICION A LA POSICION ACTUAL DEL BODY
            body.applyLinearImpulse(0, IMPULSE_JUMP, position.x, position.y, true);   //SE APLICA IMPULSO VERTICAL QUE GENERA EL SALTO
        }
    }
    
    public void jumpAnimation(){
        texture = frames[0];   //ESTABLECIENDO EL FRAME DEL SALTO
        /*esto es temporal (CREO). Hay que arreglar bien los sprites*/
    }
    
    public void walk(int direction){
        Vector2 position = body.getPosition();   //SE ACTUALIZA EL VECTOR POSICION A LA POSICION ACTUAL DEL BODY
        if (!isJumping){
            body.setLinearVelocity(direction * SPEED_PLAYER,0);   //SE APLICA FUERZA HORIZONTAL QUE GENERA EL MOVIMIENTO DE CAMINAR
        } else {
            body.applyForce((IMPULSE_JUMP - 8) * direction, 0, position.x, position.y, true);
        }
    }
    
    public void walkAnimation(float delta){
        if (!isJumping){
            duration += delta;  
            texture = (TextureRegion) animation.getKeyFrame(duration, true);    //ACTUALIZANDO FRAME DE LA ANIMACION DE CAMINAR EN FUNCION DEL TIEMPO
        }
    }
    
    public void repose(){
        if (!isJumping)
            texture = frames[3]; //ESTABLECIENDO EL FRAME DE REPOSO ERA EL 3
    }
}
