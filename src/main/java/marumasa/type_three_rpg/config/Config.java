package marumasa.type_three_rpg.config;

import marumasa.type_three_rpg.Minecraft;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    public final Tag tag;
    public final Role role;

    public Config(final Minecraft plugin) {
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();
        tag = new Tag(config);
        role = new Role(config);
    }
}
