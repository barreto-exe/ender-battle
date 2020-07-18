/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minecraft.bioma;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import static com.minecraft.bioma.Constants.HEIGHT;
import static com.minecraft.bioma.Constants.WIDTH;
import com.minecraft.bioma.actores.FloorEntity;
import com.minecraft.bioma.actores.PlayerActor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Karen
 */
public class GameScreen extends BaseScreen{
    //ATTRIBUTES
    private Stage stage;
    private World world;
    private OrthographicCamera gamecam;
    private Viewport viewport;
    private PlayerActor player;
    private List<FloorEntity> floorList = new ArrayList<>();
    
    
    
    //BUILDER
    public GameScreen(MainGame game) {
        super(game);
        gamecam =  new OrthographicCamera();
        viewport = new FitViewport(1000, 500, gamecam); 
        stage = new Stage(viewport);  //INSTANCIANDO EL STAGE
        world = new World(new Vector2(0, -10), true);  //INSTANCIANDO EL MUNDO (BOX2D)
       
        //EVALUANDO CONTACTOS ENTRE LOS BODYS DE LOS ACTORES EN EL STAGE
        world.setContactListener(new ContactListener(){
            
            //FUNCIÓN QUE INDICA SI DOS FIXTURES ESTÁN EN CONTACTO
            private boolean inContact(Contact contact, Object a, Object b){
                return (contact.getFixtureA().getUserData().equals(a) && contact.getFixtureB().getUserData().equals(b)
                     || contact.getFixtureA().getUserData().equals(b) && contact.getFixtureB().getUserData().equals(a));
            }
                    
                    
            @Override
            public void beginContact(Contact contact) {
                if (inContact(contact, "player", "floor")){
                    player.setIsJumping(false);    //SI PISO ESTÁ EN CONTACTO CON EL JUGADOR, NO ESTÁ SALTANDO
                }
            }

            @Override
            public void endContact(Contact contact) {
                if (!inContact(contact, "player", "floor")){
                    player.setIsJumping(true);    //SI PISO NO ESTÁ EN CONTACTO CON EL JUGADOR, ESTÁ SALTANDO
                }
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
                
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
                
            }
            
        });
    }

    @Override
    public void show() {
        TextureRegion region = game.manager.findRegion("Walking");  //ASIGNANDO LA REGION DE SPRITES DEL JUGADOR
        player = new PlayerActor(new TextureRegion(region, 0, 0, WIDTH, HEIGHT),   //INSTANCIANDO UN ACTOR JUGADOR
                world, new Vector2(1.5f, 1.5f));     //LE PASO COMO PARÁMETROS EL MUNDO DONDE SE ENCUENTRA, LA TEXTURA Y EL VECTOR POSICIÓN
        
        floorList.add(new FloorEntity(game.manager.findRegion("Dirt"), game.manager.findRegion("Grass"),
                world, 0, 1, 1000));     //INICIALIZANDO LAS PORCIONES DE SUELO EN EL ARRAY
        
        stage.addActor(player);    //AÑADIENDO ACTOR JUGADOR AL STAGE
        
        for (FloorEntity floor: floorList){
            stage.addActor(floor);     //AÑADIENDO CADA PORCION DE SUELO AL STAGE
        }
    }

    @Override
    public void hide() {
        player.delete();  //ELIMINA DEL MUNDO EL BODY Y FIXTURE DEL JUGADOR
        player.remove();  //ELIMINA A JUGADOR DEL STAGE
        for (FloorEntity floor: floorList){
            floor.delete();     //ELIMINA DEL MUNDO EL BODY Y FIXTURE DE CADA PORCION DE SUELO
            floor.remove();     //ELIMINA CADA PORCION DE SUELO DEL STAGE
        }
    }

    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.5f , 0.8f, 0.8f);  //COLOREA EL CIELO
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);     //LIMPIA EL BUFFER
         
        gamecam.position.set(player.getX(),player.getY(),0);
        gamecam.update();
        stage.act();   //ACTUALIZANDO EL STAGE Y SUS ACTORES
        world.step(delta, 6, 2);   //ACTUALIZANDO EL MUNDO Y SUS INTERACCIONES
        
        stage.draw();    //DIBUJANDO EL STAGE EN PANTALLA
    }
        

    
    @Override
    public void dispose() {
        stage.dispose();    //LIBERANDO STAGE
        world.dispose();    //LIBERANDO EL MUNDO
    }
    
    
}
