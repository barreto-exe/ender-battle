package game.actors.monster;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import game.actors.Player;
import game.actors.Mob;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import game.actors.collectibles.BattleObjectCollectible;
import game.actors.collectibles.EsmeraldCollective;
import game.actors.collectibles.ObjectCollectible;
import game.inventario.BattleObject;
import game.screens.GameScreen;
import game.tools.Constant;

/**
 *
 * @author luisb
 */
public abstract class MonsterMob extends Mob
{
    //<editor-fold defaultstate="collapsed" desc="Atributos">
    //Atributos Box2d
    protected FixtureDef fixtureD;
    
    //Propiedades de los Mobs
    protected boolean isBoss;
    protected int dificulty;
    protected int attackPoints;
    protected String attackDescription;
    protected BattleObject prize;
    private final TextureAtlas atlas;
    //</editor-fold>
    
    /**
     * Representa a un monstruo del juego.
     * @param screen es la pantalla en la que se encuentra.
     * @param region es el sprite del mob que está en el atlas.
     * @param x ubicación horizonal en el mapa.
     * @param y ubicación vertical en el mapa.
     * @param width ancho del mob en pixeles.
     * @param height alto del mob en pixeles.
     * @param speed la velocidad del mob.
     * @param life la vida del mob.
     * @param attackPoints puntos que restan al atacar.
     * @param isBoss si es jefe o no. Si es true, se duplican los putos de ataque,
     * la vida y se divide entre 3 la velocidad.
     * @param prize es el premio que arroja si el mob es jefe.
     * @param sonido es el sonido que hace al recibir ataques.
     */
    public MonsterMob(GameScreen screen, TextureRegion region, float x, float y, float width, float height, float speed, float life, int attackPoints, boolean isBoss, BattleObject prize, String sonido)
    {
        super(screen, region, life, sonido);
        this.speed = speed;
        atlas = screen.getAtlas();
        this.attackPoints = attackPoints;
        this.isBoss = isBoss;
        this.prize = prize;
        
        if (isBoss)
        {
            this.attackPoints *= 2;
            this.life *= 4;
            this.speed /= 3.5;
            setBounds(0, 0, (width / Constant.PPM) * 2, (height / Constant.PPM) * 2);
        }
        else
        {
            setBounds(0, 0, width / Constant.PPM, height / Constant.PPM);
        }
        

        //<editor-fold defaultstate="collapsed" desc="Definición del body">
        BodyDef bodyD = new BodyDef();
        bodyD.position.set(x, y);
        bodyD.type = BodyDef.BodyType.DynamicBody;
        body = this.world.createBody(bodyD);
        //</editor-fold>
        
        
        fixtureD = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        if(this instanceof EnderDragon)
        {
            shape.setAsBox(getWidth() / 2 - 1, getHeight() / 2 - 0.5f);
        }
        else if(this instanceof Zombie)
        {
            shape.setAsBox(getWidth() / 2 - 0.8f, getHeight() / 2);
        }
        else
        {
            shape.setAsBox(getWidth() / 2, getHeight() / 2);
        }
        fixtureD.shape = shape;
        fixtureD.filter.categoryBits = Constant.MOB_BIT;
        fixtureD.filter.maskBits = Constant.GROUND_BIT | Constant.MOB_BIT | Constant.PLAYER_BIT;
        body.createFixture(fixtureD).setUserData(this);
    }

    public boolean isBoss()
    {
        return isBoss;
    }
    
    /**
     * Atacar a un jugador.
     * @param player es el jugador que recibe el ataque.
     */
    public void attackPlayer(Player player)
    {
        if (attackOportunity(2))
        {
            player.toRecibeAttack(attackPoints);
        }
        if (attackOportunity(3))
        {
            specialAttack(player);
        }
    }
    
    /**
     * Determina aleatoriamente si el mob tiene oportunidad de atacar al jugador.
     * @param oportunity factor de oportunidad.
     * @return true si puede atacar.
     */
    public boolean attackOportunity(int oportunity)
    {
        int aux;
        aux = oportunity - 1;
        int chance = (int) (Math.random() * oportunity);
        return chance == aux;
    }

    @Override
    protected void toDie()
    {
        ObjectCollectible objects[];
        
        if (isBoss)
        {
            objects = new ObjectCollectible[5];
            
            //Esmeraldas
            objects[2] = new EsmeraldCollective(textureEsmereald, world, new Vector2(body.getPosition().x - getWidth() / 4, body.getPosition().y));
            objects[3] = new EsmeraldCollective(textureEsmereald, world, new Vector2(body.getPosition().x + getWidth() / 4, body.getPosition().y));
            
            //Pieza de la armadura de diamante que custotia el jefe
            objects[4] = new BattleObjectCollectible(prize.getObject(), prize.getMaterial().getMaterial(), atlas, world, body.getPosition());
        }
        else
        {
            objects = new ObjectCollectible[3];
            
            //Esmeralda
            objects[2] = new EsmeraldCollective(textureEsmereald, world, new Vector2(body.getPosition().x, body.getPosition().y));
        }
        
        //Esmeraldas
        objects[0] = new EsmeraldCollective(textureEsmereald, world, new Vector2(body.getPosition().x - getWidth() / 2, body.getPosition().y));
        objects[1] = new EsmeraldCollective(textureEsmereald, world, new Vector2(body.getPosition().x + getWidth() / 2, body.getPosition().y));
        
        for (ObjectCollectible o : objects)
        {
            actors.addActor(o);
        }
    }
    
    /**
     * Ejecuta ataque especial al jugador.
     * @param player es el jugador que recibe el ataque.
     */
    public abstract void specialAttack(Player player);

}
