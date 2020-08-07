package game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import game.MainGame;

/**
 * @author Karen
 */
public abstract class BaseScreen implements Screen
{
    //ATTRIBUTES
    protected MainGame game;
    private TextureAtlas atlas;
    private TextureAtlas plantas;

    //BUILDER
    public BaseScreen(MainGame game)
    {
        atlas = new TextureAtlas("atlas.atlas");
        plantas = new TextureAtlas("plantas.atlas");
        this.game = game;
    }

    public TextureAtlas getAtlas()
    {
        return atlas;
    }

    public TextureAtlas getPlantas() {
        return plantas;
    }

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
