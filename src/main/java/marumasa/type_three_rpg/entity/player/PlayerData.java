package marumasa.type_three_rpg.entity.player;

import org.bukkit.entity.Player;

public class PlayerData {
    public int hitPoint;

    public int physicalAttack;
    public int magicAttack;

    public int physicalDefense;
    public int magicDefense;

    public String role;
    public int level;

    public PlayerData(Player player) {
        hitPoint = 20;
        physicalAttack = 5;
        magicAttack = 5;

        physicalDefense = 5;
        magicDefense = 5;

        role = "§d魔法使い";
    }
}
