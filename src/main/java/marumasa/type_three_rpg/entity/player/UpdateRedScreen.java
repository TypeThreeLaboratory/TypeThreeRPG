package marumasa.type_three_rpg.entity.player;

import com.github.yannicklamprecht.worldborder.api.WorldBorderApi;
import marumasa.type_three_rpg.database;
import marumasa.type_three_rpg.minecraft;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class UpdateRedScreen extends BukkitRunnable {
    final Player pl;
    final WorldBorderApi wbApi;
    final minecraft mc;

    public UpdateRedScreen(Player player, WorldBorderApi worldBorderApi, minecraft minecraft) {

        pl = player;
        wbApi = worldBorderApi;
        mc = minecraft;

        database.ShowRedScreenPlayerList.put(pl, this);

    }

    @Override
    public void run() {

        final AttributeInstance MaxHealthAttribute = pl.getAttribute(Attribute.GENERIC_MAX_HEALTH);

        if (MaxHealthAttribute == null) return;

        final double Health = pl.getHealth();
        final double MaxHealth = MaxHealthAttribute.getValue();

        if (Health / MaxHealth > 0.4) {
            database.ShowRedScreenPlayerList.remove(pl);
            this.cancel();
        } else {
            //wbApi.sendRedScreenForSeconds(pl, 1, mc);
        }
    }
}
