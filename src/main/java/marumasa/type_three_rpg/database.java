package marumasa.type_three_rpg;

import marumasa.type_three_rpg.entity.EntityData;
import marumasa.type_three_rpg.entity.player.UpdateRedScreen;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class database {
    public static final Map<Player, UpdateRedScreen> ShowRedScreenPlayerList = new HashMap<>();
    public static final Map<Player, marumasa.type_three_rpg.entity.player.PlayerData> PlayerData = new HashMap<>();
    public static final Map<Entity, EntityData> EntityData = new HashMap<>();
}
