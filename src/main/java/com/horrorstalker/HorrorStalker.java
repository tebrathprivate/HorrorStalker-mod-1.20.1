package com.horrorstalker;

import com.horrorstalker.entity.DaddyInRedEntity;
import com.horrorstalker.entity.HorrorMobEntity;
import com.horrorstalker.entity.WhistlerEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;

public class HorrorStalker implements ModInitializer {
    public static final String MOD_ID = "horrorstalker";

    public static final EntityType<WhistlerEntity> WHISTLER = EntityType.Builder
            .of(WhistlerEntity::new, MobCategory.MONSTER)
            .sized(0.9F, 2.4F)
            .clientTrackingRange(128)
            .updateInterval(2)
            .build("whistler");

    public static final EntityType<DaddyInRedEntity> DADDY_IN_RED = EntityType.Builder
            .of(DaddyInRedEntity::new, MobCategory.MONSTER)
            .sized(0.9F, 2.5F)
            .clientTrackingRange(128)
            .updateInterval(2)
            .build("daddy_in_red");

    @Override
    public void onInitialize() {
        BuiltInRegistries.ENTITY_TYPE.register(new ResourceLocation(MOD_ID, "whistler"), WHISTLER);
        BuiltInRegistries.ENTITY_TYPE.register(new ResourceLocation(MOD_ID, "daddy_in_red"), DADDY_IN_RED);
        FabricDefaultAttributeRegistry.register(WHISTLER, WhistlerEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(DADDY_IN_RED, DaddyInRedEntity.createAttributes());

        ServerTickEvents.END_WORLD_TICK.register(HorrorStalker::horrorEvents);
    }

    private static void horrorEvents(ServerLevel level) {
        if (level.dimension() != Level.OVERWORLD || !level.isNight()) {
            return;
        }

        for (ServerPlayer player : level.players()) {
            // Rare ambience/jumpscare event.
            if (level.random.nextInt(700) == 0) {
                player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 45));
                level.playSound(null, player.blockPosition(), SoundEvents.WARDEN_HEARTBEAT,
                        SoundSource.HOSTILE, 0.7F, 0.8F + level.random.nextFloat() * 0.3F);
            }

            // Rare stalking spawn. The mob starts far away, then uses its huge follow range.
            if (level.random.nextInt(2600) != 0) {
                continue;
            }

            if (!level.getEntitiesOfClass(HorrorMobEntity.class, player.getBoundingBox().inflate(96.0D),
                    mob -> true).isEmpty()) {
                continue;
            }

            spawnStalker(level, player);
        }
    }

    private static void spawnStalker(ServerLevel level, ServerPlayer player) {
        RandomSource random = level.random;

        double angle = random.nextDouble() * Math.PI * 2.0D;
        double distance = 42.0D + random.nextDouble() * 38.0D;
        int x = (int) Math.floor(player.getX() + Math.cos(angle) * distance);
        int z = (int) Math.floor(player.getZ() + Math.sin(angle) * distance);
        int y = level.getHeightmapPos(
                net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, new BlockPos(x, 0, z)
        ).getY();

        BlockPos pos = new BlockPos(x, y, z);
        if (!level.getBlockState(pos.below()).isSolid() || !level.getBlockState(pos).isAir()) {
            return;
        }

        boolean daddy = random.nextBoolean();
        HorrorMobEntity mob = daddy ? DADDY_IN_RED.create(level) : WHISTLER.create(level);
        if (mob == null) return;

        mob.moveTo(x + 0.5D, y, z + 0.5D, random.nextFloat() * 360.0F, 0.0F);
        mob.setPersistenceRequired();
        level.addFreshEntity(mob);
        level.playSound(null, pos, SoundEvents.AMBIENT_CAVE, SoundSource.HOSTILE, 0.8F, 0.6F);
    }
}
