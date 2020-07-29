/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actors;

import actors.groups.Actor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import game.screens.GameScreen;
import tools.Constant;
import tools.Constant.*;
import tools.HandleInput;
import tools.VirtualController;

/**
 *
 * @author Karen
 */
public class Player extends Sprite implements Actor
{
    //<editor-fold defaultstate="collapsed" desc="Atributos">
    //Atributos de Box2d
    private World world;
    private Body body;
    
    //Atributos de Controladores
    private VirtualController controller;
    private HandleInput processor;
    private boolean isJumping;
    private State lastKeyPressed;
    private int toquesSuelo = 0;
    
    //Atributos de Textura
    private TextureRegion[] frames;
    private Animation animation;
    private Animation walkingRight;
    private Animation walkingLeft;
    private float duration;
    //</editor-fold>

    /**
     * Objeto que controla y dibuja al protagonista del juego en pantalla.
     * @param screen la pantalla en la que se está mostrando el jugador.
     * @param x posición horizontal de inicio.
     * @param y posición vertial de inicio.
     * @param color representa el sprite elegido por el jugador.
     */
    public Player(GameScreen screen, int x, int y, String color)
    {
        super(screen.getAtlas().findRegion(color));
        world = screen.getWorld();

        //<editor-fold defaultstate="collapsed" desc="Definición de Animación">
        TextureRegion sheetPlayer = screen.getAtlas().findRegion(color);
        TextureRegion[][] region = sheetPlayer.split(Constant.PLAYER_WIDTH / 4, Constant.PLAYER_HEIGHT);
        frames = new TextureRegion[region.length * region[0].length];
        int index = 0;

        //APLANANDO ARREGLO DE TEXTURES
        /*lo hice de esta forma porque pienso estructurar los sprites de una manera no lineal*/
        for (int i = 0; i < region.length; i++)
        {
            for (int j = 0; j < region[i].length; j++)
            {
                frames[index++] = region[i][j];
            }
        }
        walkingRight = new Animation(0.15f, frames);

        for (int i = 0; i < frames.length; i++)
        {
            frames[i].flip(true, false);  //Haciendo flip a cada frame
        }
        walkingLeft = new Animation(0.15f, frames);

        invertAnimation(State.WALK_LEFT);
        //</editor-fold>

        //Colocar posición
        setBounds(0, 0, 128 / Constant.PPM, 128 / Constant.PPM);
        
        //Colocar frame en reposo
        setRegion(frames[3]);

        //<editor-fold defaultstate="collapsed" desc="Definición de Body">
        BodyDef bodyD = new BodyDef();
        bodyD.position.set(x, y);
        bodyD.type = BodyDef.BodyType.DynamicBody;
        body = this.world.createBody(bodyD);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Definición de Fixture Cuerpo">
        FixtureDef fixtureD = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() / 2 - 0.8f, getHeight() / 2);
        fixtureD.shape = shape;
        fixtureD.filter.categoryBits = Constant.PLAYER_BIT;
        fixtureD.filter.maskBits = Constant.GROUND_BIT | Constant.ESMERALD_BIT | Constant.FRUIT_BIT;
        body.createFixture(fixtureD).setUserData("player");
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Definición Fixture Pies">
        EdgeShape feet = new EdgeShape();
        feet.set(getWidth() / -2 + 0.9f, getHeight() / -2, getWidth() / 2 - 0.9f, getHeight() / -2);
        fixtureD.shape = feet;
        fixtureD.isSensor = true;
        body.createFixture(fixtureD).setUserData("feet");
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Controladores">
        controller = new VirtualController();
        processor = new HandleInput(controller);
        Gdx.input.setInputProcessor(processor);
        //</editor-fold>

        isJumping = false;
        lastKeyPressed = State.DEFAULT;
        duration = 0;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public Body getBody()
    {
        return body;
    }
    
    public boolean isJumping()
    {
        return isJumping;
    }
    
    public VirtualController getController()
    {
        return controller;
    }
    
    public void setIsJumping(boolean isJumping)
    {
        this.isJumping = isJumping;
        
        if(!isJumping)
        {
            toquesSuelo++;
            if(toquesSuelo>2)
            {
                toquesSuelo = 2;
            }
        }
    }
    
    public boolean isWalking()
    {
        return !isJumping;
    }
    //</editor-fold>

    @Override
    public void act(float delta)
    {
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        if (isJumping)
        {
            body.applyForceToCenter(0, Constant.IMPULSE_JUMP * -0.75f, true);
            setRegion(frames[0]);
        }

        if (controller.isUp())
        {
            jump();
        } 
        else if (controller.isRight())
        {
            if (lastKeyPressed == Constant.State.WALK_LEFT)
            {
                invertAnimation(Constant.State.WALK_RIGHT);
            }
            lastKeyPressed = Constant.State.WALK_RIGHT;
            walk(1);
            walkAnimation(delta);
        } 
        else if (controller.isLeft())
        {
            if (lastKeyPressed == Constant.State.WALK_RIGHT)
            {
                invertAnimation(Constant.State.WALK_LEFT);
            }
            lastKeyPressed = Constant.State.WALK_LEFT;
            walk(-1);
            walkAnimation(delta);
        }
        else
        {
            if (body.getLinearVelocity().x < 0)
            {
                body.applyForceToCenter(8, 0, true);
            } else if (body.getLinearVelocity().x > 0)
            {
                body.applyForceToCenter(-8, 0, true);
            }
            repose();
        }
    }
    
    private void jump()
    {
        if (!isJumping && toquesSuelo == 2)
        {
            toquesSuelo = 0;
            isJumping = true;
            body.applyLinearImpulse(0, Constant.IMPULSE_JUMP, body.getWorldCenter().x, body.getWorldCenter().y, true);
        }
    }

    public void walk(int direction)
    {
        Vector2 position = body.getPosition();   //SE ACTUALIZA EL VECTOR POSICION A LA POSICION ACTUAL DEL BODY
        if (!isJumping)
        {
            body.setLinearVelocity(direction * Constant.SPEED_PLAYER, 0);   //SE APLICA FUERZA HORIZONTAL QUE GENERA EL MOVIMIENTO DE CAMINAR
        } else
        {
            body.applyForce(((Constant.IMPULSE_JUMP - 12) * 0.6f) * direction, 0, position.x, position.y, true);
        }
    }

    public void walkAnimation(float delta)
    {
        if (!isJumping)
        {
            duration += delta;
            setRegion((TextureRegion) animation.getKeyFrame(duration, true));
        }
    }

    public void repose()
    {
        if (!isJumping)
        {
            setRegion(frames[3]);
        }
    }

    public void invertAnimation(State flip)
    {
        for (int i = 0; i < frames.length; i++)
        {
            frames[i].flip(true, false);  //Haciendo flip a cada frame
        }
        if (flip == Constant.State.WALK_LEFT)
        {
            animation = walkingRight;
        }
        if (flip == Constant.State.WALK_RIGHT)
        {
            animation = walkingLeft;
        }
    }
}
