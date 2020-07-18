package com.minecraft.bioma;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class MainGame extends Game {
    //ATTRIBUTES
    public TextureAtlas manager;   //Hoja de sprites   
    
    @Override
    public void create() {
        manager = new TextureAtlas("hojaSprites.atlas");  //INSTANCIANDO HOJA DE SPRITES
        setScreen(new GameScreen(this));          //MOSTRANDO LA PANTALLA DE JUEGO
    }  
}
