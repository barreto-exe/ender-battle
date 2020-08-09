package game.actors;

import game.actors.groups.Actor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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
import game.inventario.BattleObject;
import game.inventario.Inventory;
import game.actors.collectibles.FoodCollectible;
import game.actors.farming.plants.Plant;
import com.badlogic.gdx.graphics.Color;
import game.actors.collectibles.BattleObjectCollectible;
import game.actors.collectibles.EsmeraldCollective;
import game.actors.collectibles.ObjectCollectible;
import game.tools.Constant;
import game.tools.Constant.*;
import game.tools.HandleInput;
import static game.tools.Sonido.soundManager;
import game.tools.VirtualController;

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
    private boolean setToAttack;
    private Mob enemy;
    private ObjectCollectible objectCollectible;
    private Plant plant;

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
    private PlayerCondition condition;
    float timeCondition = 0;
    private int direction;

    //Atributos de Inventario
    private Inventory inventory;
    private BattleObject[] portedObjects;
    
    //</editor-fold>

    /**
     * Instaciando al player con el color de ropa.
     *
     * @param color representa el ropa elegido por el jugador antes de iniciar
     * partida.
     */
    public Player(String color)
    {
        //informacion del jugador
        this.color = color;

        this.life = 100;

        //instanciando inventario vacío
        inventory = new Inventory();
        portedObjects = new BattleObject[5];
        for (int i = 0; i < 5; i++)
        {
            portedObjects[i] = null;
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public Body getBody()
    {
        return body;
    }

    public Inventory getInventory()
    {
        return inventory;
    }

    public VirtualController getController()
    {
        return controller;
    }

    public float getLife()
    {
        return life;
    }

    public void setLife(float life)
    {
        this.life = life;
    }

    public void addLife(float lifeAdded)
    {
        System.out.println("tenía de vida: " + life);

        life += lifeAdded;

        if (life > 100)
        {
            life = 100;
        }

        System.out.println("ahora tiene de vida: " + life);
    }

    public TextureRegion getHitFrame(float delta)
    {
        deltaHit += delta;
        if (deltaHit > (0.06f * 6))
        {
            isHitting = false;
        }

        if (setToAttack && enemy != null)
        {
            toHurt(enemy);
            setToAttack = false;
        }

        if (setToAttack && plant != null)
        {
            toCrop(plant);
            setToAttack = false;
        }

        return (TextureRegion) punchAnimation.getKeyFrame(deltaHit, true);
    }

    public State getState()
    {
        if (controller.isHitting() && !isHitting)
        {
            isHitting = true;
            setToAttack = true;
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

    public void canAttack(boolean canAttack)
    {
        this.setToAttack = canAttack;
    }

    public Mob getEnemy()
    {
        return enemy;
    }

    public void setEnemy(Mob enemy)
    {
        this.enemy = enemy;
    }

    public void setObjectCollectible(ObjectCollectible food)
    {
        this.objectCollectible = food;
    }

    public void setPlant(Plant plant)
    {
        this.plant = plant;
    }

    public int getDirection()
    {
        return direction;
    }
    //</editor-fold>

    /**
     * Método que crea y dibuja al protagonista del juego en pantalla.
     *
     * @param screen la pantalla en la que se está mostrando el jugador.
     */
    public void create(GameScreen screen)
    {
        setRegion(screen.getAtlas().findRegion(color + "_caminar"));
        world = screen.getWorld();

        //<editor-fold defaultstate="collapsed" desc="Definición de Animación "Caminar"">
        TextureRegion spriteSheet = screen.getAtlas().findRegion(color + "_caminar");
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
        spriteSheet = screen.getAtlas().findRegion(color + "_golpear");
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
        fixtureD.filter.maskBits
                = Constant.GROUND_BIT | Constant.VILLAGER_BIT | Constant.OBJECT_BIT | Constant.MOB_BIT
                | Constant.MOB_SENSOR_BIT | Constant.MOB_TOP_BIT | Constant.TREE_BIT | Constant.ARROW_SENSOR_BIT;
        body.createFixture(fixtureD).setUserData(this);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Definición Fixture Pies">
        EdgeShape feet = new EdgeShape();
        feet.set(getWidth() / -2 + 0.9f, getHeight() / -2, getWidth() / 2 - 0.9f, getHeight() / -2);
        fixtureD.shape = feet;
        fixtureD.filter.categoryBits = Constant.PLAYER_FEET_BIT;
        fixtureD.filter.maskBits = Constant.GROUND_BIT;
        fixtureD.isSensor = true;
        body.createFixture(fixtureD).setUserData(this);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Controladores">
        controller = new VirtualController();
        processor = new HandleInput(controller, screen);
        Gdx.input.setInputProcessor(processor);
        //</editor-fold>

        isJumping = isHitting = setToAttack = false;
        previousState = State.WALKING_RIGHT;
        condition = PlayerCondition.NORMAL;
        deltaFrame = 0;
        enemy = null;
        objectCollectible = null;
        plant = null;
    }

    @Override
    public void act(float delta)
    {
        affectCondition(delta);

        //System.out.println("Life: " + life + " || " + condition.toString());
        if (life <= 0)
        {
            life = 0;
            System.out.println("El Jugador ha muerto");
        }

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
            direction = 1;
            walk(1);
        }
        else if (controller.isLeft())
        {
            direction = -1;
            walk(-1);
        }
        else if (controller.isPickingUp() && (objectCollectible != null))
        {
            toPickUp();
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

    /**
     * Aplica impulso al jugador para que salte.
     */
    private void jump()
    {
        if (!(currentState == State.FALLING || currentState == State.JUMPING || condition == PlayerCondition.ENTANGLED))
        {
            isJumping = true;
            body.applyLinearImpulse(0, Constant.IMPULSE_JUMP, body.getWorldCenter().x, body.getWorldCenter().y, true);
        }
    }

    /**
     * Aplica impulso al jugador para que camine.
     * @param direction 
     */
    public void walk(int direction)
    {
        if (!(currentState == State.FALLING || currentState == State.JUMPING))
        {
            body.setLinearVelocity(direction * Constant.SPEED_PLAYER, 0);
        }
        else
        {
            body.applyForce(((Constant.IMPULSE_JUMP - 12) * 0.6f) * direction, 0, body.getWorldCenter().x, body.getWorldCenter().y, true);
        }
    }

    /**
     * Invierte la animación del jugador.
     */
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

    /**
     * Añade un objeto al inventario del jugador.
     * @param object el que se añade al inventario.
     */
    public void portarObjeto(BattleObject object)
    {
        int index = 0;

        switch (object.getObject())
        {
            case SWORD:
            case AX:
            case PICK:
            case SHOVEL:
                index = 0;
                break;
            case HELMET:
                index = 1;
                break;
            case SHIRTFRONT:
                index = 2;
                break;
            case LEGGING:
                index = 3;
                break;
            case BOOTS:
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

    /**
     * Calcula el daño que hace el jugador de acuerdo al arma que porta.
     * @return cantidad de daño que hace.
     */
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

    /**
     * Hiere a un mob.
     * @param mob el que va a herirse.
     */
    public void toHurt(Mob mob)
    {
        if (mob != null)
        {
            mob.toRecibeAttack(this, calculateDamage());
        }
    }

    /**
     * Hace daño a una planta.
     * @param plant la que recibe el golpe.
     */
    public void toCrop(Plant plant)
    {
        if (plant != null)
        {
            soundManager.get("sonidos/arbolhit.ogg", Sound.class).play();
            plant.toRecibeAttack(this, calculateDamage());
        }
    }

    /**
     * Recibir un ataque.
     * @param hit catidad de daño del ataque.
     */
    public void toRecibeAttack(float hit)
    {
        life -= hit;
        
        soundManager.get("sonidos/hurt1.ogg", Sound.class).play();
        soundManager.get("sonidos/hurt2.ogg", Sound.class).play();
        
        final Player player = this;

        //Cantidad de segudos que permanece coloreado de rojo
        final float segundos = 1;

        //Colorear de rojo por haber sido herido
        player.setColor(Color.CORAL);

        //Lanzar thread que espera un segundo y lo colorea a la normalidad
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(((long) (segundos)) * 1000);
                    player.setColor(Color.WHITE);
                } catch (InterruptedException ex)
                {
                    System.out.println(ex.getMessage());
                }
            }

        }).start();
        
        if (!isJumping)
        {
            body.applyLinearImpulse(0, 3, body.getWorldCenter().x, body.getWorldCenter().y, true);
        }
    }

    /**
     * Recoge un objeto del suelo.
     */
    private void toPickUp()
    {
        objectCollectible.setIsCollected(true);
        
        if (objectCollectible instanceof FoodCollectible)
        {
            soundManager.get("sonidos/pick.ogg", Sound.class).play();
            inventory.addFood(((FoodCollectible) objectCollectible).getType());
        }
        else if (objectCollectible instanceof EsmeraldCollective)
        {
            soundManager.get("sonidos/esmeralda.ogg", Sound.class).play();
            inventory.setEsmeraldas(inventory.getEsmeraldas() + 1);
        }
        else if (objectCollectible instanceof BattleObjectCollectible)
        {
            inventory.addBattleObject(((BattleObjectCollectible) objectCollectible).getObject());
        }

        //liberar objectCollectible
    }

    /**
     * Le coloca al jugador un estado de envenenamiento por un monstruo. Cada
     * estado lo afectará de una forma u otra al pasar el tiempo.
     *
     * @param condition es la condicion a colocarle.
     * @param segundos cantidad de tiempo que se verá afectado el jugador.
     */
    public void setCondition(PlayerCondition condition, float segundos)
    {
        //Si hay otra afección en progreso, no hay que hacer más nada
        if (timeCondition > 0)
        {
            //(En este caso, el jugador no va a tener nunca doble envenenamiento. Pero, si quieren
            //pensar en una lógica para que sí lo tenga, eso no estaría mal).
            return;
        }

        this.condition = condition;

        //Colocar segundos de envenenamiento.
        timeCondition = segundos;
    }

    /**
     * Cura al jugador de cualquier afección.
     */
    public void curate()
    {
        setColor(Color.WHITE);
        this.condition = PlayerCondition.NORMAL;
        body.setGravityScale(1);
    }

    /**
     * Indica si el jugador está curado o envenenado.
     *
     * @return la condición del jugador.
     */
    public PlayerCondition getCondition()
    {
        return condition;
    }

    /**
     * Afectar al jugador según algún envenenamiento que tenga.
     *
     * @param delta tiempo en segundos transcurrido desde la última llamada al
     * método.
     */
    public void affectCondition(float delta)
    {
        //Si el jugador está curado, no hacer anda
        if (condition == PlayerCondition.NORMAL)
        {
            return;
        }

        //Le resta el tiempo al envenenamiento
        timeCondition -= delta;

        //Si ya se acabó el envenenamiento, curar al jugador.
        if (timeCondition <= 0)
        {
            timeCondition = 0;
            curate();
        }

        //Afecta al jugador dependiento del hechizo
        switch (condition)
        {
            case POISONED:
                //Quitar (1) vida por segundo
                this.life -= 1 * delta;
                setColor(Color.FOREST);
                break;

            case BURNED:
                //Quitar (2) vidas por segundo
                this.life -= 2 * delta;
                setColor(Color.RED);
                break;

            case ENTANGLED:
                body.setGravityScale(80);
                setColor(Color.GRAY);
                break;
        }
    }
}
