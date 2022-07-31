package thatpreston.mermod.client.render;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.VertexConsumers;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;

import java.util.concurrent.atomic.AtomicInteger;

public class TailModel<T extends LivingEntity> extends AnimalModel<T> {
    private final ModelPart main;
    private final ModelPart waist;
    private final ModelPart bra;
    private final ModelPart tail1;
    private final ModelPart tail2;
    private final ModelPart tail3;
    private final ModelPart tail4;
    private final ModelPart tail5;
    private final ModelPart tail6;
    private final ModelPart tail7;
    private final ModelPart fin1;
    private final ModelPart fin2;
    private static final float r = (float)(Math.PI / 180);
    public TailModel(ModelPart root) {
        main = root.getChild("main");
        waist = main.getChild("waist");
        bra = main.getChild("bra");
        tail1 = main.getChild("tail1");
        tail2 = tail1.getChild("tail2");
        tail3 = tail2.getChild("tail3");
        tail4 = tail3.getChild("tail4");
        tail5 = tail4.getChild("tail5");
        tail6 = tail5.getChild("tail6");
        tail7 = tail6.getChild("tail7");
        fin1 = tail7.getChild("fin1");
        fin2 = fin1.getChild("fin2");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData main = modelPartData.addChild("main", ModelPartBuilder.create().cuboid(-4, 0, -2, 8, 12, 4), ModelTransform.NONE);
        main.addChild("waist", ModelPartBuilder.create().uv(24, 0).cuboid(-4, 0, -2, 8, 12, 4), ModelTransform.NONE);
        main.addChild("bra", ModelPartBuilder.create().uv(24, 16).cuboid(-4, 0, -2, 8, 12, 4), ModelTransform.NONE);
        ModelPartData tail1 = main.addChild("tail1", ModelPartBuilder.create().uv(0, 0).cuboid(-4, 0, -2, 8, 4, 4), ModelTransform.pivot(0, 12, 0));
        ModelPartData tail2 = tail1.addChild("tail2", ModelPartBuilder.create().uv(0, 8).cuboid(-3.75F, 0, -1.75F, 7.5F, 3, 3.5F), ModelTransform.pivot(0, 4, 0));
        ModelPartData tail3 = tail2.addChild("tail3", ModelPartBuilder.create().uv(0, 15).cuboid(-3.5F, 0, -1.5F, 7, 2, 3), ModelTransform.pivot(0, 3, 0));
        ModelPartData tail4 = tail3.addChild("tail4", ModelPartBuilder.create().uv(0, 20).cuboid(-3.25F, 0, -1.25F, 6.5F, 2, 2.5F), ModelTransform.pivot(0, 2, 0));
        ModelPartData tail5 = tail4.addChild("tail5", ModelPartBuilder.create().uv(0, 25).cuboid(-3, 0, -1, 6, 2, 2), ModelTransform.pivot(0, 2, 0));
        ModelPartData tail6 = tail5.addChild("tail6", ModelPartBuilder.create().uv(0, 29).cuboid(-2.75F, 0, -0.75F, 5.5F, 2, 1.5F), ModelTransform.pivot(0, 2, 0));
        ModelPartData tail7 = tail6.addChild("tail7", ModelPartBuilder.create().uv(0, 33).cuboid(-2.5F, 0, -0.5F, 5, 2, 1), ModelTransform.pivot(0, 2, 0));
        ModelPartData fin1 = tail7.addChild("fin1", ModelPartBuilder.create().uv(0, 36).cuboid(-11.5F, 0, 0.02F, 23, 24, 1), ModelTransform.pivot(0, 2, 0));
        fin1.addChild("fin2", ModelPartBuilder.create().uv(0, 36).cuboid(-11.5F, 0, -0.04F, 23, 24, 1), ModelTransform.rotation(0, 180 * r, 0));
        return TexturedModelData.of(modelData, 48, 61);
    }
    @Override
    protected Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of(main);
    }
    @Override
    protected Iterable<ModelPart> getHeadParts() {
        return ImmutableList.of();
    }
    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {}
    private static float sine(int a, int b, float t) {
        return (float)((-(b - a)) / 2 * (Math.cos(Math.PI * t) - 1) + a);
    }
    private static float angle(float a, int b, float c) {
        float d = ((a - b) * c) % 40;
        float e = ((a - b) * c) % 80;
        return e < 40 ? sine(-20, 20, d / 40) : sine(20, -20, d / 40);
    }
    private void swimPose(float age) {
        tail1.pitch = tail2.pitch = angle(age, 0, 2.5F) * r;
        tail3.pitch = angle(age, 5, 2.5F) * r;
        tail4.pitch = tail5.pitch = angle(age, 10, 2.5F) * r;
        tail6.pitch = tail7.pitch = fin1.pitch = angle(age, 15, 2.5F) * r;
    }
    private void idlePose(float angle) {
        tail1.pitch = angle * r;
        tail2.pitch = tail3.pitch = tail4.pitch = tail5.pitch = tail6.pitch = tail7.pitch = fin1.pitch = 0;
    }
    private void landPose() {
        tail1.pitch = tail2.pitch = tail3.pitch = tail4.pitch = tail5.pitch = tail6.pitch = tail7.pitch = 14 * r;
        fin1.pitch = 0;
    }
    public void updatePose(PlayerEntity entity, BipedEntityModel context, float age) {
        context.copyStateTo(this);
        this.main.copyTransform(context.body);
        if(entity.isSleeping()) {
            idlePose(0);
        } else if(context.riding) {
            idlePose(-90);
        } else if(entity.isOnGround() && !entity.isSwimming()) {
            landPose();
        } else {
            swimPose(age);
        }
    }
    public void renderTail(MatrixStack stack, VertexConsumerProvider provider, int light, int overlay, MermaidTailStyle style) {
        VertexConsumer consumer = ItemRenderer.getDirectItemGlintConsumer(provider, RenderLayer.getEntityTranslucentCull(style.getTexture()), false, style.getHasGlint());
        float tailRed = (style.getTailColor() >> 16 & 255) / 255.0F;
        float tailGreen = (style.getTailColor() >> 8 & 255) / 255.0F;
        float tailBlue = (style.getTailColor() & 255) / 255.0F;
        float braRed = (style.getBraColor() >> 16 & 255) / 255.0F;
        float braGreen = (style.getBraColor() >> 8 & 255) / 255.0F;
        float braBlue = (style.getBraColor() & 255) / 255.0F;
        float gradientRed = (style.getGradientColor() >> 16 & 255) / 255.0F;
        float gradientGreen = (style.getGradientColor() >> 8 & 255) / 255.0F;
        float gradientBlue = (style.getGradientColor() & 255) / 255.0F;
        this.main.rotate(stack);
        stack.scale(1.01F, 1.01F, 1.01F);
        stack.translate(0, -0.00375F, 0);
        this.waist.render(stack, consumer, light, overlay, tailRed, tailGreen, tailBlue, 1);
        if(style.getHasBra()) {
            this.bra.render(stack, consumer, light, overlay, braRed, braGreen, braBlue, 1);
        }
        AtomicInteger index = new AtomicInteger();
        this.tail1.forEachCuboid(stack, (entry, path, cuboidIndex, cuboid) -> {
            float red = tailRed;
            float green = tailGreen;
            float blue = tailBlue;
            if(style.getHasGradient()) {
                if(index.get() < 7) {
                    float delta = index.get() / 7.0F;
                    red = MathHelper.lerp(delta, red, gradientRed);
                    green = MathHelper.lerp(delta, green, gradientGreen);
                    blue = MathHelper.lerp(delta, blue, gradientBlue);
                } else {
                    red = gradientRed;
                    green = gradientGreen;
                    blue = gradientBlue;
                }
            }
            cuboid.renderCuboid(entry, consumer, light, overlay, red, green, blue, 1);
            index.getAndIncrement();
        });
    }
}