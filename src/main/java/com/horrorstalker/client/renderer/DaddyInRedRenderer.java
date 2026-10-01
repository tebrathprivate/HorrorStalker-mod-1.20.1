package com.horrorstalker.client.renderer;

import com.horrorstalker.HorrorStalker;
import com.horrorstalker.client.model.DaddyInRedModel;
import com.horrorstalker.entity.DaddyInRedEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DaddyInRedRenderer extends MobRenderer<DaddyInRedEntity, DaddyInRedModel> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(HorrorStalker.MOD_ID, "textures/entity/daddy_in_red.png");

    public DaddyInRedRenderer(EntityRendererProvider.Context context) {
        super(context, new DaddyInRedModel(context.bakeLayer(DaddyInRedModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    protected void scale(DaddyInRedEntity entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(0.06F, 0.06F, 0.06F);
    }

    @Override
    public ResourceLocation getTextureLocation(DaddyInRedEntity entity) {
        return TEXTURE;
    }
}
