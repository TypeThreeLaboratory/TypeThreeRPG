package marumasa.type_three_rpg.item;

import marumasa.type_three_rpg.config.Config;
import marumasa.type_three_rpg.entity.player.PlayerData;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    public final ItemStack itemStack;

    public Menu(Player player, PlayerData playerData, Config config) {

        itemStack = new ItemStack(Material.PLAYER_HEAD);
        final SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();

        skullMeta.setOwningPlayer(player);
        skullMeta.displayName(Component.text("§gクリックしてメニューを表示"));

        itemStack.setItemMeta(skullMeta);

        List<Component> text = new ArrayList<>();

        text.add(Component.text("§f役職:§d" + playerData.role));
        text.add(Component.text("§fレベル:§e" + playerData.getLevel()));
        text.add(Component.text("§f体力:§c" + playerData.getHitPoint()));
        text.add(Component.text("§f物理攻撃:§9" + playerData.getPhysicalDefense()));
        text.add(Component.text("§f物理防御:§9" + playerData.getPhysicalDefense()));
        text.add(Component.text("§f魔法攻撃:§9" + playerData.getPhysicalDefense()));
        text.add(Component.text("§f魔法防御:§9" + playerData.getPhysicalDefense()));

        itemStack.lore(text);

    }

    public static void open(Player player) {
        player.openInventory(Bukkit.createInventory(null, 9, Component.text("メニュー")));
    }
}
