/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.screens.worlds;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import game.tools.Constant;
import game.screens.GameScreen;

/**
 * @author Karen
 */
public class BiomeAssemblerClass {
    
    /**
     * Se encarga de ensamblar el bioma en la bantalla.
     * @param screen la pantalla en la que se construirá el bioma.
     */
    public static void BiomeAssembler(GameScreen screen) {
        BodyDef def = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixture = new FixtureDef();
        Body body;
        
        for (MapObject object : screen.getMap().getLayers().get(1).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            
            def.type = BodyDef.BodyType.StaticBody;
            def.position.set((rectangle.getX() + rectangle.getWidth() / 2) / Constant.PPM, (rectangle.getY() + rectangle.getHeight() / 2) / Constant.PPM);
            body = screen.getWorld().createBody(def);
            
            shape.setAsBox(rectangle.getWidth() / 2 / Constant.PPM, rectangle.getHeight() / 2 / Constant.PPM);
            fixture.shape = shape;
            fixture.filter.categoryBits = Constant.GROUND_BIT;
            fixture.filter.maskBits = Constant.PLAYER_BIT | Constant.PLAYER_FEET_BIT | Constant.MOB_BIT | Constant.MOB_SENSOR_BIT;
            body.createFixture(fixture).setUserData("overfloor");
        }
    }
    
    //La clase no es instanciable, sólo se usa para contener al método BiomeAssembler.
    private BiomeAssemblerClass(){};
}
