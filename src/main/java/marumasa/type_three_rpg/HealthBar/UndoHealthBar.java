package marumasa.type_three_rpg.HealthBar;

import marumasa.type_three_rpg.database;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

public class UndoHealthBar extends BukkitRunnable {

    private final LivingEntity target;
    public final String name;
    public final boolean CustomNameVisible;

    public UndoHealthBar(LivingEntity entity, String entityName, boolean isCustomNameVisible) {
        target = entity;
        name = entityName;
        CustomNameVisible = isCustomNameVisible;
    }

    @Override
    public void run() {
        database.ShowHealthBarEntityList.remove(target);

        target.customName(null);
        target.setCustomNameVisible(CustomNameVisible);
    }
}
