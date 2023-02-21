package me.kikugie.hardcorebegone.mixin;

import me.kikugie.hardcorebegone.HardcoreMod;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.file.Paths;
import java.util.Objects;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {
    @Shadow
    @Final
    public MinecraftServer server;
    @Shadow
    public ServerPlayNetworkHandler networkHandler;

    @Inject(method = "onDeath", at = @At("HEAD"))
    private void adiosHardcoreWorld(DamageSource damageSource, CallbackInfo ci) {
        if (!HardcoreMod.isIntegratedHardcoreServer() || !Objects.equals(this.getClass(), ServerPlayerEntity.class))
            return;
        HardcoreMod.worldLocation = Paths.get(this.server.getRunDirectory().getAbsolutePath(), "saves", this.server.getOverworld().getChunkManager().threadedAnvilChunkStorage.getSaveDir()).toFile();
        this.networkHandler.disconnect(Text.of("§cRoses are red;\nThis journey was fun,\nbut now your hardcore world is gone."));
    }
}
