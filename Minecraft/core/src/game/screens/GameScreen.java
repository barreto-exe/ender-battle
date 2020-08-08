package game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import game.MainGame;
import game.actors.Player;
import game.actors.farming.plants.Plant;
import game.actors.groups.Group;
import game.actors.monster.Zombie;
import game.actors.pacific.PacificMob;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FillViewport;
import game.actors.collectibles.EsmeraldCollective;
import game.screens.worlds.BiomeAssemblerClass;
import game.tools.Constant;
import game.tools.WorldContactListener;
import game.ui.FrmInventario;

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
    private BiomeAssemblerClass manager;

    //Atributos de Box2d
    private Box2DDebugRenderer debugger;
    private World world;

    //Atributos Actores
    private String color;
    private Group actors;
    private Player player;
    private Array<PacificMob> pacificMobs;
    private Array<Plant> trees;
    //</editor-fold>

    private static FrmInventario ventanaInventario;

    /**
     * Es la pantalla del juego principal.
     *
     * @param game instancia del juego GDX.
     * @param biome es el identificador del bioma en la que se encuentra el
     * jugador.
     * @param player es el jugador que se encuentra en el bioma
     */
    public GameScreen(MainGame game, String biome, Player player)
    {
        super(game);
        this.player = player;
        actors = new Group();
        ventanaInventario = new FrmInventario(player);

        //<editor-fold defaultstate="collapsed" desc="Posicionar Cámara">
        gameCam = new OrthographicCamera();
        viewport = new FillViewport(Constant.FRAME_WIDTH / Constant.PPM, Constant.FRAME_HEIGHT / Constant.PPM, gameCam);
        gameCam.position.set(Constant.FRAME_WIDTH / 2 / Constant.PPM, Constant.FRAME_HEIGHT / 2 / Constant.PPM, 0);
        gameCam.zoom += 0.2f;
        gameCam.translate(new Vector2(2, 0.3f));
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Construir bioma">
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(biome);
        renderer = new OrthogonalTiledMapRenderer(map, 1 / Constant.PPM);

        world = new World(new Vector2(0, -10), true);
        debugger = new Box2DDebugRenderer();
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

    public Group getActors()
    {
        return actors;
    }

    public static FrmInventario getVentanaInventario()
    {
        return ventanaInventario;
    }

    //</editor-fold>
    
    @Override
    public void show()
    {
        manager = new BiomeAssemblerClass(this);
        pacificMobs = manager.getPacificMobs();
        trees = manager.getFarming();

        for (Plant plant : trees)
        {
            actors.addActor(plant);
        }

        for (PacificMob mob : pacificMobs)
        {
            actors.addActor(mob);
        }

        //Instanciar actores del mundo
        this.player.create(this);

        //Añadir actores al grupo
        actors.addActor(player);

        //Monstruos de prueba   
        //actors.addActor(new Skeleton(this,20,5));
        actors.addActor(new Zombie(this, 17, 5, false));
        //actors.addActor(new Pigman(this,15,5));
        //actors.addActor(new Creeper(this,13,5));
        actors.addActor(new EsmeraldCollective(getAtlas().findRegion("esmeralda"), world, new Vector2(25, 6)));

        world.setContactListener(new WorldContactListener(player));
    }

    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height);
    }

    //<editor-fold defaultstate="collapsed" desc="Elementos de la GUI">
    Batch batchUI = new SpriteBatch();
    BitmapFont cantidadEsmeralda = new BitmapFont();
    Sprite esmeralda = new Sprite(this.getAtlas().findRegion("esmeralda"));
    Sprite corazon = new Sprite(this.getAtlas().findRegion("corazon_lleno"));
    Sprite corazonMitad = new Sprite(this.getAtlas().findRegion("corazon_mitad"));
    //</editor-fold>

    
    private void dibujarGUI()
    {   
        int cantMedioCorazones = (int) (player.getLife() /5);
        int cantCorazones = cantMedioCorazones / 2;
        boolean medioCorazon = cantMedioCorazones % 2 > 0;
        
        batchUI.begin();

        batchUI.draw(esmeralda, 40, 590);
        cantidadEsmeralda.draw(batchUI, ""+player.getInventory().getEsmeraldas() ,50 + esmeralda.getWidth(), 620);
        
        int i;
        for(i = 0; i < cantCorazones; i++)
        {
            batchUI.draw(corazon, 825 + (corazon.getWidth()+5)*i, 580);
        }
        if(medioCorazon)
        {
            batchUI.draw(corazonMitad, 825 + (corazon.getWidth()+5)*i, 580);
        }
        
        batchUI.end();
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
        if ((player.getBody().getPosition().x > Constant.FRAME_WIDTH / 2 / Constant.PPM)
                && player.getBody().getPosition().x < (Constant.MAX_MAP - (Constant.FRAME_WIDTH / 2)) / Constant.PPM - 4)
        {
            gameCam.position.x = player.getBody().getPosition().x + 2;
        }
        gameCam.update();
        renderer.setView(gameCam);
        renderer.render();
        //debugger.render(world, gameCam.combined);
        game.getBatch().setProjectionMatrix(gameCam.combined);
        //</editor-fold>

        //Dibujar las actualizaciones 
        game.getBatch().begin();
        actors.draw(game.getBatch());
        game.getBatch().end();

        dibujarGUI();
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
