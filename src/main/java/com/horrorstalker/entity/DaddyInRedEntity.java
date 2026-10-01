package com.horrorstalker.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;

public class DaddyInRedEntity extends HorrorMobEntity {
    public DaddyInRedEntity(EntityType<? extends DaddyInRedEntity> type, Level level) {
        super(type, level);
        this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).setBaseValue(0.35D);
        this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).setBaseValue(8.0D);
        this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.KNOCKBACK_RESISTANCE).setBaseValue(0.55D);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return horrorAttributes();
    }
}
