package net.terminator;

import net.fabricmc.api.ModInitializer;
import net.terminator.config.TerminatorConfig;
import net.terminator.account.AccountManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TerminatorMod implements ModInitializer {
    public static final String MOD_ID = "terminator";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Terminator Mod");
        TerminatorConfig.loadConfig();

        String serverUrl = "";
        if (serverUrl != null && !serverUrl.isEmpty()) {
            AccountManager.setServerEndpoint(serverUrl);
        } else {
            LOGGER.warn("Terminator account server URL not provided. Using default account data.");
        }
    }
}
