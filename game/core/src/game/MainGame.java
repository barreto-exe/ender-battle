package game;

import actors.Player;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.screens.GameScreen;

public class MainGame extends Game {
    private SpriteBatch batch;
    private Player player;

    public SpriteBatch getBatch() {
        return batch;
    }
    
    @Override
    public void create() {
        batch = new SpriteBatch();
        player = new Player("normal");
        setScreen(new GameScreen(this, "bioma1.tmx", player));          //MOSTRANDO LA PANTALLA DE JUEGO
    }  

    @Override
    public void render() {
        super.render();
    }
    
    
}
