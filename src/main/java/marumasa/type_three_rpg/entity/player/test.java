package marumasa.type_three_rpg.entity.player;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class test extends BukkitRunnable {
    private final StatusEffect effect;

    public test(StatusEffect statusEffect) {
        effect = statusEffect;
    }

    @Override
    public void run() {
        effect.EvasionUp -= 100;
        Bukkit.getLogger().info("grttt5r4t4r");
    }
}
