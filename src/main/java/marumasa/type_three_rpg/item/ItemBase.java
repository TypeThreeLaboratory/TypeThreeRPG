package marumasa.type_three_rpg.item;

import marumasa.type_three_rpg.Minecraft;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemBase {
    private static final List<String> LoreBase = new ArrayList<>(Arrays.asList(
            "private static void use(Player player) {",
            "    player.addPotionEffect(new PotionEffect(",
            "            PotionEffectType.SPEED,",
            "            20,",
            "            0",
            "    ));",
            "}",
            "解説: 右クリックすると１秒間、移動速度レベル１が付与される"
    ));

    public static void UseEvent(ItemStack itemStack, Player player, Minecraft mc) {

        ItemMeta meta = itemStack.getItemMeta();
        List<Component> lore = meta.lore();
        if (lore == null) return;

        final List<String> stringLore = new ArrayList<>();
        for (Component component : lore) {
            if (component instanceof TextComponent textComponent) {
                stringLore.add(textComponent.content());
            } else return;
        }

        if (stringLore.equals(LoreBase)) {
            use(player);
        }

    }

    private static void use(Player player) {
        player.addPotionEffect(new PotionEffect(
                PotionEffectType.SPEED,
                20,
                0
        ));
    }
}
