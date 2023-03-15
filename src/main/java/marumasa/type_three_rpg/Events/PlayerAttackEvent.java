package marumasa.type_three_rpg.Events;

import marumasa.type_three_rpg.minecraft;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerAttackEvent {
    public static void main(Player player, double AttackDamage, minecraft mc) {
        subtractRecast(player, 1);

        //攻撃力をプレイヤーに表示
        player.sendActionBar(Component.text(String.format("§6%.0fダメージ与えた！！", AttackDamage)));

        //Attack.EvasiveSlash(player, mc);
    }

    public static void subtractRecast(Player player, int value) {

        final ItemStack itemStack = player.getInventory().getItemInOffHand();
        if (itemStack.getType() == Material.BOOK) {
            final int amount = itemStack.getAmount() - value;

            final ItemMeta itemMeta = itemStack.getItemMeta();

            if (!itemMeta.hasEnchants()) {

                if (amount < 1) {

                    itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
                    itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    itemStack.setItemMeta(itemMeta);
                    player.sendMessage("§6スキル使用可能になりました");
                    itemStack.setAmount(1);

                } else {

                    itemStack.setAmount(amount);
                }
            }
        }

        /*for (int loop = 0; loop <= 8; loop++) {
            ItemStack itemStack = inventory.getItem(loop);
            if (itemStack == null) continue;

            int amount = itemStack.getAmount() - value;
            if (itemStack.getType() == Material.BOOK && amount < 1) {

                final ItemMeta itemMeta = itemStack.getItemMeta();

                if (!itemMeta.hasEnchants()) {
                    itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
                    itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                }

            } else {
                itemStack.setAmount(amount);
            }
        }*/
    }
}
