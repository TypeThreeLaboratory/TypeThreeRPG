package marumasa.type_three_rpg.HealthBar;

import marumasa.type_three_rpg.database;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TextDisplay;
import org.bukkit.scheduler.BukkitRunnable;

public class UndoHealthBar extends BukkitRunnable {

    public final TextDisplay text;
    public final Entity target;

    public UndoHealthBar(TextDisplay textDisplay, Entity entity) {
        text = textDisplay;
        target = entity;
    }

    @Override
    public void run() {
        database.ShowHealthBarEntityList.remove(target);
        text.remove();
    }
}
