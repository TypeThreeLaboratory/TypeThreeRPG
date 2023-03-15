package marumasa.type_three_rpg.entity.dripstone_zombie;

import marumasa.type_three_rpg.entity.meta.Meta;
import marumasa.type_three_rpg.minecraft;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class dripstone_zombie {
    public static void Attack(LivingEntity livingTarget, Zombie zombie, minecraft mc) {

        EntityEquipment entityEquipment = zombie.getEquipment();

        if (entityEquipment.getItemInMainHand().getType() == Material.DRIPSTONE_BLOCK &&
                entityEquipment.getItemInOffHand().getType() == Material.DRIPSTONE_BLOCK) {
            livingTarget.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 0));
            entityEquipment.setItemInMainHand(new ItemStack(Material.AIR));
            entityEquipment.setItemInOffHand(new ItemStack(Material.AIR));
        }

        if (Meta.get(zombie, mc, "PowerAttackPoint", 0) > 2 && new Random().nextInt(3) == 0)
            PowerAttack(zombie, mc, entityEquipment);
    }

    public static void Damage(Zombie zombie, minecraft mc) {
        if (Meta.get(zombie, mc, "PowerAttackPoint", 0) > 2 && new Random().nextInt(3) == 0)
            PowerAttack(zombie, mc);
    }

    private static void PowerAttack(Zombie zombie, minecraft mc) {

        EntityEquipment entityEquipment = zombie.getEquipment();

        entityEquipment.setItemInMainHand(new ItemStack(Material.DRIPSTONE_BLOCK));
        entityEquipment.setItemInOffHand(new ItemStack(Material.DRIPSTONE_BLOCK));
        Meta.set(zombie, mc, "PowerAttackPoint", 0);
    }

    private static void PowerAttack(Zombie zombie, minecraft mc, EntityEquipment entityEquipment) {
        entityEquipment.setItemInMainHand(new ItemStack(Material.DRIPSTONE_BLOCK));
        entityEquipment.setItemInOffHand(new ItemStack(Material.DRIPSTONE_BLOCK));
        Meta.set(zombie, mc, "PowerAttackPoint", 0);
    }
}
