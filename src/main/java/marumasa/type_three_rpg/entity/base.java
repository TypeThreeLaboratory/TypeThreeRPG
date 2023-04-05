package marumasa.type_three_rpg.entity;

import marumasa.type_three_rpg.database;
import marumasa.type_three_rpg.minecraft;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Transformation;

public class base extends EntityData {

    private final Husk entity;

    private final Interaction interaction_1;
    private final Interaction interaction_2;
    private final ItemDisplay itemDisplay;
    private final tick tick;

    public base(Husk husk, float height, float width, minecraft minecraft) {
        super(husk);

        entity = husk;

        final World world = entity.getWorld();
        final Location location = entity.getLocation();

        interaction_1 = (Interaction) world.spawnEntity(location, EntityType.INTERACTION);
        interaction_2 = (Interaction) world.spawnEntity(location, EntityType.INTERACTION);
        itemDisplay = (ItemDisplay) world.spawnEntity(location, EntityType.ITEM_DISPLAY);

        entity.addPassenger(interaction_1);
        entity.addPassenger(interaction_2);
        entity.addPassenger(itemDisplay);

        entity.setBaby();
        entity.setSilent(true);
        entity.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 0, false, false));

        interaction_1.setInteractionHeight(-0.71f);
        interaction_1.setInteractionWidth(width);

        interaction_2.setInteractionHeight(height - 0.71f);
        interaction_2.setInteractionWidth(width);


        final ItemStack itemStack = new ItemStack(Material.ECHO_SHARD);
        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setCustomModelData(1);
        itemStack.setItemMeta(itemMeta);

        itemDisplay.setItemStack(itemStack);
        itemDisplay.setShadowRadius(width * 0.75f);

        tick = new tick(itemDisplay);
        tick.runTaskTimer(minecraft, 0, 0);

        database.EntityDamageLink.put(interaction_1, husk);
        database.EntityDamageLink.put(interaction_2, husk);
        database.EntityDamageLink.put(itemDisplay, husk);

    }

    private static class tick extends BukkitRunnable {
        private final ItemDisplay display;

        private tick(ItemDisplay itemDisplay) {
            display = itemDisplay;
        }

        @Override
        public void run() {
            Transformation transformation = display.getTransformation();
            display.setTransformation(new Transformation(
                    transformation.getTranslation(),
                    transformation.getLeftRotation(),
                    transformation.getScale(),
                    transformation.getRightRotation()
            ));
        }
    }

    public void remove() {
        interaction_1.remove();
        interaction_2.remove();
        itemDisplay.remove();

        tick.cancel();
    }
}
