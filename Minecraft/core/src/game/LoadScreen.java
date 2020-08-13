package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.actors.Player;
import game.screens.BaseScreen;
import game.screens.GameScreen;
import game.screens.worlds.Room;

/**
 *
 * @author luisb
 */
public class LoadScreen extends BaseScreen
{
    private SpriteBatch batch;
    private float tiempoColor, tiempoCambio;
    private Room habitaacionDestino;
    private Player player;
    
    /**
     * Es una pantalla de carga con un fondo verde parpadeando y el logo abajo a la izquierda.
     * @param game instancia del juego en donde se cargará la próxima screen.
     * @param habitacionDestino habitacion que se mostrará después de unos 5 segundos de carga.
     * @param player instancia del player.
     */
    public LoadScreen(MainGame game, Room habitacionDestino, Player player)
    {
        super(game);
        batch = new SpriteBatch();
        tiempoColor = 0;
        tiempoCambio = 6;
        
        this.habitaacionDestino = habitacionDestino;
        this.player = player;
    }

    @Override
    public void render(float delta)
    {
        tiempoColor += delta;
        Gdx.gl.glClearColor(0, Math.abs((float) Math.cos(tiempoColor)) - 0.5f, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        
        batch.draw(getAtlas().findRegion("logo"), 50, 45);
        
        batch.end();
        
        tiempoCambio -= delta;
        if(tiempoCambio <= 0)
        {
            game.setScreen(new GameScreen(game, habitaacionDestino, player));
        }
    }
}
