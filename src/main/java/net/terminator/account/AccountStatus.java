package net.terminator.account;

public class AccountStatus {
    public static String getUsername() {
        return AccountManager.getCurrentAccount().getUsername();
    }

    public static String getRank() {
        return AccountManager.getCurrentAccount().getRank();
    }

    public static void refresh() {
        AccountManager.refreshAccountInfo();
    }
}
