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
    private State previousState;
    private State currentState;
    private boolean isJumping;
    
    //Atributos de Textura
    private TextureRegion[] frames;
    private Animation animation;
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
        animation = new Animation(0.15f, frames);
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
        fixtureD.filter.maskBits = Constant.GROUND_BIT | Constant.ESMERALD_BIT | Constant.FRUIT_BIT | Constant.MOB_BIT;
        body.createFixture(fixtureD).setUserData("player");
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Definición Fixture Pies">
        EdgeShape feet = new EdgeShape();
        feet.set(getWidth() / -2 + 0.9f, getHeight() / -2, getWidth() / 2 - 0.9f, getHeight() / -2);
        fixtureD.shape = feet;
        fixtureD.filter.categoryBits = Constant.PLAYER_FEET_BIT;
        fixtureD.filter.maskBits = Constant.GROUND_BIT;        
        fixtureD.isSensor = true;
        body.createFixture(fixtureD).setUserData("feet");
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Controladores">
        controller = new VirtualController();
        processor = new HandleInput(controller);
        Gdx.input.setInputProcessor(processor);
        //</editor-fold>

        isJumping = false;
        previousState = State.WALKING_RIGHT;
        duration = 0;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public Body getBody()
    {
        return body;
    }
    
    public VirtualController getController()
    {
        return controller;
    }
    
    public State getState() {
        if (body.getLinearVelocity().y >= 0.02f)
        {
            return State.JUMPING;
        }
        else if (body.getLinearVelocity().y <= -0.02f)
        {
            return State.FALLING;
        }
        else if (controller.isHitting())
        {
            return State.HITTING;
        }
        else if (body.getLinearVelocity().x >= 1 && controller.isRight())
        {
            return State.WALKING_RIGHT;
        }
        else if (body.getLinearVelocity().x <= -1 && controller.isLeft())
        {
            return State.WALKING_LEFT;
        } 
        else 
        {
            return State.STANDING;
        }
    }
    
    public TextureRegion getFrame(float delta){
        TextureRegion frame = frames[3];
        duration +=delta;
        
        if (null != currentState)
            switch (currentState) 
            {
                case JUMPING:
                case FALLING:
                    frame = frames[0];
                    break;
                case HITTING:
                    break;
                case WALKING_RIGHT:
                    if (previousState == Constant.State.WALKING_LEFT)
                    {
                        invertAnimation();
                    }   
                    previousState = Constant.State.WALKING_RIGHT;
                    frame = (TextureRegion) animation.getKeyFrame(duration, true);
                    break;
                case WALKING_LEFT:
                    if (previousState == Constant.State.WALKING_RIGHT)
                    {
                        invertAnimation();
                    }   
                    previousState = Constant.State.WALKING_LEFT;
                    frame = (TextureRegion) animation.getKeyFrame(duration, true);
                    break;
            }
        
        return frame;
    }

    public void setIsJumping(boolean isJumping) {
        this.isJumping = isJumping;
    }
    
    //</editor-fold>
    
    @Override
    public void act(float delta)
    {
        currentState = getState();
        
        if (currentState == State.FALLING || currentState == State.JUMPING)
        {
            body.applyForceToCenter(0, Constant.IMPULSE_JUMP * -0.75f, true);
        }

        if (controller.isUp() && !isJumping)
        {
            jump();
        } 
        else if (controller.isRight())
        {
            walk(1);
        } 
        else if (controller.isLeft())
        {
            walk(-1);
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
        }
        
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(delta));
    }
    
    private void jump()
    {
        if (!(currentState == State.FALLING || currentState == State.JUMPING))
        {
            isJumping = true;
            body.applyLinearImpulse(0, Constant.IMPULSE_JUMP, body.getWorldCenter().x, body.getWorldCenter().y, true);
        }
    }

    public void walk(int direction)
    {
        if (!(currentState == State.FALLING || currentState == State.JUMPING))
        {
            body.setLinearVelocity(direction * Constant.SPEED_PLAYER, 0);   //SE APLICA FUERZA HORIZONTAL QUE GENERA EL MOVIMIENTO DE CAMINAR
        } else
        {
            body.applyForce(((Constant.IMPULSE_JUMP - 12) * 0.6f) * direction, 0, body.getWorldCenter().x, body.getWorldCenter().y, true);
        }
    }

    public void invertAnimation()
    {
        for (int i = 0; i < frames.length; i++)
        {
            frames[i].flip(true, false);  //Haciendo flip a cada frame
        }
    }
}
