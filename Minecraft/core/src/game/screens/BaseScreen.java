package game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import game.MainGame;

/**
 * @author Karen
 */
public abstract class BaseScreen implements Screen
{
    protected MainGame game;
    private TextureAtlas atlas;
    private TextureAtlas plantas;

    public BaseScreen(MainGame game)
    {
        atlas = new TextureAtlas("atlas.atlas");
        plantas = new TextureAtlas("plantas.atlas");
        this.game = game;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public TextureAtlas getAtlas()
    {
        return atlas;
    }
    public TextureAtlas getPlantas() {
        return plantas;
    }
    public MainGame getGame()
    {
        return game;
    }
    //</editor-fold>
    
    @Override
    public void show()
    {
    }

    @Override
    public void render(float delta)
    {
    }

    @Override
    public void resize(int width, int height)
    {
    }

    @Override
    public void pause()
    {
    }

    @Override
    public void resume()
    {
    }

    @Override
    public void hide()
    {
    }

    @Override
    public void dispose()
    {
    }

}
