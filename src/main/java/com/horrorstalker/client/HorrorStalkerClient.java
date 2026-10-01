package com.horrorstalker.client;

import com.horrorstalker.HorrorStalker;
import com.horrorstalker.client.model.DaddyInRedModel;
import com.horrorstalker.client.model.WhistlerModel;
import com.horrorstalker.client.renderer.DaddyInRedRenderer;
import com.horrorstalker.client.renderer.WhistlerRenderer;
import com.horrorstalker.entity.DaddyInRedEntity;
import com.horrorstalker.entity.WhistlerEntity;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class HorrorStalkerClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(WhistlerModel.LAYER_LOCATION, WhistlerModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(DaddyInRedModel.LAYER_LOCATION, DaddyInRedModel::createBodyLayer);

        EntityRenderers.register(HorrorStalker.WHISTLER, WhistlerRenderer::new);
        EntityRenderers.register(HorrorStalker.DADDY_IN_RED, DaddyInRedRenderer::new);
    }
}
