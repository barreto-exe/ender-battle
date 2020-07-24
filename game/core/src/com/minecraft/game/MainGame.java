package com.minecraft.game;

import com.badlogic.gdx.Game;
import com.minecraft.game.screens.GameScreen;

public class MainGame extends Game {
    @Override
    public void create() {
        setScreen(new GameScreen(this, "bioma1.tmx"));          //MOSTRANDO LA PANTALLA DE JUEGO
    }  

    @Override
    public void render() {
        super.render();
    }
    
    
}
