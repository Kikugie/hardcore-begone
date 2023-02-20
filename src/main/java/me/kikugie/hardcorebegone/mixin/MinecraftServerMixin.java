package me.kikugie.hardcorebegone.mixin;

import me.kikugie.hardcorebegone.HardcoreMod;
import net.minecraft.server.MinecraftServer;
import org.apache.commons.io.FileUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
    @Inject(method = "shutdown", at = @At("RETURN"))
    private void worldBeGone(CallbackInfo ci) {
        if (HardcoreMod.worldLocation == null) return;

        try {
            FileUtils.deleteDirectory(HardcoreMod.worldLocation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            HardcoreMod.worldLocation = null;
        }
    }
}
