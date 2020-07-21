/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minecraft.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.minecraft.game.Constant;
import com.minecraft.game.MainGame;

/**
 *
 * @author Karen
 */
public class GameScreen extends BaseScreen{
    private Stage stage;
    private OrthographicCamera gameCam;
    private Viewport viewport;
    
    private Box2DDebugRenderer debugger;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    
    private World world;
    
    public GameScreen(MainGame game) {
        super(game);
        stage = new Stage();
        gameCam = new OrthographicCamera();
        viewport = new FitViewport(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT, gameCam);
        
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("prueba.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gameCam.position.set(Constant.FRAME_WIDTH / 2, Constant.FRAME_HEIGHT/ 2, 0);
        world = new World(new Vector2(0, -10), true);
        
        debugger = new Box2DDebugRenderer();
        BodyDef def = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixture = new FixtureDef();
        Body body;
        
        for (MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            
            def.type = BodyDef.BodyType.StaticBody;
            def.position.set(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2);
            body = world.createBody(def);
            
            shape.setAsBox(rectangle.getWidth() / 2, rectangle.getHeight() / 2);
            fixture.shape = shape;
            body.createFixture(fixture);
        }
               
    }

    public World getWorld() {
        return world;
    }
    
    
    public void uptadte(float delta){
        if (Gdx.input.isTouched())
            gameCam.position.x += 100 * delta;
          //ACTUALIZANDO EL STAGE Y SUS ACTORES
        renderer.setView(gameCam);
        stage.act(); 
        world.step(delta, 6, 2);
        gameCam.update();
        stage.draw();  
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
    
    
    
    @Override
    public void render(float delta) {
        uptadte(delta);
        Gdx.gl.glClearColor(0.4f, 0.5f , 0.8f, 0.8f);  //COLOREA EL CIELO
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);     //LIMPIA EL BUFFER
        
        debugger.render(world, gameCam.combined);
        renderer.render();
    }

    
    
    
}
