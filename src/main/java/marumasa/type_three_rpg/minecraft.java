package marumasa.type_three_rpg;

import com.github.yannicklamprecht.worldborder.api.WorldBorderApi;
import marumasa.type_three_rpg.Events.events;
import marumasa.type_three_rpg.config.config;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class minecraft extends JavaPlugin {

    private final Logger logger = Bukkit.getLogger();

    @Override
    public void onEnable() {
        config config = new config(this);


        final Server server = getServer();
        RegisteredServiceProvider<WorldBorderApi> worldBorderApiRegisteredServiceProvider = server.getServicesManager().getRegistration(WorldBorderApi.class);

        if (worldBorderApiRegisteredServiceProvider == null) {
            getLogger().info("WorldBorderAPI not found");
            server.getPluginManager().disablePlugin(this);
            return;
        }

        final WorldBorderApi worldBorderApi = worldBorderApiRegisteredServiceProvider.getProvider();
        server.getPluginManager().registerEvents(new events(config, this, worldBorderApi), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
