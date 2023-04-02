package marumasa.type_three_rpg.config;

import org.bukkit.configuration.file.FileConfiguration;

public class Role {
    public final String pharmacist;
    public final String gladiator;

    public Role(FileConfiguration config) {
        pharmacist = config.getString("role.pharmacist");
        gladiator = config.getString("role.gladiator");
    }
}
