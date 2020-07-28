/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import game.MainGame;
import game.screens.worlds.BiomeAssembler;
import sprites.Chicken;
import sprites.Player;
import tools.Constant;
import tools.WorldContactListener;

/**
 *
 * @author Karen
 */
public class GameScreen extends BaseScreen{
    private OrthographicCamera gameCam;
    private FitViewport viewport;
    
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    
    private Box2DDebugRenderer debugger;
    private World world;
    private Player player;
    private Chicken chicken;
    
    private String color;
    
    
    public GameScreen(MainGame game, String biome, String color) {
        super(game);
        this.color = color;
        gameCam = new OrthographicCamera();
        viewport = new FitViewport(Constant.FRAME_WIDTH / Constant.PPM, Constant.FRAME_HEIGHT / Constant.PPM, gameCam);
        
        gameCam.position.set(Constant.FRAME_WIDTH / 2 / Constant.PPM, Constant.FRAME_HEIGHT / 2 / Constant.PPM, 0);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(biome);
        renderer = new OrthogonalTiledMapRenderer(map, 1 / Constant.PPM);
        
        world = new World(new Vector2(0, -10), true);
        debugger = new Box2DDebugRenderer();
        new BiomeAssembler(this);
    }

    public TiledMap getMap() {
        return map;
    }

    public World getWorld() {
        return world;
    }

    @Override
    public void show() {
        player = new Player(this, 2, 2, color);
        chicken = new Chicken(this, 4, 2);
        world.setContactListener(new WorldContactListener(player));
    }
    
    

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
    
    @Override
    public void render(float delta) {
        player.act(delta);
        chicken.act(delta);
        world.step(delta, 6, 2);
        if ((player.getBody().getPosition().x > Constant.FRAME_WIDTH / 2 / Constant.PPM) && player.getBody().getPosition().x < (Constant.MAX_MAP - (Constant.FRAME_WIDTH / 2)) / Constant.PPM)
            gameCam.position.x = player.getBody().getPosition().x;
        gameCam.update();
        renderer.setView(gameCam);
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 0.8f);  //COLOREA EL CIELO
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);     //LIMPIA EL BUFFER 
        renderer.render();
        debugger.render(world, gameCam.combined);
        
        game.getBatch().setProjectionMatrix(gameCam.combined);
        game.getBatch().begin();
        player.draw(game.getBatch());
        chicken.draw(game.getBatch());
        game.getBatch().end();
        
    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        debugger.dispose();
    }
    
    
    
}
