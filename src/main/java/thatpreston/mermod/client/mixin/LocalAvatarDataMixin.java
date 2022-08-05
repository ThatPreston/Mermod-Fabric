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

package thatpreston.mermod.client.mixin;

import net.blancworks.figura.avatar.AvatarData;
import net.blancworks.figura.avatar.LocalAvatarData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thatpreston.mermod.integration.figura.MermodFiguraAPI;

import java.util.UUID;

@Mixin(LocalAvatarData.class)
public abstract class LocalAvatarDataMixin extends AvatarData {
    public LocalAvatarDataMixin(UUID id) {
        super(id);
    }
    @Inject(method = "loadModelFile", at = @At("HEAD"), remap = false)
    public void onLoadModelFile(CallbackInfo info) {
        if(this.entityId != null) {
            MermodFiguraAPI.DISABLED.remove(this.entityId);
        }
    }
}