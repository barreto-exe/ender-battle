/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minecraft.bioma.actores;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import static com.minecraft.bioma.Constant.PIXELES_IN_METTER;

/**
 *
 * @author Karen
 */
public class FloorEntity extends Actor{
    //ATTRIBUTES
    private TextureRegion texture, overFloor;
    private World world;
    private Body body;
    private Fixture fixture;

    public FloorEntity(TextureRegion texture, TextureRegion overFloor, World world, float x, float y, float width) {
        this.texture = texture;
        this.overFloor = overFloor;
        this.world = world;
        
        //CREANDO BODY ESTÁTICO DEL SUELO
        BodyDef def = new BodyDef();
        def.position.set(x + width / 2, y - 0.5f);
        body = world.createBody(def);
        
        
        //CREANDO FIXTURE DEL SUELO
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, 0.5f);
        fixture = body.createFixture(shape, 1);
        fixture.setUserData("floor");
        shape.dispose();
        
        //ESTABLECIENDO TAMAÑO Y POSICIÓN DEL SUELO
        setSize(width * PIXELES_IN_METTER, PIXELES_IN_METTER);
        setPosition(x * PIXELES_IN_METTER, (y - 1) * PIXELES_IN_METTER);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());    //DIBUJANDO PISO
        batch.draw(overFloor, getX(), getY() + 0.9f * getHeight(), getWidth(), 0.1f * getHeight());     //DIBUJANDO SOBRE - PISO
    }
         
    public void delete(){
        body.destroyFixture(fixture);   //ELIMINANDO FIXTURE
        world.destroyBody(body);        //ELIMINANDO BODY
    }
}
