package marumasa.type_three_rpg.item;

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

    public ItemStack itemStack;
    public PlayerData playerData;

    public menu(Player player) {
        itemStack = new ItemStack(Material.PLAYER_HEAD);

        final SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        skullMeta.setOwningPlayer(player);
        skullMeta.displayName(Component.text("§gクリックしてメニューを表示"));

        playerData = new PlayerData(player);
        database.PlayerData.put(player, playerData);
    }

    public void update() {
        List<Component> text = new ArrayList<>();

        text.add(Component.text("役職:" + playerData.role));
        text.add(Component.text("レベル:" + playerData.level));
        text.add(Component.text("体力:" + playerData.hitPoint));
        text.add(Component.text("防御力:" + playerData.physicalDefense));

        itemStack.lore(text);
    }
}
