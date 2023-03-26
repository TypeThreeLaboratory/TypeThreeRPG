package marumasa.type_three_rpg.config;

import marumasa.type_three_rpg.minecraft;
import org.bukkit.configuration.file.FileConfiguration;

public class config {
    public final Tag tag;

    public config(final minecraft plugin) {
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();
        tag = new Tag(config);
    }
}
