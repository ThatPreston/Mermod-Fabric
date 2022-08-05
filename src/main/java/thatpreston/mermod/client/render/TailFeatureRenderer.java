/*
Mermod adds a magical necklace that transforms you into a merperson in water!
Copyright (C) 2022 ThatPreston
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.
You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

package thatpreston.mermod.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import thatpreston.mermod.Mermod;
import thatpreston.mermod.MermodClient;

@Environment(EnvType.CLIENT)
public class TailFeatureRenderer<T extends PlayerEntity, M extends BipedEntityModel<T>> extends FeatureRenderer<T, M> {
    private TailModel model;
    public TailFeatureRenderer(FeatureRendererContext<T, M> context, EntityModelLoader loader) {
        super(context);
        this.model = new TailModel(loader.getModelPart(MermodClient.TAIL));
    }
    @Override
    public void render(MatrixStack stack, VertexConsumerProvider provider, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if(Mermod.checkTailConditions(entity)) {
            MermaidTailStyle style = Mermod.getTailStyle(entity);
            if(style != null) {
                stack.push();
                this.model.updatePose(entity, this.getContextModel(), animationProgress);
                this.model.renderTail(stack, provider, light, OverlayTexture.DEFAULT_UV, style);
                stack.pop();
            }
        }
    }
}