package marumasa.type_three_rpg.HealthBar;

import marumasa.type_three_rpg.config.config;
import marumasa.type_three_rpg.database;
import marumasa.type_three_rpg.minecraft;
import net.kyori.adventure.text.Component;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

public class UpdateHealthBar extends BukkitRunnable {

    private final LivingEntity target;
    private final config cfg;
    private final minecraft mc;

    public UpdateHealthBar(LivingEntity entity, config config, minecraft minecraft) {
        target = entity;
        cfg = config;
        mc = minecraft;
    }

    @Override
    public void run() {

        if (target.isDead()) {
            if (database.ShowHealthBarEntityList.containsKey(target)) {
                final UndoHealthBar old_UndoHealthBar = database.ShowHealthBarEntityList.get(target);
                old_UndoHealthBar.cancel();
                new UndoHealthBar(old_UndoHealthBar.text, target).run();
            }
            return;
        }

        final AttributeInstance MaxHealthAttribute = target.getAttribute(Attribute.GENERIC_MAX_HEALTH);

        if (MaxHealthAttribute == null) return;

        final int Health = (int) Math.round(target.getHealth());
        final int MaxHealth = (int) Math.round(MaxHealthAttribute.getValue());

        final StringBuilder name = new StringBuilder("§c▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏§r");
        name.insert((30 * Health / MaxHealth) + 2, "§8");

        final UndoHealthBar undoHealthBar;
        if (database.ShowHealthBarEntityList.containsKey(target)) {

            final UndoHealthBar old_UndoHealthBar = database.ShowHealthBarEntityList.get(target);
            old_UndoHealthBar.cancel();
            old_UndoHealthBar.text.text(Component.text(name.toString()));

            undoHealthBar = new UndoHealthBar(old_UndoHealthBar.text, target);

        } else {

            undoHealthBar = new UndoHealthBar(SummonHealthBar.run(target, name.toString(), cfg, mc), target);

        }
        database.ShowHealthBarEntityList.put(target, undoHealthBar);
        undoHealthBar.runTaskLater(mc, 40);

        //database.ShowHealthBarEntityList.put(target, undoHealthBar);




        /*if (entityList.size() != 0 && entityList.get(0) instanceof TextDisplay textDisplay) {

            textDisplay.text(Component.text(name.toString()));
            final UndoHealthBar old_UndoHealthBar = database.ShowHealthBarEntityList.get(target);
            if (old_UndoHealthBar == null) return;
            old_UndoHealthBar.cancel();
            undoHealthBar = new UndoHealthBar(textDisplay);
            database.ShowHealthBarEntityList.replace(target, undoHealthBar);

        } else {
            TextDisplay textDisplay = (TextDisplay) target.getWorld().spawnEntity(target.getLocation(), EntityType.TEXT_DISPLAY);
            textDisplay.text(Component.text(name.toString()));
            textDisplay.setBillboard(Display.Billboard.CENTER);
            textDisplay.setSeeThrough(true);

            //database.ShowHealthBarEntityList.put(target, undoHealthBar);
            new UndoHealthBar(textDisplay);

            target.addPassenger(textDisplay);

            undoHealthBar = new UndoHealthBar(textDisplay);

            undoHealthBar.runTaskLater(mc, 40);
            database.ShowHealthBarEntityList.put(target, undoHealthBar);
        }*/
    }
}
