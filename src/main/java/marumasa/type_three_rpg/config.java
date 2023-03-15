package marumasa.type_three_rpg;

import org.bukkit.configuration.file.FileConfiguration;

public class config {
    public config(final minecraft plugin) {
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();

    }
}
