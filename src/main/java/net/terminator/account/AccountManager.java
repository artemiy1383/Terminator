package net.terminator.account;

import net.minecraft.client.MinecraftClient;

public class AccountManager {

    private static AccountInfo currentAccount = new AccountInfo("Unknown", "Network:NoResponse");
    private static String serverEndpoint = null; // по умолчанию сервер не задан

    public static AccountInfo getCurrentAccount() {
        return currentAccount;
    }

    public static void refreshAccountInfo() {
        String username = MinecraftClient.getInstance().getSession().getUsername();

        if (serverEndpoint == null || serverEndpoint.isEmpty()) {
            currentAccount = new AccountInfo(username, "Network:NoResponse");
        } else {
            // Здесь в будущем будет логика обращения к серверу
            currentAccount = new AccountInfo(username, "Network:ConnectedTo:" + serverEndpoint);
        }
    }

    public static void setServerEndpoint(String endpoint) {
        serverEndpoint = endpoint;
    }

    public static String getServerEndpoint() {
        return serverEndpoint;
    }
}
