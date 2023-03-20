package marumasa.type_three_rpg.DamageDisplay;

import org.bukkit.entity.TextDisplay;
import org.bukkit.scheduler.BukkitRunnable;

public class RemoveDamageDisplay extends BukkitRunnable {
    private final TextDisplay target;

    public RemoveDamageDisplay(TextDisplay textDisplay) {
        target = textDisplay;
    }

    @Override
    public void run() {
        target.remove();
    }
}
