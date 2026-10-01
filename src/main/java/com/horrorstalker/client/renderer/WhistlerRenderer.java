package com.horrorstalker.client.renderer;

import com.horrorstalker.HorrorStalker;
import com.horrorstalker.client.model.WhistlerModel;
import com.horrorstalker.entity.WhistlerEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class WhistlerRenderer extends MobRenderer<WhistlerEntity, WhistlerModel> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(HorrorStalker.MOD_ID, "textures/entity/whistler.png");

    public WhistlerRenderer(EntityRendererProvider.Context context) {
        super(context, new WhistlerModel(context.bakeLayer(WhistlerModel.LAYER_LOCATION)), 0.45F);
    }

    @Override
    protected void scale(WhistlerEntity entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(0.30F, 0.30F, 0.30F);
    }

    @Override
    public ResourceLocation getTextureLocation(WhistlerEntity entity) {
        return TEXTURE;
    }
}
