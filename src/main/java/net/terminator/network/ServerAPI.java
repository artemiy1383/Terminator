package net.terminator.network;

import java.util.concurrent.CompletableFuture;

public interface ServerAPI {
    CompletableFuture<ServerResponse<String>> requestAccountRank(String uuid);
    CompletableFuture<ServerResponse<Boolean>> uploadStructure(String schematicName, byte[] schematicData);
}
