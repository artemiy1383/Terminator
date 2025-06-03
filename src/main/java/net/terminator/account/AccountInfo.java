package net.terminator.account;

public class AccountInfo {
    private final String username;
    private final String rank;

    public AccountInfo(String username, String rank) {
        this.username = username;
        this.rank = rank;
    }

    public String getUsername() {
        return username;
    }

    public String getRank() {
        return rank;
    }
}
