package marumasa.type_three_rpg;

import marumasa.type_three_rpg.entity.base.EntityBase;
import marumasa.type_three_rpg.entity.player.UpdateRedScreen;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class database {
    public static final Map<Player, UpdateRedScreen> ShowRedScreenPlayerList = new HashMap<>();
    public static final Map<Player, marumasa.type_three_rpg.entity.player.PlayerData> PlayerDataList = new HashMap<>();
    public static final Map<Entity, EntityBase> EntityBaseList = new HashMap<>();
    public static final Map<Entity, LivingEntity> EntityDamageLink = new HashMap<>();
}
