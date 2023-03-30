package marumasa.type_three_rpg.Events;

import com.github.yannicklamprecht.worldborder.api.WorldBorderApi;
import io.papermc.paper.event.player.PlayerArmSwingEvent;
import marumasa.type_three_rpg.config.config;
import marumasa.type_three_rpg.database;
import marumasa.type_three_rpg.entity.PowerAttack;
import marumasa.type_three_rpg.entity.dripstone_zombie.dripstone_zombie;
import marumasa.type_three_rpg.entity.meta.Meta;
import marumasa.type_three_rpg.entity.player.UpdateRedScreen;
import marumasa.type_three_rpg.entity.player.mainPlayer;
import marumasa.type_three_rpg.item.UpdateInventory;
import marumasa.type_three_rpg.minecraft;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.world.EntitiesLoadEvent;
import org.bukkit.event.world.EntitiesUnloadEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.SmithingInventory;

import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

public class events implements Listener {

    private final Logger logger = Bukkit.getLogger();
    private final minecraft mc;
    private final config cfg;
    private final WorldBorderApi worldBorderApi;

    public events(config config, minecraft data, WorldBorderApi worldBorderAPI) {
        mc = data;
        cfg = config;
        worldBorderApi = worldBorderAPI;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        //インベントリをクリックしたら

        final Inventory inventory = event.getClickedInventory();
        if (inventory == null) return;

        final int slot = event.getSlot();

        final ItemStack itemStack = inventory.getItem(slot);

        if (itemStack == null) return;
        if (itemStack.getType() == Material.BOOK) {
            if (event.isRightClick()) {
                event.setCancelled(true);
            }
        }


        if (event.isLeftClick())
            if (inventory instanceof SmithingInventory smithingInventory) {
                new UpdateInventory(smithingInventory).runTaskLater(mc, 0);
            }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        //プレイヤーがオブジェクトまたは空気に向かって使用ボタンを押したら
        if (event.getAction().isRightClick()) {
            UseEvent.main(event.getPlayer(), mc);
        }
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        //プレイヤーがエンティティに向かって使用ボタンを押した場合

    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerArmSwingEvent event) {
        //プレイヤーが腕を振った場合

        //Attack.EvasiveSlash(event.getPlayer(), mc);
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        //Projectile が発射された場合

        logger.info("test");
        Projectile projectile = event.getEntity();
        projectile.setVelocity(projectile.getVelocity().multiply(1.5));
    }

    @EventHandler
    public void onEntityLoad(EntitiesLoadEvent event) {
        //エンティティがロードされたら
        for (Entity entity : event.getEntities()) {
            if (entity instanceof LivingEntity livingEntity) {
                if (livingEntity instanceof Zombie zombie) {
                    final String name = zombie.getName();
                    if (name.contains("鍾乳石ゾンビ")) {
                        Meta.get(livingEntity, mc, "Type", "鍾乳石ゾンビ");
                    }
                }
            }
            remove(entity);

        }
    }

    private void remove(Entity entity) {
        final Set<String> tags = entity.getScoreboardTags();
        for (final String tag : tags)
            switch (tag) {
                case "HealthBar", "DamageDisplay" -> {
                    entity.remove();
                    return;
                }
                default -> {
                }
            }
    }

