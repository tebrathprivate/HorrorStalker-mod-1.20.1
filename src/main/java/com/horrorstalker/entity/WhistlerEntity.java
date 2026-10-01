package com.horrorstalker.entity;

import com.horrorstalker.HorrorStalker;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;

public class WhistlerEntity extends HorrorMobEntity {
    public WhistlerEntity(EntityType<? extends WhistlerEntity> type, Level level) {
        super(type, level);
        this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).setBaseValue(0.32D);
        this.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).setBaseValue(5.0D);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return horrorAttributes();
    }
}
