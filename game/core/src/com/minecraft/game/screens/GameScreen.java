/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minecraft.game.screens;

import actors.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.minecraft.game.Constant;
import com.minecraft.game.MainGame;
import com.minecraft.game.screens.worlds.BiomeAssembler;

/**
 *
 * @author Karen
 */
public class GameScreen extends BaseScreen
{

    private Stage stage;
    private OrthographicCamera gameCam;
    private Viewport viewport;

    private Box2DDebugRenderer debugger;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Player player;

    /*CREO QUE RECIBE UN ARRAYLIST DEL SERVER CON LOS JUGADORES DE LA PARTIDA EN CURSO*/
    public GameScreen(MainGame game, String biome)
    {
        super(game);
        stage = new Stage();
        gameCam = new OrthographicCamera();
        viewport = new FitViewport(Constant.FRAME_WIDTH / Constant.PPM, Constant.FRAME_HEIGHT / Constant.PPM, gameCam);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load(biome);
        renderer = new OrthogonalTiledMapRenderer(map, 1 / Constant.PPM);
        gameCam.position.set(Constant.FRAME_WIDTH / 2 / Constant.PPM, Constant.FRAME_HEIGHT / 2 / Constant.PPM, 0);
        world = new World(new Vector2(0, -10), true);

        debugger = new Box2DDebugRenderer();
        new BiomeAssembler(world, map);

        world.setContactListener(new ContactListener()
        {

            //FUNCIÓN QUE INDICA SI DOS FIXTURES ESTÁN EN CONTACTO
            private boolean inContact(Contact contact, Object a, Object b)
            {
                return (contact.getFixtureA().getUserData().equals(a) && contact.getFixtureB().getUserData().equals(b)
                        || contact.getFixtureA().getUserData().equals(b) && contact.getFixtureB().getUserData().equals(a));
            }

            @Override
            public void beginContact(Contact contact)
            {
                boolean tocandoBloque = inContact(contact, "feet", "overfloor") || inContact(contact, "player", "overfloor");

                if (tocandoBloque || player.getIsJumping())
                {
                    player.setIsJumping(false);
                }
            }

            @Override
            public void endContact(Contact contact)
            {
                boolean tocandoSueloPies = inContact(contact, "feet", "overfloor");
                boolean caminando = player.isWalking();

                if (caminando && tocandoSueloPies)
                {
                    player.setIsJumping(true);
                }
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold)
            {
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse)
            {
            }

        });
    }

    @Override
    public void show()
    {
        player = new Player(world, getAtlas().findRegion("caminar"), new Vector2(128 / Constant.PPM, 128 / Constant.PPM));
        stage.addActor(player);
    }

    public World getWorld()
    {
        return world;
    }

    /*public void uptadte(float delta){
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            player.getBody().applyLinearImpulse(new Vector2(0, 4f), player.getBody().getWorldCenter(), true);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.getBody().getLinearVelocity().x <= 2){
            player.getBody().applyLinearImpulse(new Vector2(0.1f, 0), player.getBody().getWorldCenter(), true);
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.getBody().getLinearVelocity().x >= -2){
            player.getBody().applyLinearImpulse(new Vector2(-0.1f, 0), player.getBody().getWorldCenter(), true);
        }
    }*/
    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height);
    }

    @Override
    public void render(float delta)
    {
        //uptadte(delta);
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 0.8f);  //COLOREA EL CIELO
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);     //LIMPIA EL BUFFER

        renderer.setView(gameCam);
        renderer.render();
        stage.act();
        world.step(delta, 6, 2);
        stage.draw();
        debugger.render(world, gameCam.combined);
        gameCam.position.x = player.getBody().getPosition().x;
        gameCam.update();
    }

    @Override
    public void dispose()
    {
        map.dispose();
        renderer.dispose();
        world.dispose();
        debugger.dispose();
        stage.dispose();
    }

}
