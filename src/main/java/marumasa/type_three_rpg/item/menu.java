package marumasa.type_three_rpg.item;

import marumasa.type_three_rpg.config.Config;
import marumasa.type_three_rpg.database;
import marumasa.type_three_rpg.entity.player.PlayerData;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class menu {

    private final ItemStack itemStack;
    private final PlayerData data;

    public menu(Player player, PlayerData playerData, Config config) {

        data = playerData;

        itemStack = new ItemStack(Material.PLAYER_HEAD);

        final SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        skullMeta.setOwningPlayer(player);
        skullMeta.displayName(Component.text("§gクリックしてメニューを表示"));

        update();

    }

    public void update() {
        List<Component> text = new ArrayList<>();

        text.add(Component.text("役職:" + data.role));
        text.add(Component.text("レベル:" + data.getLevel()));
        text.add(Component.text("体力:" + data.getHitPoint()));
        text.add(Component.text("防御力:" + data.getPhysicalDefense()));

        itemStack.lore(text);
    }
}
