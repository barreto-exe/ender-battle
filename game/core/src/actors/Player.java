/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actors;

import actors.groups.Actor;
import actors.pacific.Mob;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import game.screens.GameScreen;
import inventario.BattleObject;
import inventario.Inventory;
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
    private boolean isHitting;
    private boolean canAttack;
    private Mob enemy;

    //Atributos de Textura
    private Array<TextureRegion> walkFrames;
    private Array<TextureRegion> punchFrames;
    private Animation walkAnimation;
    private Animation punchAnimation;
    private float deltaFrame;
    private float deltaHit;
    
    //Atributos de Información
    private final String color;
    private float life;
    
    //Atributos de Inventario
    private Inventory inventory;
    private BattleObject[] portedObjects;
    //</editor-fold>

    /**
     * Instaciando al player con el color de ropa.
     *
     * @param color representa el ropa elegido por el jugador antes de iniciar partida.
     */    
    public Player(String color) {
        //informacion del jugador
        this.color = color;
        
        //instanciando inventario vacío
        inventory = new Inventory();
        portedObjects = new BattleObject[5];
        for (int i = 0; i < 5; i++)
        {
            portedObjects[i] = null;
        }
    }
    
    /**
     * Método que controla y dibuja al protagonista del juego en pantalla.
     *
     * @param screen la pantalla en la que se está mostrando el jugador.
     */ 
    public void create(GameScreen screen){
        setRegion(screen.getAtlas().findRegion(color+"_caminar"));
        world = screen.getWorld();

        //<editor-fold defaultstate="collapsed" desc="Definición de Animación "Caminar"">
        TextureRegion spriteSheet = screen.getAtlas().findRegion(color+"_caminar");
        TextureRegion[][] region = spriteSheet.split(Constant.PLAYER_WIDTH / 4, Constant.PLAYER_HEIGHT);
        walkFrames = new Array<>();

        //APLANANDO ARREGLO DE TEXTURES
        for (TextureRegion[] regionF : region)
        {
            for (TextureRegion regionC : regionF)
            {
                walkFrames.add(regionC);
            }
        }

        walkAnimation = new Animation(0.15f, walkFrames);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Definición de Animación "Golpear"">
        spriteSheet = screen.getAtlas().findRegion(color+"_golpear");
        region = spriteSheet.split(768 / 6, Constant.PLAYER_HEIGHT);
        punchFrames = new Array<>();

        //APLANANDO ARREGLO DE TEXTURES
        for (TextureRegion[] regionF : region)
        {
            for (TextureRegion regionC : regionF)
            {
                punchFrames.add(regionC);
            }
        }

        punchAnimation = new Animation(0.06f, punchFrames);
        //</editor-fold>

        //Colocar posición
        setBounds(0, 0, 128 / Constant.PPM, 128 / Constant.PPM);

        //Colocar frame en reposo
        setRegion(walkFrames.get(3));

        //<editor-fold defaultstate="collapsed" desc="Definición de Body">
        BodyDef bodyD = new BodyDef();
        bodyD.position.set(3, 2);
        bodyD.type = BodyDef.BodyType.DynamicBody;
        body = this.world.createBody(bodyD);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Definición de Fixture Cuerpo">
        FixtureDef fixtureD = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() / 2 - 0.8f, getHeight() / 2);
        fixtureD.shape = shape;
        fixtureD.filter.categoryBits = Constant.PLAYER_BIT;
        fixtureD.filter.maskBits = Constant.GROUND_BIT | Constant.ESMERALD_BIT | Constant.FRUIT_BIT | Constant.MOB_BIT | Constant.MOB_SENSOR_BIT;
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

        isJumping = isHitting = canAttack = false;
        previousState = State.WALKING_RIGHT;
        deltaFrame = 0;
        enemy = null;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public Body getBody()
    {
        return body;
    }

    public Inventory getInventory() {
        return inventory;
    }
    
    public VirtualController getController()
    {
        return controller;
    }

    public float getLife() {
        return life;
    }

    public void setLife(float life) {
        this.life = life;
    }
    
    public TextureRegion getHitFrame(float delta)
    {
        deltaHit += delta;
        if (deltaHit > (0.06f * 6))
        {
            isHitting = false;
        }
        
        if (canAttack && enemy != null)
        {
            toHurt(enemy);
            canAttack = false;
        }

        return (TextureRegion) punchAnimation.getKeyFrame(deltaHit, true);
    }

    public State getState()
    {
        if (controller.isHitting() && !isHitting)
        {
            isHitting = true;
            canAttack = true;
            deltaHit = 0;
        }

        if (isHitting)
        {
            return State.HITTING;
        } 
        else if (body.getLinearVelocity().y >= 0.02f)
        {
            return State.JUMPING;
        }
        else if (body.getLinearVelocity().y <= -0.02f)
        {
            return State.FALLING;
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

    public TextureRegion getFrame(float delta)
    {
        TextureRegion frame = walkFrames.get(3);
        deltaFrame += delta;

        if (null != currentState)
        {
            switch (currentState)
            {
                case JUMPING:
                case FALLING:
                    frame = walkFrames.get(0);
                    break;
                case HITTING:
                    frame = getHitFrame(delta);
                    break;
                case WALKING_RIGHT:
                    if (previousState == Constant.State.WALKING_LEFT)
                    {
                        invertAnimation();
                    }
                    previousState = Constant.State.WALKING_RIGHT;
                    frame = (TextureRegion) walkAnimation.getKeyFrame(deltaFrame, true);
                    break;
                case WALKING_LEFT:
                    if (previousState == Constant.State.WALKING_RIGHT)
                    {
                        invertAnimation();
                    }
                    previousState = Constant.State.WALKING_LEFT;
                    frame = (TextureRegion) walkAnimation.getKeyFrame(deltaFrame, true);
                    break;
            }
        }

        return frame;
    }

    public void setIsJumping(boolean isJumping)
    {
        this.isJumping = isJumping;
    }

    public void canAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    public void setEnemy(Mob enemy) {
        this.enemy = enemy;
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
            } 
            else if (body.getLinearVelocity().x > 0)
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
        for (TextureRegion frame : walkFrames)
        {
            frame.flip(true, false);
        }

        for (TextureRegion frame : punchFrames)
        {
            frame.flip(true, false);
        }
    }
    
    public void portarObjeto(BattleObject object)
    {
        int index = 0;
        
        switch (object.getDescription()){
            case ("espada"):
            case ("hacha"):
            case ("pico"):
            case ("pala"):
                index = 0;
                break;
            case ("casco"):
                index = 1;
                break;
            case ("pecho"):
                index = 2;
                break;
            case ("pantalon"):
                index = 3;
                break;
            case ("botas"):
                index = 4;
                break;
        }
        
        if (portedObjects[index] != null)
        {
            portedObjects[index].setIsPorted(false);
        }
        
        object.setIsPorted(true);
        portedObjects[index] = object;
    }
    
    private float calculateDamage()
    {
        if (portedObjects[0] != null)
        {
            return portedObjects[0].getFactorObject();
        }
        else
        {
            return 1;
        }
    }
    
    public void toHurt(Mob mob)
    {
        if (mob != null)
        {
            mob.toRecibeAttack(this, calculateDamage());
        }
        
    }
}
