package marumasa.type_three_rpg.entity.player;

import marumasa.type_three_rpg.config.Config;
import marumasa.type_three_rpg.entity.EntityData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class PlayerData extends EntityData {
    public String role;

    public long exp;


    public PlayerData(Player player, Config cfg) {
        super(player);

        setHitPoint(0);
        setPhysicalAttack(0);
        setMagicAttack(0);

        setPhysicalDefense(0);
        setMagicDefense(0);

        role = "§d魔法使い";

        setLevel(0);
    }

    public PlayerData(Entity entity) {
        super(entity);
    }

    public void update() {
    }
}
