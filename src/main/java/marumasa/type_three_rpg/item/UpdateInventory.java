package marumasa.type_three_rpg.item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.SmithingInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpdateInventory extends BukkitRunnable {
    private static final List<String> LoreBase = new ArrayList<>(Arrays.asList(
            "private static void use(Player player) {",
            "    player.addPotionEffect(new PotionEffect(",
            "            PotionEffectType.SPEED,",
            "            20,",
            "            0",
            "    ));",
            "}"
    ));
    private static final String explanation = "解説: 右クリックすると１秒間、移動速度レベル１が付与される";

    private final SmithingInventory smithingInventory;

    public UpdateInventory(SmithingInventory inventory) {
        smithingInventory = inventory;
    }

    @Override
    public void run() {
        ItemStack target = smithingInventory.getItem(0);
        ItemStack material = smithingInventory.getItem(1);
        ItemStack result = smithingInventory.getResult();
        if (target == null || material == null || result == null) return;
        if (material.getType() != Material.PAPER || result.getType() == Material.AIR) return;

        ItemMeta resultMeta = result.getItemMeta();
        if (resultMeta == null) return;

        final List<TextComponent> lore = new ArrayList<>();
        for (String string : LoreBase) {
            lore.add(Component.text(string).color(
                    TextColor.color(255, 170, 0)).decoration(
                    TextDecoration.ITALIC, false)
            );
        }
        lore.add(Component.text(explanation).color(
                TextColor.color(85, 255, 255)).decoration(
                TextDecoration.ITALIC, false)
        );


        resultMeta.lore(lore);

        result.setItemMeta(resultMeta);
        smithingInventory.setResult(result);
    }
}
