/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minecraft.game.screens.worlds;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.minecraft.game.Constant;

/**
 *
 * @author Karen
 */
public class BiomeAssembler {

    public BiomeAssembler(World world, TiledMap map) {
        BodyDef def = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixture = new FixtureDef();
        Body body;
        
        for (MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            
            def.type = BodyDef.BodyType.StaticBody;
            def.position.set((rectangle.getX() + rectangle.getWidth() / 2) / Constant.PPM, (rectangle.getY() + rectangle.getHeight() / 2) / Constant.PPM);
            body = world.createBody(def);
            
            shape.setAsBox(rectangle.getWidth() / 2 / Constant.PPM, rectangle.getHeight() / 2 / Constant.PPM);
            fixture.shape = shape;
            body.createFixture(fixture).setUserData("overfloor");
        }
        
    }
    
}
