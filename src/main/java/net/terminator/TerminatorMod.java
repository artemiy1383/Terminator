package net.terminator;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TerminatorMod implements ModInitializer {
	public static final String MOD_ID = "modid";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		LOGGER.info("Terrminator Client Was Successfully Initialized !");
	}
}