package marumasa.type_three_rpg;

import marumasa.type_three_rpg.entity.player.PlayerData;
import marumasa.type_three_rpg.entity.player.UpdateRedScreen;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class database {
    public static final Map<Player, UpdateRedScreen> ShowRedScreenPlayerList = new HashMap<>();
    public static final Map<Player, PlayerData> PlayerData = new HashMap<>();
}
