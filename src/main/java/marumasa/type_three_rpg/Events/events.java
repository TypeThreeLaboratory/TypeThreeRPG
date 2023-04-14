package marumasa.type_three_rpg.Events;

import com.github.yannicklamprecht.worldborder.api.WorldBorderApi;
import io.papermc.paper.event.player.PlayerArmSwingEvent;
import marumasa.type_three_rpg.Minecraft;
import marumasa.type_three_rpg.config.Config;
import marumasa.type_three_rpg.database;
import marumasa.type_three_rpg.entity.base.EntityBase;
import marumasa.type_three_rpg.entity.player.MenuOpen;
import marumasa.type_three_rpg.entity.player.PlayerData;
import marumasa.type_three_rpg.entity.player.UpdateRedScreen;
import marumasa.type_three_rpg.entity.player.mainPlayer;
import marumasa.type_three_rpg.entity.target_dummy;
import marumasa.type_three_rpg.item.ItemBase;
import marumasa.type_three_rpg.item.UpdateInventory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.world.EntitiesLoadEvent;
import org.bukkit.event.world.EntitiesUnloadEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.SmithingInventory;

import java.util.Objects;
import java.util.logging.Logger;

public class events implements Listener {

    private final Logger logger = Bukkit.getLogger();
    private final Minecraft mc;
    private final Config cfg;
    private final WorldBorderApi worldBorderApi;

    public events(Config config, Minecraft data, WorldBorderApi worldBorderAPI) {
        mc = data;
        cfg = config;
        worldBorderApi = worldBorderAPI;
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        //エンティティによってエンティティがダメージを受けたら

        final EntityBase base = database.EntityBaseList.get(event.getDamager());
        if (base != null) base.attack();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        //インベントリをクリックしたら

        logger.info("c");
        if (event.getView().getPlayer() instanceof Player player) {
            final Inventory inventory = event.getClickedInventory();
            if (inventory == null) return;
            if (inventory instanceof PlayerInventory) {

                if (event.getSlot() == 9) {
                    logger.info("test3");
                    event.setCancelled(true);

                    new MenuOpen(player).runTaskLater(mc, 0);
                }
            } else if (event.isLeftClick())
                if (inventory instanceof SmithingInventory smithingInventory) {
                    new UpdateInventory(smithingInventory).runTaskLater(mc, 0);
                }


            //--------------------------
            /*final int slot = event.getSlot();

            final ItemStack itemStack = inventory.getItem(slot);

            if (itemStack == null) return;
            if (itemStack.getType() == Material.BOOK) {
                if (event.isRightClick()) {
                    event.setCancelled(true);
                }
            }*/


        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        final ItemStack itemStack = event.getItem();
        if (itemStack == null) return;


        //プレイヤーがオブジェクトまたは空気に向かって使用ボタンを押したら
        if (event.getAction().isRightClick()) {

            ItemBase.UseEvent(itemStack, event.getPlayer(), mc);
        }
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        //プレイヤーがエンティティに向かって使用ボタンを押した場合
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerArmSwingEvent event) {
        //プレイヤーが腕を振った場合
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        //Projectile が発射された場合

        Projectile projectile = event.getEntity();

        if (projectile.getShooter() instanceof Player player) {

            final PlayerData playerData = database.PlayerDataList.get(player);
            if (Objects.equals(playerData.role, cfg.role.pharmacist)) {
                projectile.setVelocity(projectile.getVelocity().multiply(1.5));
            }
        }
    }

    @EventHandler
    public void onEntityLoad(EntitiesLoadEvent event) {
        //エンティティがロードされたら
        for (Entity entity : event.getEntities()) {
            if (entity instanceof LivingEntity livingEntity) {
                if (livingEntity instanceof Husk husk) {
                    database.EntityBaseList.put(husk, new target_dummy(husk, mc));
                }
            }
        }
    }

    @EventHandler
    public void onEntityUnload(EntitiesUnloadEvent event) {
        //エンティティがアンロードされたら
        for (Entity entity : event.getEntities()) {
            EntityBase entityBase = database.EntityBaseList.get(entity);
            if (entityBase != null) {
                entityBase.remove();
                database.EntityBaseList.remove(entity);
            }
        }
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        //エンティティがスポーンしたら

        if (event.getEntity() instanceof Husk husk) {
            database.EntityBaseList.put(husk, new target_dummy(husk, mc));
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        //プレイヤーがログインしたら

        final Player player = event.getPlayer();


        //レッドスクリーンを更新
        if (!database.ShowRedScreenPlayerList.containsKey(player)) {
            new UpdateRedScreen(player, worldBorderApi, mc).runTaskTimer(mc, 0, 30);
        }
        database.PlayerDataList.put(player, new PlayerData(player, cfg));


        mainPlayer.setSkillSlot(player, mc);
    }

    @EventHandler
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        //プレイヤーがワールドを移動したら

        final Player player = event.getPlayer();

        //レッドスクリーンを更新
        if (!database.ShowRedScreenPlayerList.containsKey(player)) {
            new UpdateRedScreen(player, worldBorderApi, mc).runTaskTimer(mc, 0, 30);
        }
        database.PlayerDataList.put(player, new PlayerData(player, cfg));


        //mainPlayer.setSkillSlot(player, mc);
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent event) {
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {

        final EntityBase base = database.EntityBaseList.get(event.getEntity());
        if (base != null) base.damage();
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        //エンティティがダメージを受けたら
        final Entity entity = event.getEntity();

        final EntityBase base = database.EntityBaseList.get(entity);
        if (base != null) {
            base.remove();
            database.EntityBaseList.remove(entity);
        }
    }

    @EventHandler
    public void onPluginEnable(PluginEnableEvent event) {
        //エンティティがダメージを受けたら

    }

    @EventHandler
    public void onEntityRegainHealth(EntityRegainHealthEvent event) {
        //エンティティの体力が回復したら
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        //プレイヤーがアイテムを捨てたら

        final Player player = event.getPlayer();
        final PlayerData playerData = database.PlayerDataList.get(player);
        if (playerData.role == null) return;

        final ItemStack itemStack = event.getItemDrop().getItemStack();
        if (itemStack.getType() == Material.BOOK) {
            event.setCancelled(true);
        }
    }
}
