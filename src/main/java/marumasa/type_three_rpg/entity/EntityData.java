package marumasa.type_three_rpg.entity;

import org.bukkit.entity.Entity;

public class EntityData {
    private int hitPoint;

    private int physicalAttack;
    private int magicAttack;

    private int physicalDefense;
    private int magicDefense;

    private int level;
    private int powerAttackPoint;

    public EntityData(Entity entity) {
        hitPoint = 0;
    }

    public int getHitPoint() {
        return hitPoint;
    }
    public void setHitPoint(int value) {
        hitPoint = value;
    }
    public int getPhysicalAttack() {
        return physicalAttack;
    }
    public void setPhysicalAttack(int value) {
        physicalAttack = value;
    }
    public int getMagicAttack() {
        return magicAttack;
    }
    public void setMagicAttack(int value) {
        magicAttack = value;
    }
    public int getPhysicalDefense() {
        return physicalDefense;
    }
    public void setPhysicalDefense(int value) {
        physicalDefense = value;
    }
    public int getMagicDefense() {
        return magicDefense;
    }
    public void setMagicDefense(int value) {
        magicDefense = value;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int value) {
        level = value;
    }
    public int getPowerAttackPoint() {
        return powerAttackPoint;
    }
    public void setPowerAttackPoint(int value) {
        powerAttackPoint = value;
    }

}
