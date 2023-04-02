package marumasa.type_three_rpg.entity;

import marumasa.type_three_rpg.database;
import org.bukkit.entity.LivingEntity;

public class PowerAttack {
    public static void add(LivingEntity entity, int value) {
        final EntityData entityData = database.EntityData.get(entity);
        if (entityData == null) {
            database.EntityData.put(entity, new EntityData(entity));
            return;
        }
        final int powerAttackPoint = entityData.getPowerAttackPoint() + value;
        entityData.setPowerAttackPoint(powerAttackPoint);
    }

    public static void set(LivingEntity entity, int value) {
        final EntityData entityData = database.EntityData.get(entity);
        entityData.setPowerAttackPoint(value);
    }
}
