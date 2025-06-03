package net.terminator.network;

import net.minecraft.client.MinecraftClient;

import java.util.concurrent.CompletableFuture;

public class FakeServerAPI implements ServerAPI {

    @Override
    public CompletableFuture<ServerResponse<String>> requestAccountRank(String uuid) {
        String username = MinecraftClient.getInstance().getSession().getUsername();

        System.out.println("[FakeServer] Failed to retrieve rank for " + username + " (" + uuid + "): Server unavailable");

        return CompletableFuture.completedFuture(
            ServerResponse.failure("Network:NoResponse")
        );
    }

    @Override
    public CompletableFuture<ServerResponse<Boolean>> uploadStructure(String schematicName, byte[] schematicData) {
        System.out.println("[FakeServer] Failed to upload structure '" + schematicName + "': Server unavailable");

        return CompletableFuture.completedFuture(
            ServerResponse.failure("Network:NoResponse")
        );
    }
}
