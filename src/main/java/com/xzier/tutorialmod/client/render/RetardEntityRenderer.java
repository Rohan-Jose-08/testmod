package com.xzier.tutorialmod.client.render;

import com.xzier.tutorialmod.Entities.RetardEntity;
import com.xzier.tutorialmod.TutorialMod;
import com.xzier.tutorialmod.client.model.RetardEntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RetardEntityRenderer extends MobRenderer<RetardEntity, RetardEntityModel<RetardEntity>> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(TutorialMod.MODID, "textures/entity/retard_entity.png");

    public RetardEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new RetardEntityModel<>(ctx.bakeLayer(RetardEntityModel.LAYER_LOCATION)), 1.0f);
    }

    @Override
    public ResourceLocation getTextureLocation(RetardEntity entity) {
        return TEXTURE;
    }
}
