package marumasa.type_three_rpg;

import marumasa.type_three_rpg.DamageDisplay.RemoveDamageDisplay;
import marumasa.type_three_rpg.HealthBar.UndoHealthBar;
import marumasa.type_three_rpg.entity.player.UpdateRedScreen;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;

import java.util.HashMap;
import java.util.Map;

public class database {
    public static final Map<Entity, UndoHealthBar> ShowHealthBarEntityList = new HashMap<>();
    public static final Map<Player, UpdateRedScreen> ShowRedScreenPlayerList = new HashMap<>();
    public static final Map<TextDisplay, RemoveDamageDisplay> DamageDisplayList = new HashMap<>();
}
