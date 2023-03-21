package marumasa.type_three_rpg.entity.dripstone_zombie;

import marumasa.type_three_rpg.minecraft;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class PowerAttack extends BukkitRunnable {
    private final LivingEntity attack;
    private final LivingEntity target;
    private final minecraft mc;

    public PowerAttack(LivingEntity attackEntity, LivingEntity targetEntity, minecraft minecraft) {
        attack = attackEntity;
        target = targetEntity;
        mc = minecraft;
    }

    @Override
    public void run() {
        if (attack.isDead() || target.isDead()) return;

        attack.setVelocity(target.getLocation().clone().add(0, -0.2, 0).toVector().subtract(attack.getLocation().toVector()).multiply(0.8));

        target.setVelocity(attack.getLocation().clone().add(0, -0.2, 0).toVector().subtract(target.getLocation().toVector()).multiply(-0.8));
        marumasa.type_three_rpg.entity.PowerAttack.set(attack, mc, 0);
        attack.setAI(true);

        target.damage(5, attack);
        EntityEquipment entityEquipment = attack.getEquipment();
        if (entityEquipment == null) return;
        entityEquipment.setItemInMainHand(new ItemStack(Material.AIR));
        entityEquipment.setItemInOffHand(new ItemStack(Material.AIR));
    }
}
