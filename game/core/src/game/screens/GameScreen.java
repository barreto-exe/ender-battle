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
import game.MainGame;
import actors.pacific.Chicken;
import actors.Player;
import actors.groups.Group;
import com.badlogic.gdx.utils.viewport.FillViewport;
import tools.Constant;
import tools.WorldContactListener;
import static game.screens.worlds.BiomeAssemblerClass.BiomeAssembler;

/**
 *
 * @author Karen
 */
public class GameScreen extends BaseScreen
{
    //<editor-fold defaultstate="collapsed" desc="Atributos">
    //Atributos de la cámara
    private OrthographicCamera gameCam;
    private FillViewport viewport;
    
    //Atributos de objetos del bioma
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    
    //Atributos de Box2d
    private Box2DDebugRenderer debugger;
    private World world;
    
    //Atributos Actores
    private String color;
    private Group actors;
    private Player player;
    private Chicken chicken;
    //</editor-fold>

    /**
     * Es la pantalla del juego principal.
     * @param game instancia del juego GDX.
     * @param biome es el identificador del bioma en la que se encuentra el jugador.
     * @param color nombre del color de personaje elegido por el jugador.
     */
    public GameScreen(MainGame game, String biome, String color)
    {
        super(game);
        this.color = color;
        
        //<editor-fold defaultstate="collapsed" desc="Posicionar Cámara">
        gameCam = new OrthographicCamera();
        viewport = new FillViewport(Constant.FRAME_WIDTH / Constant.PPM, Constant.FRAME_HEIGHT / Constant.PPM, gameCam);
        gameCam.position.set(Constant.FRAME_WIDTH / 2 / Constant.PPM, Constant.FRAME_HEIGHT / 2 / Constant.PPM, 0);
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Construir bioma y set Box2d">
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(biome);
        renderer = new OrthogonalTiledMapRenderer(map, 1 / Constant.PPM);

        world = new World(new Vector2(0, -10), true);
        debugger = new Box2DDebugRenderer();

        BiomeAssembler(this);
        //</editor-fold>
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public TiledMap getMap()
    {
        return map;
    }
    
    public World getWorld()
    {
        return world;
    }
    //</editor-fold>

    @Override
    public void show()
    {
        //Instanciar actores del mundo
        player = new Player(this, 2, 2, color);
        chicken = new Chicken(this, 14, 4);

        //Añadir actores al grupo
        actors = new Group();
        actors.addActor(player);
        actors.addActor(chicken);
        
        world.setContactListener(new WorldContactListener(player));
    }

    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height);
    }

    @Override
    public void render(float delta)
    {
        //Colorear cielo
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 0.8f); 
        //Limpiar buffer
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);  

        //Actualizar actores y mundo
        actors.act(delta);
        world.step(delta, 6, 2);
        
        //<editor-fold defaultstate="collapsed" desc="Mover Cámara">
        if ((player.getBody().getPosition().x > Constant.FRAME_WIDTH / 2 / Constant.PPM) && player.getBody().getPosition().x < (Constant.MAX_MAP - (Constant.FRAME_WIDTH / 2)) / Constant.PPM)
        {
            gameCam.position.x = player.getBody().getPosition().x;
        }
        gameCam.update();
        renderer.setView(gameCam);
        renderer.render();
        debugger.render(world, gameCam.combined);
        game.getBatch().setProjectionMatrix(gameCam.combined);
        //</editor-fold>

        //Dibujar las actualizaciones 
        game.getBatch().begin();
        actors.draw(game.getBatch());
        game.getBatch().end();
    }

    @Override
    public void dispose()
    {
        map.dispose();
        renderer.dispose();
        world.dispose();
        debugger.dispose();
        
        //group.dispose!!!!!!!!!!!!!!!!!!!!
    }

}
