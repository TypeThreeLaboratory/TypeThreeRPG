package marumasa.type_three_rpg;

import marumasa.type_three_rpg.HealthBar.UndoHealthBar;
import marumasa.type_three_rpg.entity.player.UpdateRedScreen;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class database {
    public static final Map<LivingEntity, UndoHealthBar> ShowHealthBarEntityList = new HashMap<>();
    public static final Map<Player, UpdateRedScreen> ShowRedScreenPlayerList = new HashMap<>();
}
