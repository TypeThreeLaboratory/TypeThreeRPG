package marumasa.type_three_rpg.entity.player;

import marumasa.type_three_rpg.Minecraft;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Objects;

public class Attack {
    public static double calculate(double DefaultDamage) {

        double MaxDamage = DefaultDamage * 1.25;
        double MinDamage = DefaultDamage * 0.75;

        return Math.ceil((Math.random() * (MaxDamage - MinDamage)) + MinDamage);
    }

    public static double calculate(double MaxDamage, double MinDamage) {
        return Math.ceil((Math.random() * (MaxDamage - MinDamage)) + MinDamage);
    }

    public static void EvasiveSlash(Player player, Minecraft mc) {

        final PlayerInventory inventory = player.getInventory();
        final ItemStack itemStack = inventory.getItemInMainHand();
        final Material material = itemStack.getType();

        final String skill = "";
        if (Objects.equals(skill, "回避斬り")) {
            if (material == Material.WOODEN_SWORD ||
                    material == Material.STONE_SWORD ||
                    material == Material.GOLDEN_SWORD ||
                    material == Material.IRON_SWORD ||
                    material == Material.DIAMOND_SWORD ||
                    material == Material.NETHERITE_SWORD) {

                player.sendMessage("§6回避斬り発動！！");
                //Meta.set(player, mc, "skill", "");

                player.setVelocity(
                        player.getLocation().getDirection().multiply(-0.7)
                );
            }
        }
    }
}
