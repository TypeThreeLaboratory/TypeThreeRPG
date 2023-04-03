package marumasa.type_three_rpg.entity.player;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class MenuOpen extends BukkitRunnable {

    private final Player target;


    public MenuOpen(Player player) {
        target = player;
    }

    @Override
    public void run() {
        target.openInventory(Bukkit.createInventory(null, 9, Component.text("メニュー")));
    }
}
