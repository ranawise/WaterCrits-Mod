package com.watercrits.mod.mixin;

import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Player.class)
public abstract class PlayerAttackMixin {

    
    @Redirect(
        method = "attack",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;isInWater()Z"),
        require = 0
    )
    private boolean watercrits$allowCritInWater(Player instance) {
        return false; 
    }

    @Redirect(
        method = "attack",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;isInWaterOrBubble()Z"),
        require = 0
    )
    private boolean watercrits$allowCritInWaterOrBubble(Player instance) {
        return false;
    }
}
