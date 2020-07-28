package com.minecraft.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.minecraft.game.screens.GameScreen;

public class MainGame extends Game {
    private SpriteBatch batch;

    public SpriteBatch getBatch() {
        return batch;
    }
    
    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new GameScreen(this, "bioma1.tmx"));          //MOSTRANDO LA PANTALLA DE JUEGO
    }  

    @Override
    public void render() {
        super.render();
    }
    
    
}
