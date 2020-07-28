package com.minecraft.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.minecraft.game.screens.PlayScreen;

public class MainGame extends Game {
    private SpriteBatch batch;

    public SpriteBatch getBatch() {
        return batch;
    }
    
    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new PlayScreen(this, "bioma1.tmx", "caminar"));          //MOSTRANDO LA PANTALLA DE JUEGO
    }  

    @Override
    public void render() {
        super.render();
    }
    
    
}
