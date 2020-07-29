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
    private boolean isHitting;
    private State lastKeyPressed;
    private int toquesSuelo;
    
    //Atributos de Textura
    private TextureRegion[] frames;
    private TextureRegion[] framesPunch;
    private Animation animation;
    private Animation animationHit;
    private Animation walkingRight;
    private Animation hitRight;
    private Animation walkingLeft;
    private Animation hitLeft;
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

        //<editor-fold defaultstate="collapsed" desc="Definición de Animación Caminar">
        TextureRegion sheetPlayer = screen.getAtlas().findRegion(color);
        TextureRegion[][] region = sheetPlayer.split(Constant.PLAYER_WIDTH / 4, Constant.PLAYER_HEIGHT);
        frames = new TextureRegion[region.length * region[0].length];

        int index = 0;
        //APLANANDO ARREGLO DE TEXTURES
        for (TextureRegion[] regionFil : region)
        {
            for (TextureRegion regionCol : regionFil)
            {
                frames[index++] = regionCol;
            }
        }
        walkingRight = new Animation(0.15f, frames);

        for (TextureRegion frame : frames)
        {
            frame.flip(true, false); 
        }
        walkingLeft = new Animation(0.15f, frames);
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Definición de Animación Puño">
        TextureRegion sheetPunch = screen.getAtlas().findRegion("golpear");
        region = sheetPunch.split(Constant.PLAYER_WIDTH / 4, Constant.PLAYER_HEIGHT);
        framesPunch = new TextureRegion[region.length * region[0].length];
        index = 0;
        //APLANANDO ARREGLO DE TEXTURES
        for (TextureRegion[] regionFil : region)
        {
            for (TextureRegion regionCol : regionFil)
            {
                framesPunch[index++] = regionCol;
            }
        }
        hitRight = new Animation(0.08f, framesPunch);

        for (TextureRegion frame : framesPunch)
        {
            frame.flip(true, false);
        }
        hitLeft = new Animation(0.08f, framesPunch);
        //</editor-fold>
        
        invertAnimation(State.WALK_LEFT);
        
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
        lastKeyPressed = State.WALK_RIGHT;
        duration = 0;
        toquesSuelo = 0;
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

    public boolean isPunching()
    {
        return isHitting;
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

        if(controller.isHitting())
        {
            hit();
        }
        
        if (controller.isUp())
        {
            jump();
        } 
        else if (controller.isRight())
        {
            if (lastKeyPressed == State.WALK_LEFT)
            {
                invertAnimation(State.WALK_RIGHT);
            }
            lastKeyPressed = State.WALK_RIGHT;
            walk(1);
            walkAnimation(delta);
        } 
        else if (controller.isLeft())
        {
            if (lastKeyPressed == State.WALK_RIGHT)
            {
                invertAnimation(State.WALK_LEFT);
            }
            lastKeyPressed = State.WALK_LEFT;
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
            repose(delta);
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

    private float deltaHit = -1;
    private final float DURACION_HIT = 0.08f * 4;
    
    private final boolean animatingHit()
    {
        boolean result = deltaHit >= 0 && deltaHit <= DURACION_HIT;
        //if(result) System.out.println(deltaHit);
        return result;
    }
    
    public void hit()
    {
        if(!animatingHit())
        {
            this.isHitting = true;
            deltaHit = 0;
        }
    }
    
    public void hitAnimation(float delta)
    {
        isHitting = false;
        deltaHit += delta;
        System.out.println(deltaHit);
        setRegion((TextureRegion) animationHit.getKeyFrame(deltaHit, true));

        if(deltaHit > DURACION_HIT)
        {
            deltaHit = -1;
            //walkAnimation(delta);
        }
    }
    public void repose(float delta)
    {
        if (!isJumping)
        {
            setRegion(frames[3]);
            
            if(animatingHit())
            {
                hitAnimation(delta);
            }
        }
    }
    
    public void walkAnimation(float delta)
    {
        if (!isJumping)
        {
            duration += delta;
            
            if(!animatingHit())
            {
                setRegion((TextureRegion) animation.getKeyFrame(duration, true));
            }
            else 
            {
                hitAnimation(delta);
            }
        }
    }

    public void invertAnimation(State flip)
    {
        for(int i = 0; i < frames.length; i++)
        {
            frames[i].flip(true, false); 
            framesPunch[i].flip(true, false); 
        }
        
        if (flip == State.WALK_LEFT)
        {
            animation = walkingRight;
            animationHit = hitRight;
        }
        if (flip == State.WALK_RIGHT)
        {
            animation = walkingLeft;
            animationHit = hitLeft;
        }
    }
}
