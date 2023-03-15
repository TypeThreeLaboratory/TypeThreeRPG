package marumasa.type_three_rpg.Events;

import marumasa.type_three_rpg.entity.meta.Meta;
import marumasa.type_three_rpg.entity.player.StatusEffect;
import marumasa.type_three_rpg.entity.player.test;
import marumasa.type_three_rpg.minecraft;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class UseEvent {
    public static void main(Player player, minecraft mc) {

        final PlayerInventory inventory = player.getInventory();
        ItemStack itemStack = inventory.getItemInOffHand();


        if (itemStack.getType() == Material.BOOK) {

            final ItemMeta itemMeta = itemStack.getItemMeta();

            if (itemStack.getAmount() <= 1 && itemMeta.hasEnchants()) {

                ItemMeta meta = itemStack.getItemMeta();

                final Component name = meta.displayName();
                if (name == null) return;
                final Material material = inventory.getItemInMainHand().getType();

                if (material == Material.WOODEN_SWORD ||
                        material == Material.STONE_SWORD ||
                        material == Material.GOLDEN_SWORD ||
                        material == Material.IRON_SWORD ||
                        material == Material.DIAMOND_SWORD ||
                        material == Material.NETHERITE_SWORD) {
                    if (name.toString().contains("スキル: 蝶のように舞い、蜂のように刺す")) {


                        Entity entity = player.getTargetEntity(6);

                        player.swingMainHand();

                        if (entity instanceof LivingEntity livingEntity) {
                            player.attack(livingEntity);
                        }
                        player.sendMessage("§aスキル発動！ 1秒間 回避率100%プラス");


                        final StatusEffect StatusEffect = Meta.get(player, mc, "StatusEffect", new StatusEffect());
                        StatusEffect.EvasionUp += 100;
                        new test(StatusEffect).runTaskLater(mc, 20);


                        player.setVelocity(
                                player.getLocation().getDirection().multiply(-0.7)
                        );

                        itemMeta.removeEnchant(Enchantment.DURABILITY);
                        itemStack.setItemMeta(itemMeta);

                        itemStack.setType(Material.BOOK);
                        itemStack.setAmount(10);
                    }
                }
            }
        }
    }
}