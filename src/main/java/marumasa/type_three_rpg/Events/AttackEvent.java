package marumasa.type_three_rpg.Events;

import marumasa.type_three_rpg.DamageDisplay.SummonDamageDisplay;
import marumasa.type_three_rpg.entity.player.Attack;
import marumasa.type_three_rpg.minecraft;
import org.bukkit.entity.*;

public class AttackEvent {
    public static double main(LivingEntity attacker, LivingEntity target, double damage, minecraft mc) {


        //攻撃力を計算
        final double AttackDamage = Attack.calculate(damage);

        //攻撃したエンティティがプレイヤーだったら
        if (attacker instanceof Player player) {
            PlayerAttackEvent.main(player, AttackDamage, mc);
        }

        SummonDamageDisplay.run(target, AttackDamage, mc);

        return AttackDamage;
    }

}
