/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
import com.minecraft.game.screens.PlayScreen;
import tools.Constant;
import tools.HandleInput;
import tools.VirtualController;

/**
 *
 * @author Karen
 */
public class Player extends Sprite{
    private World world;
    private Body body;
    
    private VirtualController controller;
    private HandleInput processor;
    
    private boolean isJumping;
    private Constant.state lastKeyPressed;
    private Constant.state currentState;
    
    private TextureRegion standing;
    private TextureRegion jumping;
    private Animation walking;
    private float duration;

    public Player(PlayScreen screen, int x, int y, String color) {
        super(screen.getAtlas().findRegion(color));
        world = screen.getWorld();
        
        /********************************************************************************************************************************************/
        
        TextureRegion sheetPlayer = screen.getAtlas().findRegion(color);
        TextureRegion[][] region = sheetPlayer.split(Constant.PLAYER_WIDTH / 4, Constant.PLAYER_HEIGHT);
        TextureRegion[] frames = new TextureRegion[region.length * region[0].length];int index = 0;

        //APLANANDO ARREGLO DE TEXTURES
        /*lo hice de esta forma porque pienso estructurar los sprites de una manera no lineal*/
        for (int i = 0; i < region.length; i++)
        {
            for (int j = 0; j < region[i].length; j++)
            {
                frames[index++] = region[i][j];
            }
        }
        walking = new Animation(0.15f, frames);
        standing = frames[3]; 
        jumping = frames[0];
        
        setBounds(0, 0, 128 / Constant.PPM, 128 / Constant.PPM);
        setRegion(standing);
        
        
        /********************************************************************************************************************************************/
        
        BodyDef bodyD = new BodyDef();
        bodyD.position.set(x, y);
        bodyD.type = BodyDef.BodyType.DynamicBody;
        body = this.world.createBody(bodyD);

        FixtureDef fixtureD = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() / 2 - 0.5f, getHeight() / 2);
        fixtureD.shape = shape;
        fixtureD.filter.categoryBits = Constant.PLAYER_BIT;
        fixtureD.filter.maskBits = Constant.GROUND_BIT | Constant.ESMERALD_BIT | Constant.FRUIT_BIT;
        body.createFixture(fixtureD).setUserData("player");

        EdgeShape feet = new EdgeShape();
        feet.set(getWidth() / -2 + 0.8f, getHeight() / -2, getWidth() / 2 - 0.8f, getHeight() / -2);
        fixtureD.shape = feet;
        fixtureD.isSensor = true;
        body.createFixture(fixtureD).setUserData("feet");
        
        /********************************************************************************************************************************************/
        
        controller = new VirtualController();
        processor = new HandleInput(controller);
        Gdx.input.setInputProcessor(processor);
        
        isJumping = false;
        lastKeyPressed = Constant.state.DEFAULT;
        duration = 0;
    }

    public Body getBody() {
        return body;
    }
    
    public boolean isJumping()
    {
        return isJumping;
    }
    
    public VirtualController getController() {
        return controller;
    }

    public void setIsJumping(boolean isJumping)
    {
        this.isJumping = isJumping;
    }

    public boolean isWalking()
    {
        return !isJumping;
    }
    
    
    /**********************************IGNORAR**********************************/

    public Constant.state getState() {
        if (body.getLinearVelocity().y > 0)
            return Constant.state.JUMPING;
        if (body.getLinearVelocity().y < 0)
            return Constant.state.FALLING;
        if (body.getLinearVelocity().x > 0)
            return Constant.state.WALK_RIGHT;
        if (body.getLinearVelocity().x < 0)
            return Constant.state.WALK_LEFT;
        return Constant.state.DEFAULT;
    }
    
    /***************************************************************************/
    
    public void act(float delta){
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        if (isJumping)
        {
            body.applyForceToCenter(0, Constant.IMPULSE_JUMP * -0.75f, true);
            setRegion(jumping);
        }
        
        if (controller.isUp()){
            jump();
        } else if (controller.isRight()){
            //if (lastKeyPressed == Constant.state.WALK_LEFT)
                //invertAnimation();
            lastKeyPressed = Constant.state.WALK_RIGHT;
            walk(1);
            walkAnimation(delta);
        } else if (controller.isLeft()){
            //if (lastKeyPressed == Constant.state.WALK_RIGHT)
               // invertAnimation();
            lastKeyPressed = Constant.state.WALK_LEFT;
            walk(-1);
            walkAnimation(delta);
        } else {
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
        if (!isJumping)
        {
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
            body.applyForce(((Constant.IMPULSE_JUMP - 8) * 0.6f) * direction, 0, position.x, position.y, true);
        }
    }
    
    public void walkAnimation(float delta)
    {
        if (!isJumping)
        {
            duration += delta;
            setRegion((TextureRegion) walking.getKeyFrame(duration, true));
        }
    }
    
    public void repose()
    {
        if (!isJumping)
        {
            setRegion(standing);
        }
    }

}