    @EventHandler
    public void onEntityLoad(EntitiesUnloadEvent event) {
        //エンティティがアンロードされたら
        for (Entity entity : event.getEntities()) {
            remove(entity);
        }
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        //エンティティがスポーンしたら
        if (event.getEntity() instanceof LivingEntity livingEntity) {
            if (livingEntity instanceof Zombie zombie) {
                final String name = zombie.getName();
                if (name.contains("鍾乳石ゾンビ")) {
                    Meta.get(livingEntity, mc, "Type", "鍾乳石ゾンビ");
                }
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        //プレイヤーがログインしたら

        final Player player = event.getPlayer();

        Meta.set(player, mc, "role", "剣闘士");

        //レッドスクリーンを更新
        if (!database.ShowRedScreenPlayerList.containsKey(player)) {
            new UpdateRedScreen(player, worldBorderApi, mc).runTaskTimer(mc, 0, 30);
        }

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

        mainPlayer.setSkillSlot(player, mc);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        //エンティティがダメージを受けたら
        if (event.getEntity() instanceof LivingEntity livingEntity) {

            //ダメージを受けたエンティティがプレイヤーだったら
            if (livingEntity instanceof Player player) {

                //レッドスクリーンを更新
                if (!database.ShowRedScreenPlayerList.containsKey(player)) {
                    new UpdateRedScreen(player, worldBorderApi, mc).runTaskTimer(mc, 0, 30);
                }
            }
        }
    }

    @EventHandler
    public void onEntityDeath(PlayerItemDamageEvent event) {
        //エンティティがダメージを受けたら

        //event.setDeathMessage("");

    }

    @EventHandler
    public void onPluginEnable(PluginEnableEvent event) {
        //エンティティがダメージを受けたら

        //event.setDeathMessage("");

    }

    @EventHandler
    public void onEntityRegainHealth(EntityRegainHealthEvent event) {
        //エンティティの体力が回復したら

        //体力が回復したエンティティがプレイヤーだったら
        if (event.getEntity() instanceof Player player) {


            //レッドスクリーンを更新
            if (!database.ShowRedScreenPlayerList.containsKey(player)) {
                new UpdateRedScreen(player, worldBorderApi, mc).runTaskTimer(mc, 0, 30);
            }
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        //プレイヤーがアイテムを捨てたら

        final Player player = event.getPlayer();

        if (Objects.equals(Meta.get(player, mc, "role", ""), "")) return;

        final ItemStack itemStack = event.getItemDrop().getItemStack();
        if (itemStack.getType() == Material.BOOK) {
            event.setCancelled(true);
        }

        /*final PlayerInventory inventory = player.getInventory();
        final int slot = inventory.getHeldItemSlot();

        logger.info(String.valueOf(slot));

        final ItemStack itemStack = event.getItemDrop().getItemStack();

        if (inventory.getItemInMainHand() != itemStack) {
            logger.info(player.getName() + "はハッククライアントを使用しています");
        } else if (slot == 6 || slot == 7 || slot == 8) {
        }*/

        //event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        //エンティティによってエンティティがダメージを受けたら

        //攻撃されたエンティティが生きているエンティティだったら
        if (event.getEntity() instanceof LivingEntity livingTarget) {
            double AttackDamage = event.getDamage();

            /*Random random = new Random();
            int randomValue = random.nextInt(100);
            if (randomValue + 100 / Meta.get(livingTarget, mc, "StatusEffect", new StatusEffect()).EvasionUp)
                event.setCancelled(true);*/

            //攻撃したエンティティが生きているエンティティだったら
            if (event.getDamager() instanceof LivingEntity livingAttacker) {

                //強攻撃ポイント追加
                PowerAttack.add(livingTarget, mc, 2);

                AttackDamage = AttackEvent.main(livingAttacker, livingTarget, AttackDamage, mc);

                //攻撃したエンティティの種類を取得
                final String TypeA = Meta.get(livingAttacker, mc, "Type", "");

                //もし攻撃したエンティティの種類が鍾乳石ゾンビだったら
                if (Objects.equals(TypeA, "鍾乳石ゾンビ")) {
                    dripstone_zombie.Attack(livingTarget, (Zombie) livingAttacker, mc);
                }

                //攻撃されたエンティティの種類を取得
                final String TypeT = Meta.get(livingTarget, mc, "Type", "");

                //もし攻撃されたエンティティの種類が鍾乳石ゾンビだったら
                if (Objects.equals(TypeT, "鍾乳石ゾンビ")) {
                    dripstone_zombie.Damage((Zombie) livingTarget, mc);
                }

                //もし攻撃されたエンティティの体力が与えられたダメージより多かったら
               /* if (livingTarget.getHealth() - AttackDamage <= 0) {
                    if (database.ShowHealthBarEntityList.containsKey(livingAttacker)) {
                        final UndoHealthBar old_UndoHealthBar = database.ShowHealthBarEntityList.get(livingAttacker);
                        old_UndoHealthBar.cancel();
                        new UndoHealthBar(livingAttacker, old_UndoHealthBar.name, old_UndoHealthBar.CustomNameVisible).run();
                    }
                }*/
            }
        }
    }
}
