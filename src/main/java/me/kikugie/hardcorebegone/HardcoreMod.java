package me.kikugie.hardcorebegone;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.integrated.IntegratedServer;

import java.io.File;

public class HardcoreMod implements ModInitializer {
    public static File worldLocation;
    private static boolean isIntegratedHardcoreServer = false;

    public static boolean isIntegratedHardcoreServer() {
        return isIntegratedHardcoreServer;
    }

    @Override
    public void onInitialize() {
        ServerPlayConnectionEvents.INIT.register((handler, server) -> isIntegratedHardcoreServer = server.isHardcore() && server instanceof IntegratedServer);
    }
}
