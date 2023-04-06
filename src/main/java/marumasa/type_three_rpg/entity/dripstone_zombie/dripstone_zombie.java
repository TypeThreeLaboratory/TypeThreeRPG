package marumasa.type_three_rpg.entity.dripstone_zombie;

public class dripstone_zombie {
    /*public dripstone_zombie(Entity entity) {
        super(entity);
    }

    public static void Attack(LivingEntity livingTarget, Zombie zombie, minecraft mc) {

        EntityEquipment entityEquipment = zombie.getEquipment();

        if (entityEquipment.getItemInMainHand().getType() == Material.DRIPSTONE_BLOCK &&
                entityEquipment.getItemInOffHand().getType() == Material.DRIPSTONE_BLOCK) {
            livingTarget.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 0));
            entityEquipment.setItemInMainHand(new ItemStack(Material.AIR));
            entityEquipment.setItemInOffHand(new ItemStack(Material.AIR));
        }

        final EntityData entityData = database.EntityData.get(zombie);
        if (entityData == null) return;

        if (entityData.getPowerAttackPoint() > 2 && new Random().nextInt(3) == 0)
            PowerAttack(zombie, entityEquipment);
    }

    public static void Damage(Zombie zombie, minecraft mc) {

        final EntityData entityData = database.EntityData.get(zombie);
        if (entityData == null) return;

        if (entityData.getPowerAttackPoint() > 2 && new Random().nextInt(3) == 0)
            PowerAttack(zombie);
    }

    private static void PowerAttack(Zombie zombie) {

        EntityEquipment entityEquipment = zombie.getEquipment();

        entityEquipment.setItemInMainHand(new ItemStack(Material.DRIPSTONE_BLOCK));
        entityEquipment.setItemInOffHand(new ItemStack(Material.DRIPSTONE_BLOCK));

        final EntityData entityData = database.EntityData.get(zombie);
        if (entityData == null) return;

        entityData.setPowerAttackPoint(0);
    }

    private static void PowerAttack(Zombie zombie, EntityEquipment entityEquipment) {
        entityEquipment.setItemInMainHand(new ItemStack(Material.DRIPSTONE_BLOCK));
        entityEquipment.setItemInOffHand(new ItemStack(Material.DRIPSTONE_BLOCK));

        final EntityData entityData = database.EntityData.get(zombie);
        if (entityData == null) return;

        entityData.setPowerAttackPoint(0);
    }*/
}
