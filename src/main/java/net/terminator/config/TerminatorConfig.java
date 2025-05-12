package net.terminator.config;

public class TerminatorConfig {
    private static final TerminatorConfig INSTANCE = new TerminatorConfig();

    public static TerminatorConfig getInstance() {
        return INSTANCE;
    }

    public static void loadConfig() {
        // Ничего не загружается
    }

    public static void saveConfig() {
        // Ничего не сохраняется
    }
}

