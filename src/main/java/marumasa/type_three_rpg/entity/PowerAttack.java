package marumasa.type_three_rpg.entity;

import marumasa.type_three_rpg.entity.meta.Meta;
import marumasa.type_three_rpg.minecraft;
import org.bukkit.entity.LivingEntity;

public class PowerAttack {
    public static int add(LivingEntity entity, minecraft mc, int value) {
       return Meta.set(entity, mc, "PowerAttackPoint", Meta.get(entity, mc, "PowerAttackPoint", 0) + value);
    }

    public static void set(LivingEntity entity, minecraft mc, int value) {
        Meta.set(entity, mc, "PowerAttackPoint", value);
    }
}
