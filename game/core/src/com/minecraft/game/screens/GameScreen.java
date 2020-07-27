/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minecraft.game.screens;

import actors.Player;
import actors.mobs.Chicken;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import tools.Constant;
import com.minecraft.game.MainGame;
import com.minecraft.game.screens.worlds.BiomeAssembler;
import tools.WorldContactListener;

/**
 *
 * @author Karen
 */
public class GameScreen extends BaseScreen
{

    private Stage stage;
    private OrthographicCamera gameCam;
    private Viewport viewport;

    private Box2DDebugRenderer debugger;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Player player;
    private Chicken chicken;

    /*CREO QUE RECIBE UN ARRAYLIST DEL SERVER CON LOS JUGADORES DE LA PARTIDA EN CURSO*/
    public GameScreen(MainGame game, String biome)
    {
        super(game);
        gameCam = new OrthographicCamera();
        viewport = new FitViewport(Constant.FRAME_WIDTH / Constant.PPM, Constant.FRAME_HEIGHT / Constant.PPM, gameCam);
        stage = new Stage(/*viewport*/);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load(biome);
        renderer = new OrthogonalTiledMapRenderer(map, 1 / Constant.PPM);
        gameCam.position.set(Constant.FRAME_WIDTH / 2 / Constant.PPM, Constant.FRAME_HEIGHT / 2 / Constant.PPM, 0);
        world = new World(new Vector2(0, -10), true);

        debugger = new Box2DDebugRenderer();
        new BiomeAssembler(this);
    }

    @Override
    public void show()
    {
        chicken = new Chicken(world, getAtlas().findRegion("chicken"), 4, 2);
        stage.addActor(chicken);
        
        player = new Player(world, getAtlas().findRegion("caminar"), new Vector2(128 / Constant.PPM, 128 / Constant.PPM));
        stage.addActor(player);
        
        world.setContactListener(new WorldContactListener(player));
    }

    public World getWorld()
    {
        return world;
    }

    public TiledMap getMap() {
        return map;
    }
    
    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height);
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 0.8f);  //COLOREA EL CIELO
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);     //LIMPIA EL BUFFER
        
        renderer.setView(gameCam);
        renderer.render();
        stage.act();
        world.step(delta, 6, 2);
        stage.draw();
        debugger.render(world, gameCam.combined);
        if (player.getX() > 500 && player.getController().isRight())
            gameCam.position.x += 10 / Constant.PPM;
        gameCam.update();
    }

    @Override
    public void dispose()
    {
        map.dispose();
        renderer.dispose();
        world.dispose();
        debugger.dispose();
        stage.dispose();
    }

}
