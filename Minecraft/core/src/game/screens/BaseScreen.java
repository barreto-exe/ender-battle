/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    protected static MainGame game;
    private TextureAtlas atlas;

    //BUILDER
    public BaseScreen(MainGame game)
    {
        atlas = new TextureAtlas("atlas.atlas");
        this.game = game;
    }

    public TextureAtlas getAtlas()
    {
        return atlas;
    }

    public static MainGame getGame()
    {
        return game;
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
