package marumasa.type_three_rpg.entity;

import marumasa.type_three_rpg.Minecraft;
import marumasa.type_three_rpg.entity.base.ItemDisplayBase;
import org.bukkit.entity.Husk;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class target_dummy extends ItemDisplayBase {

    private final Husk target;

    public target_dummy(Husk husk, Minecraft minecraft) {
        super(husk, minecraft, 1001, 1002, 0.95f);

        target = husk;
        target.setAdult();

        target.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, -1, 4, true, false));

        target.setAI(false);
    }

    @Override
    public void damage() {
        super.damage();
        target.getWorld().playSound(target.getLocation(), "block.wool.break", 1, 1);
    }
}