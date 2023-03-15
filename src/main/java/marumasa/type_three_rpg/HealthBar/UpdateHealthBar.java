package marumasa.type_three_rpg.HealthBar;

import marumasa.type_three_rpg.database;
import marumasa.type_three_rpg.minecraft;
import net.kyori.adventure.text.Component;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

public class UpdateHealthBar extends BukkitRunnable {

    private final LivingEntity target;
    private final minecraft mc;

    public UpdateHealthBar(LivingEntity entity, minecraft minecraft) {
        target = entity;
        mc = minecraft;
    }

    @Override
    public void run() {
        final UndoHealthBar undoHealthBar;
        if (database.ShowHealthBarEntityList.containsKey(target)) {
            final UndoHealthBar old_UndoHealthBar = database.ShowHealthBarEntityList.get(target);
            old_UndoHealthBar.cancel();

            undoHealthBar = new UndoHealthBar(target, old_UndoHealthBar.name, old_UndoHealthBar.CustomNameVisible);
        } else {
            undoHealthBar = new UndoHealthBar(target, target.getName(), target.isCustomNameVisible());
        }


        final AttributeInstance MaxHealthAttribute = target.getAttribute(Attribute.GENERIC_MAX_HEALTH);

        if (MaxHealthAttribute == null) return;

        final int Health = (int) Math.round(target.getHealth());
        final int MaxHealth = (int) Math.round(MaxHealthAttribute.getValue());

        final StringBuilder name = new StringBuilder("§c▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏§r");
        name.insert((30 * Health / MaxHealth) + 2, "§8");

        database.ShowHealthBarEntityList.put(target, undoHealthBar);
        undoHealthBar.runTaskLater(mc, 40);

        target.customName(Component.text(name.toString()));
        target.setCustomNameVisible(true);
    }
}
