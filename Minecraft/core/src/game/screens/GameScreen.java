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
import game.actors.pacific.PacificMob;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FillViewport;
import comunicacion.MetodosSocket;
import comunicacion.MetodosSocket.UsesSocket;
import comunicacion.PaqueteOperacion;
import comunicacion.PaqueteOperacion.Operacion;
import comunicacion.PaqueteOperacion.ResultadoOperacion;
import comunicacion.PaqueteResultado;
import comunicacion.ProgresoJugador;
import game.LoadScreen;
import game.actors.Villager;
import game.actors.monster.EnderDragon;
import game.actors.monster.MonsterMob;
import game.screens.worlds.Room;
import game.screens.worlds.BiomeAssemblerClass;
import game.tools.*;
import game.tools.Constant.MapType;
import game.ui.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
/**
 *
 * @author Karen
 */
public class GameScreen extends BaseScreen implements UsesSocket
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
    private boolean isPaused;

    //Atributos de Box2d
    private Box2DDebugRenderer debugger;
    private World world;

    //Atributos Actores
    private String color;
    private Group actors;
    private Player player;
    private Villager villager;
    private Array<PacificMob> pacificMobs;
    private Array<MonsterMob> monsterMobs;
    private Array<Plant> trees;
    
    //Atributos de la GUI
    Batch batchUI = new SpriteBatch();
    BitmapFont cantidadEsmeralda, mensajeConservacion;
    boolean mostrarMensajeConservacion;
    int contadorMsjConservacion;
    Sprite esmeralda;               
    Sprite corazon, corazonVacio;              
    Sprite corazonMitad;      
    
    Thread hiloProgreso; 
    private static FrmInventario ventanaInventario;    
    private static FrmTienda ventanaTienda;
    private static FrmJugadores ventanaJugadores; 
    //</editor-fold>

    private Room room;
    
    /**
     * Es la pantalla del juego principal.
     *
     * @param game instancia del juego GDX.
     * @param room es el identificador de la habitación en la que se encuentra el
     * jugador.
     * @param player es el jugador que se encuentra en la habitacion
     */
    public GameScreen(MainGame game, Room room, Player player)
    {
        super(game);
        isPaused = mostrarMensajeConservacion = false;
        this.player = player;
        this.room = room;
        this.contadorMsjConservacion = 0;
        this.actors = new Group();
        
        ventanaInventario = new FrmInventario(player);
        ventanaTienda = new FrmTienda(player);
        ventanaJugadores = new FrmJugadores(game.getVentanaOrigen());
        
        //<editor-fold defaultstate="collapsed" desc="Posicionar Cámara">
        gameCam = new OrthographicCamera();
        viewport = new FillViewport(Constant.FRAME_WIDTH / Constant.PPM, Constant.FRAME_HEIGHT / Constant.PPM, gameCam);
        gameCam.position.set(Constant.FRAME_WIDTH / 2 / Constant.PPM, Constant.FRAME_HEIGHT / 2 / Constant.PPM, 0);
        gameCam.zoom += 0.2f;
        gameCam.translate(new Vector2(2, 0.3f));
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Construir bioma">
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(room.getMap());
        renderer = new OrthogonalTiledMapRenderer(map, 1 / Constant.PPM);

        world = new World(new Vector2(0, -10), true);
        debugger = new Box2DDebugRenderer();
        //</editor-fold>
    
        //<editor-fold defaultstate="collapsed" desc="Inicializar GUI">
        cantidadEsmeralda = new BitmapFont();
        mensajeConservacion = new BitmapFont();
        esmeralda = new Sprite(this.getAtlas().findRegion("esmeralda"));
        corazon = new Sprite(this.getAtlas().findRegion("corazon_lleno"));
        corazonVacio = new Sprite(this.getAtlas().findRegion("corazon_vacio"));
        corazonMitad = new Sprite(this.getAtlas().findRegion("corazon_mitad"));
        //</editor-fold>
        
        
        if(game.getUsuario() != null)
        {
            comunicarEstadoJugador();
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public boolean isMostrarMensajeConservacion()
    {
        return mostrarMensajeConservacion;
    }

    public void setMostrarMensajeConservacion(boolean mostrarMensajeConservacion)
    {
        contadorMsjConservacion++;
        
        this.mostrarMensajeConservacion = mostrarMensajeConservacion && contadorMsjConservacion % 5 == 0;
    }
    
    public static FrmJugadores getVentanaJugadores()
    {
        return ventanaJugadores;
    }

    public TiledMap getMap()
    {
        return map;
    }

    public World getWorld()
    {
        return world;
    }

    public Player getPlayer() {
        return player;
    }

    public Villager getVillager() {
        return villager;
    }
    
    public Group getActors()
    {
        return actors;
    }

    public static FrmInventario getVentanaInventario()
    {
        return ventanaInventario;
    }

    public static FrmTienda getVentanaTienda()
    {
        return ventanaTienda;
    }

    
    public boolean isIsPaused()
    {
        return isPaused;
    }

    public void setIsPaused(boolean isPaused)
    {
        this.isPaused = isPaused;
    }

    //</editor-fold>
    
    @Override
    public void show()
    {
        manager = new BiomeAssemblerClass(this);
        Vector2 position;
        position = manager.getVillagerPosition();
        villager = new Villager(world, getAtlas().findRegion("aldeano"), position.x, position.y);
        actors.addActor(villager);
        pacificMobs = manager.getPacificMobs();
        trees = manager.getPlants();
        monsterMobs = manager.getMonsters();

        for (Plant plant : trees)
        {
            actors.addActor(plant);
        }

        for (PacificMob mob : pacificMobs)
        {
            actors.addActor(mob);
        }
        
        for (MonsterMob mob : monsterMobs)
        {
            actors.addActor(mob);
        }

        position = manager.getPlayerPosition();
        player.create(this, position.x, position.y);
        
        //Añadir actores al grupo
        actors.addActor(player);
        actors.addActor(new EnderDragon(this, 8, 15));
        world.setContactListener(new WorldContactListener(player));
    }
    
    private boolean cambiandoBioma = false, cambiarBioma = false;
    private Room nextRoom;
    
    /**
     * Indica al screen que debe cambiar al siguiente bioma. La acción se
     * efectúa después de 7 segundos.
     */
    public void cambiarHabitacion()
    {
        if(cambiandoBioma)
            return;
        
        System.out.println("cambiando");
        
        cambiandoBioma = true;
        
        //Determinar próxima habitación a la que se cambiará
        //Dependiendo de la habitación actual:
        switch(this.room.getType())
        {
            //Si actual es bioma
            case BIOME:
            {
                //La próxima es combate (con la decoración del mapa actual).
                nextRoom = new Room(room.getMapNum(),MapType.FIGHT);
                break;
            }
            //Si actual es de combate
            case FIGHT:
            {
                //La próxima es alguna en la que el player aún no haya estado.
                
                //Consultar próximo bioma
                int proximo = player.proximoBioma();
                
                //Si no hay próximo, entonces toca ir al ender.
                if(proximo == -1)
                {
                    nextRoom = new Room(0,MapType.END);
                }
                //Si hay próximo, ir a ese mapa
                else
                {
                    nextRoom = new Room(proximo,MapType.BIOME);
                }
                
                break;
            }
            //Si actual es END, no hacer nada
            default:
            {
                return;
            }
        }
        
        //Esperar 7 segundos y aprobar cambio de bioma
        new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                    Thread.sleep(2000);
                    cambiarBioma = true;
                }
                catch (InterruptedException ex)
                {
                }
            }
        }).start();
    }
    
    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height);
    }

    /**
     * Muestra la vida y las esmeraldas del usuario.
     */
    private void dibujarGUI()
    {   
        //Contar cantidad de corazones a dibujar
        int cantMedioCorazones = (int) (player.getLife() /5);
        int cantCorazones = cantMedioCorazones / 2;
        boolean medioCorazon = cantMedioCorazones % 2 > 0;
        
        batchUI.begin();

        //Dibujar sprite esmeralda y su cantidad
        batchUI.draw(esmeralda, 40, 590);
        cantidadEsmeralda.draw(batchUI, ""+player.getInventory().getEsmeraldas() ,50 + esmeralda.getWidth(), 620);
        
        //Dibujar los corazones
        int i;
        for(i = 0; i < 10; i++)
        {
            batchUI.draw(corazonVacio, 835 + (corazon.getWidth()+5)*i, 580);
        }
        for(i = 0; i < cantCorazones; i++)
        {
            batchUI.draw(corazon, 835 + (corazon.getWidth()+5)*i, 580);
        }
        if(medioCorazon)
        {
            batchUI.draw(corazonMitad, 835 + (corazon.getWidth()+5)*i, 580);
        }
        
        if(mostrarMensajeConservacion)
        {
            mensajeConservacion.draw(batchUI, "¡No maltrates plantas ni animales en la vida real!", 100, 100);
        }
        
        batchUI.end();
    }
    
    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 0.8f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (!isPaused)
        {
            actors.act(delta);
            world.step(delta, 6, 2);   
        }
        //<editor-fold defaultstate="collapsed" desc="Mover Cámara">
        if ((player.getBody().getPosition().x > Constant.FRAME_WIDTH / 2 / Constant.PPM)
                && player.getBody().getPosition().x < (Constant.MAX_MAP - (Constant.FRAME_WIDTH / 2)) / Constant.PPM - 4
                    && room.getType() == MapType.BIOME)
        {
            gameCam.position.x = player.getBody().getPosition().x + 2;
        }
        
        gameCam.update();
        renderer.setView(gameCam);
        renderer.render();
        debugger.render(world, gameCam.combined);
        game.getBatch().setProjectionMatrix(gameCam.combined);
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Dibujar habitación">
        game.getBatch().begin();
        actors.draw(game.getBatch());
        game.getBatch().end();
        //</editor-fold>
        
        dibujarGUI();
        
        //Si jugador llegó al final, disparar cambio de habitación.
        if(player.getBody().getPosition().x > 20)
        {
            this.cambiarHabitacion();
        }
        
        //Si procesó el cambio de bioma, colocar loadscreen.
        if(cambiarBioma)
        {
            game.setScreen(new LoadScreen(game, nextRoom, player));
            this.dispose();
        }
    }

    @Override
    public void dispose()
    {
        map.dispose();
        renderer.dispose();
        world.dispose();
        debugger.dispose();
    }

    /**
     * Intercambia el estado del juego entre pausado
     * y despausado.
     */
    public void switchPaused()
    {
        isPaused ^= true;
    }
    public void pararReporteEstadoJugador()
    {
        if(hiloProgreso != null)
        {
            hiloProgreso.interrupt();
            
            //Enviar último reporte de salir de partida
            game.getProgreso().setEnPartida(false);
            PaqueteOperacion paquete = new PaqueteOperacion(Operacion.REPORTE_PROGRESO, game.getProgreso());
            MetodosSocket.enviarPaquete(paquete, null);
        }
    }
    
    /**
     * Método que se encarga de comunicar constantemente al servidor sobre
     * el avance del jugador en la partida. También se envarga de recibir
     * el estado de los demás jugadores.
     */
    public void comunicarEstadoJugador()
    {
        final UsesSocket ventanaOrigen = this;
        hiloProgreso = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    while(true)
                    {
                        PaqueteOperacion paquete = new PaqueteOperacion(Operacion.REPORTE_PROGRESO, game.getProgreso());
                        MetodosSocket.enviarPaquete(paquete, ventanaOrigen);
                        Thread.sleep(1000);
                    }
                }
                catch (InterruptedException ex)
                {
                    System.out.println(ex.toString());
                }
                
            }
        });
        hiloProgreso.start();
    }
    @Override
    public void recibirRespuestaServer(final Socket socket, UsesSocket ventanaDelegada)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                //System.out.println("Esperando respuesta server partidas... " + cont++);

                try
                {
                    //Leer respuesta del servidor
                    ObjectInputStream paqueteRecibido = new ObjectInputStream(socket.getInputStream());
                    PaqueteResultado resultado = (PaqueteResultado) paqueteRecibido.readObject();

                    if (resultado.getResultado() == ResultadoOperacion.RESPUESTA_REPORTE)
                    {
                        ArrayList<ProgresoJugador> progresos = (ArrayList<ProgresoJugador>)resultado.getInformacion();
                        Object[][] rows = new Object[progresos.size()][];
                        
                        int index = 0;
                        for(ProgresoJugador progreso : progresos)
                        {
                            rows[index] = new Object[]
                            {
                                progreso.getNombreJugador(),
                                progreso.getPersonajeSeleccionadoString(),
                                progreso.getJefesMatados(),
                                progreso.getEsmeraldasRecogidas()
                            };
                            index++;
                        }
                        
                        ventanaJugadores.actualizarTabla(rows);
                    } 
                    
                    socket.close();
                } 
                catch (IOException | ClassNotFoundException ex)
                {
                }
            }
        }).start();
    }
}
