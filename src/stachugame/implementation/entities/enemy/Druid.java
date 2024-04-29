package stachugame.implementation.entities.enemy;

import stachugame.api.entities.IEnemy;
import stachugame.api.entities.IEntity;
import stachugame.api.maps.Direction;
import stachugame.implementation.entities.AbstractEntity;

/**
 * Klasa przeciwnika Druid
 */
public class Druid extends AbstractEntity implements IEnemy {
    private final int damage;
    public Druid(int maxHealth, int damage) {
        super("Druid", maxHealth);
        this.damage = damage;
    }

    @Override
    public void update() {
        currentHealth = (int) Math.min(currentHealth*1.1, maxHealth);

        if(Math.random() > 0.8){
            Direction[] dirs = Direction.values();
            int i = 0;
            while(!currentRoom.isNextRoomOpen(dirs[i])){
                i++;
            }
            move(dirs[i]);
        }
    }

    @Override
    public void attack(IEntity entity) {
        entity.setHealth(entity.getHealth()-damage);
    }

    @Override
    public int getDamage() {
        return damage;
    }
}
