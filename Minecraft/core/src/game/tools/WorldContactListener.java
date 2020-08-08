package game.tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import game.actors.Player;
import game.actors.Mob;
import game.actors.monster.MonsterMob;
import game.actors.monster.Skeleton;
import com.badlogic.gdx.physics.box2d.Fixture;
import game.actors.collectibles.FoodCollectible;
import game.actors.collectibles.ObjectCollectible;
import game.actors.farming.plants.Plant;

/**
 *
 * @author Karen
 */
public class WorldContactListener implements ContactListener
{

    private final Player player;

    public WorldContactListener(Player player)
    {
        this.player = player;
    }

    @Override
    public void beginContact(Contact contact)
    {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();

        int colision = a.getFilterData().categoryBits | b.getFilterData().categoryBits;

        switch (colision)
        {
            case Constant.PLAYER_FEET_BIT | Constant.GROUND_BIT:
                player.setIsJumping(false);
                break;
            case Constant.MOB_SENSOR_BIT | Constant.GROUND_BIT:
                if (a.getFilterData().categoryBits == Constant.MOB_SENSOR_BIT)
                {
                    ((Mob) a.getUserData()).changeDirection();
                } 
                else
                {
                    ((Mob) b.getUserData()).changeDirection();
                }
                break;
            case Constant.PLAYER_BIT | Constant.MOB_SENSOR_BIT:
                if (a.getFilterData().categoryBits == Constant.MOB_SENSOR_BIT)
                {
                    player.setEnemy((Mob) a.getUserData());
                } 
                else
                {
                    player.setEnemy((Mob) b.getUserData());
                }
                if (player.getEnemy() instanceof MonsterMob)
                {
                    MonsterMob monsterMob = (MonsterMob) player.getEnemy();
                    monsterMob.attackPlayer(player);
                }
                player.canAttack(true);
                break;
            case Constant.PLAYER_BIT | Constant.FOOD_BIT:
                if (a.getFilterData().categoryBits == Constant.FOOD_BIT)
                {
                    player.setObjectCollectible((ObjectCollectible)a.getUserData());
                }
                else 
                {
                    player.setObjectCollectible((ObjectCollectible)b.getUserData());
                }   
                break;
            case Constant.MOB_BIT | Constant.MOB_BIT:
                ((Mob) a.getUserData()).changeDirection();
                ((Mob) b.getUserData()).changeDirection();
                break;
            case Constant.PLAYER_BIT | Constant.TREE_BIT:
                if (a.getFilterData().categoryBits == Constant.TREE_BIT)
                {
                    player.setPlant((Plant)a.getUserData());
                }
                else 
                {
                    player.setPlant((Plant)b.getUserData());
                }
                player.canAttack(true); 
                break;
            case Constant.PLAYER_BIT | Constant.ARROW_SENSOR_BIT:
                if (a.getFilterData().categoryBits == Constant.ARROW_SENSOR_BIT)
                {
                    Skeleton skeleton = ((Skeleton)a.getUserData());
                    skeleton.specialAttack(player);
                } 
                else
                {
                    Skeleton skeleton = ((Skeleton)b.getUserData());
                    skeleton.specialAttack(player);
                }
                break;
                
        }
    }

    @Override
    public void endContact(Contact contact)
    {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();

        int colision = a.getFilterData().categoryBits | b.getFilterData().categoryBits;

        switch (colision)
        {
            case Constant.PLAYER_BIT | Constant.MOB_SENSOR_BIT:
                player.setEnemy(null);
                break;
            case Constant.PLAYER_BIT | Constant.FOOD_BIT:   
                player.setObjectCollectible(null);
                break;
            case Constant.PLAYER_BIT | Constant.TREE_BIT:
                player.setPlant(null);
                break;
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

}
