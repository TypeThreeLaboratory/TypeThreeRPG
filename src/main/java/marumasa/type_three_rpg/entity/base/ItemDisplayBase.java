package marumasa.type_three_rpg.entity.base;

import marumasa.type_three_rpg.Minecraft;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Transformation;

public class ItemDisplayBase extends LivingEntityBase {

    private final ItemDisplay body;
    private final LivingEntity entity;
    private final Tick tick;
    private final Minecraft mc;
    private final int defaultModelData;
    private final int damageModelData;

    public ItemDisplayBase(LivingEntity livingEntity, Minecraft minecraft, int defaultModel, int damageModel, float height) {
        super(livingEntity);

        mc = minecraft;
        defaultModelData = defaultModel;
        damageModelData = damageModel;
        entity = livingEntity;


        final World world = entity.getWorld();
        final Location location = entity.getLocation();

        body = (ItemDisplay) world.spawnEntity(location, EntityType.ITEM_DISPLAY);

        entity.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 0, true, false));
        entity.setSilent(true);

        final ItemStack itemStack = new ItemStack(Material.AMETHYST_SHARD);
        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setCustomModelData(defaultModel);
        itemStack.setItemMeta(itemMeta);

        body.setItemStack(itemStack);

        entity.addPassenger(body);

        tick = new Tick(body, entity);
        tick.runTaskTimer(minecraft, 0, 0);


        Transformation transformation;

        transformation = body.getTransformation();
        body.setTransformation(new Transformation(
                transformation.getTranslation().add(0, -height, 0),
                transformation.getLeftRotation(),
                transformation.getScale(),
                transformation.getRightRotation()
        ));
    }

    @Override
    public void damage() {
        super.damage();

        new damage_overlay(body, defaultModelData, damageModelData).runTaskLater(mc, 10);
    }

    @Override
    public void remove() {
        super.remove();

        body.remove();
        entity.remove();

        tick.cancel();
    }

    private static class Tick extends BukkitRunnable {
        private final ItemDisplay display;
        private final LivingEntity target;

        private Tick(ItemDisplay itemDisplay, LivingEntity entity) {
            display = itemDisplay;
            target = entity;
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
            display.setRotation(target.getBodyYaw(), 0);
        }
    }

    private static class damage_overlay extends BukkitRunnable {
        private final ItemDisplay display;
        private final ItemStack item;
        private final int ModelData;

        private damage_overlay(ItemDisplay itemDisplay, int defaultModel, int damageModel) {
            display = itemDisplay;
            item = display.getItemStack();
            ModelData = defaultModel;

            if (item == null) return;

            final ItemMeta meta = item.getItemMeta();
            meta.setCustomModelData(damageModel);
            item.setItemMeta(meta);
            display.setItemStack(item);
        }

        @Override
        public void run() {
            final ItemMeta meta = item.getItemMeta();
            meta.setCustomModelData(ModelData);
            item.setItemMeta(meta);
            display.setItemStack(item);
        }
    }
}
