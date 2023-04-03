package marumasa.type_three_rpg.entity.player;

import marumasa.type_three_rpg.config.Config;
import marumasa.type_three_rpg.entity.EntityData;
import marumasa.type_three_rpg.item.Menu;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class PlayerData extends EntityData {
    public String role;

    public long exp;
    public Menu menu;


    public PlayerData(Player player, Config cfg) {
        super(player);

        setHitPoint(0);
        setPhysicalAttack(0);
        setMagicAttack(0);

        setPhysicalDefense(0);
        setMagicDefense(0);

        role = cfg.role.gladiator;

        setLevel(0);

        menu = new Menu(player, this, cfg);
        final Inventory inventory = player.getOpenInventory().getBottomInventory();

        inventory.setItem(9, menu.itemStack);
    }

    public PlayerData(Entity entity) {
        super(entity);
    }

    public void update() {
    }
}
