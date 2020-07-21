package com.minecraft.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.minecraft.game.screens.GameScreen;

public class MainGame extends Game {
    public TextureAtlas manager;
    @Override
    public void create() {
        manager = new TextureAtlas("hojaSprites.atlas");
        setScreen(new GameScreen(this));          //MOSTRANDO LA PANTALLA DE JUEGO
    }  

    @Override
    public void render() {
        super.render();
    }
    
    
}
