package marumasa.type_three_rpg.config;

import org.bukkit.configuration.file.FileConfiguration;

public class Tag {
    public final String HealthBar;
    public final String DamageDisplay;

    public Tag(FileConfiguration config) {
        HealthBar = config.getString("tag.HealthBar");
        DamageDisplay = config.getString("tag.DamageDisplay");
    }
}
