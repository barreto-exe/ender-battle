/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minecraft.bioma;

import com.badlogic.gdx.Screen;

/**
 *
 * @author Karen
 */

//CLASE DE DONDE EXTENDERÁN TODAS LAS PANTALLAS DEL JUEGO
public abstract class BaseScreen implements Screen{
    //ATTRIBUTES
    protected MainGame game;

    //BUILDER
    public BaseScreen(MainGame game) {
        this.game = game;
    }
      
    @Override
    public void show() { }

    @Override
    public void render(float delta) { }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() { }
    
}
