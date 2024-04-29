package stachugame.implementation.items.potions;

import stachugame.api.entities.IEntity;
import stachugame.implementation.items.AbstractItem;

/**
 * Przedmiot mikstury leczenia
 */
public class HealthPotion extends AbstractItem {

    private final int healthRegen;
    public HealthPotion(int healthRegen) {
        super("Jabol Jabółkowy " +((float) healthRegen/20)+"L");
        this.healthRegen = healthRegen;
    }

    @Override
    public String getItemName() {
        return String.format("%s [+%dHP]", name, healthRegen);
    }

    @Override
    public void useItem(IEntity user) {
        user.setHealth(user.getHealth()+healthRegen);
    }
}
