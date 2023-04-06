package marumasa.type_three_rpg.entity.base;

import org.bukkit.entity.LivingEntity;

public class LivingEntityBase extends EntityBase {
    public LivingEntityBase(LivingEntity entity) {
        super(entity);
        entity.setRemoveWhenFarAway(false);
    }
}
