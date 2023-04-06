package marumasa.type_three_rpg.entity.base;

import org.bukkit.entity.Entity;

public class EntityBase {

    private int hitPoint = 0;

    private int physicalAttack = 0;
    private int magicAttack = 0;

    private int physicalDefense = 0;
    private int magicDefense = 0;

    private int level = 0;
    private int powerAttackPoint = 0;

    public EntityBase(Entity entity) {
    }

    public void remove() {
    }

    public void attack() {
    }

    public void damage() {
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
