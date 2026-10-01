package com.horrorstalker.client.model;

import com.horrorstalker.HorrorStalker;
import com.horrorstalker.entity.DaddyInRedEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class DaddyInRedModel extends EntityModel<DaddyInRedEntity> {
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(new ResourceLocation(HorrorStalker.MOD_ID, "daddyinred"), "main");

    private final ModelPart All;
    private final ModelPart Head;
    private final ModelPart Legleft;
    private final ModelPart Legright;
    private final ModelPart Body;
    private final ModelPart Righthand;
    private final ModelPart Lefthand;

    public DaddyInRedModel(ModelPart root) {
        this.All = root.getChild("All");
        this.Head = this.All.getChild("Head");
        this.Legleft = this.All.getChild("Legleft");
        this.Legright = this.All.getChild("Legright");
        this.Body = this.All.getChild("Body");
        this.Righthand = this.All.getChild("Righthand");
        this.Lefthand = this.All.getChild("Lefthand");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition All = partdefinition.addOrReplaceChild("All", CubeListBuilder.create(),
                PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition Head = All.addOrReplaceChild("Head", CubeListBuilder.create(),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        Head.addOrReplaceChild("cube_r1",
                CubeListBuilder.create().texOffs(0, 0)
                        .addBox(-100.0F, -270.0F, -1.0F, 223.0F, 270.0F, 0.0F,
                                new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -529.0F, -121.0F, -0.0873F, 0.0F, 0.0F));

        All.addOrReplaceChild("Legleft",
                CubeListBuilder.create().texOffs(216, 270)
                        .addBox(5.0F, -340.0F, 0.0F, 14.0F, 340.0F, 0.0F,
                                new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        All.addOrReplaceChild("Legright",
                CubeListBuilder.create().texOffs(188, 270)
                        .addBox(-19.0F, -255.0F, -3.0F, 14.0F, 340.0F, 0.0F,
                                new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -85.0F, 3.0F));

        PartDefinition Body = All.addOrReplaceChild("Body", CubeListBuilder.create(),
                PartPose.offset(0.0F, -400.0F, -20.0F));

        Body.addOrReplaceChild("cube_r2",
                CubeListBuilder.create().texOffs(104, 270)
                        .addBox(-23.0F, -296.0F, 1.0F, 42.0F, 318.0F, 0.0F,
                                new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 47.0F, 13.0F, 0.3491F, 0.0F, 0.0F));

        PartDefinition Righthand = All.addOrReplaceChild("Righthand", CubeListBuilder.create(),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        Righthand.addOrReplaceChild("cube_r3",
                CubeListBuilder.create().texOffs(52, 270)
                        .addBox(0.1528F, -108.0F, -11.7884F, 0.0F, 595.0F, 26.0F,
                                new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-22.0F, -527.0F, -96.0F, 0.0F, 2.4435F, 0.0F));

        PartDefinition Lefthand = All.addOrReplaceChild("Lefthand", CubeListBuilder.create(),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        Lefthand.addOrReplaceChild("cube_r4",
                CubeListBuilder.create().texOffs(0, 270)
                        .addBox(1.0F, -108.0F, -48.0F, 0.0F, 595.0F, 26.0F,
                                new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(48.0F, -527.0F, -84.0F, 0.0F, 0.9599F, 0.0F));

        return LayerDefinition.create(meshdefinition, 1024, 1024);
    }

    @Override
    public void setupAnim(DaddyInRedEntity entity, float limbSwing, float limbSwingAmount,
                          float ageInTicks, float netHeadYaw, float headPitch) {
        float walk = Mth.clamp(limbSwingAmount, 0.0F, 1.0F);
        float phase = limbSwing * 1.6F;

        Legright.xRot = Mth.cos(phase) * 0.16F * walk;
        Legleft.xRot = Mth.cos(phase + Mth.PI) * 0.16F * walk;
        Righthand.xRot = Mth.cos(phase + Mth.PI) * 0.10F * walk;
        Lefthand.xRot = Mth.cos(phase) * 0.10F * walk;

        Head.xRot = headPitch * Mth.DEG_TO_RAD * 0.55F;
        Head.yRot = netHeadYaw * Mth.DEG_TO_RAD * 0.55F;
        Head.zRot = Mth.sin(ageInTicks * 0.08F) * 0.08F;
        All.y = 24.0F + Mth.sin(ageInTicks * 0.10F) * 0.5F;
    }
}
