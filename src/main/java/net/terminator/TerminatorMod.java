package net.terminator;

import net.fabricmc.api.ModInitializer;
import net.terminator.config.TerminatorConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TerminatorMod implements ModInitializer {
    public static final String MOD_ID = "terminator";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
        @Override
    public void onInitialize() {
        LOGGER.info("Initializing Terminator Mod");
        TerminatorConfig.loadConfig();
    }
}
