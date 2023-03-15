package marumasa.type_three_rpg.item;

import marumasa.type_three_rpg.minecraft;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.SmithingInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class UpdateInventory extends BukkitRunnable {
    private minecraft mc;
    List<Component> list = new ArrayList<>(List.of(Component.text("§fプログラムが付与されたアイテム")));

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

        resultMeta.lore(list);

        result.setItemMeta(resultMeta);
        smithingInventory.setResult(result);
    }
}
