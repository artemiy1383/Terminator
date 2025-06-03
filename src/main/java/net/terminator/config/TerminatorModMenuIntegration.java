package net.terminator.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.terminator.account.AccountManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TerminatorModMenuIntegration implements ModMenuApi {
    private static final Logger LOGGER = LoggerFactory.getLogger("Terminator");
    private static boolean clothConfigCompatible = true;

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            if (clothConfigCompatible) {
                try {
                    ConfigBuilder builder = ConfigBuilder.create()
                            .setParentScreen(parent)
                            .setTitle(Text.literal("Terminator Settings"))
                            .setSavingRunnable(() -> {});

                    ConfigEntryBuilder entryBuilder = ConfigEntryBuilder.create();

                    // Account Tab
                    ConfigCategory accountCategory = builder.getOrCreateCategory(Text.literal("Account"));
                    accountCategory.addEntry(entryBuilder.startTextDescription(
                            Text.literal("Username: " + AccountManager.getCurrentAccount().getUsername())
                    ).build());
                    accountCategory.addEntry(entryBuilder.startTextDescription(
                            Text.literal("Rank: " + AccountManager.getCurrentAccount().getRank())
                    ).build());
                    accountCategory.addEntry(entryBuilder.startTextDescription(
                            Text.literal("Click [here] to refresh in-game.")
                    ).build());

                    builder.getOrCreateCategory(Text.literal("Forum"));
                    builder.getOrCreateCategory(Text.literal("T-Addons"));
                    builder.getOrCreateCategory(Text.literal("Schematics"));
                    builder.getOrCreateCategory(Text.literal("Feedback"));
                    builder.getOrCreateCategory(Text.literal("Settings"));

                    return builder.build();
                } catch (NoSuchFieldError e) {
                    if (e.getMessage() != null && e.getMessage().contains("field_44669")) {
                        LOGGER.error("Cloth Config compatibility issue with Minecraft 1.21.4 detected. Using fallback config screen.", e);
                        clothConfigCompatible = false;
                        return createFallbackScreen(parent);
                    } else {
                        throw e;
                    }
                } catch (Exception e) {
                    LOGGER.error("Failed to build config screen, using fallback", e);
                    clothConfigCompatible = false;
                    return createFallbackScreen(parent);
                }
            } else {
                return createFallbackScreen(parent);
            }
        };
    }

    private Screen createFallbackScreen(Screen parent) {
        return new FallbackConfigScreen(parent);
    }

    private static class FallbackConfigScreen extends Screen {
        private final Screen parent;

        public FallbackConfigScreen(Screen parent) {
            super(Text.literal("Terminator Settings"));
            this.parent = parent;
        }

        @Override
        public void init() {
            super.init();

            int buttonWidth = 200;
            int y = this.height / 2 - 80;

            String[] categories = {
                "Account", "Forum", "T-Addons", "Schematics", "Feedback", "Settings"
            };

            for (String name : categories) {
                this.addDrawableChild(ButtonWidget.builder(
                        Text.literal(name), button -> {
                            // Заглушка — ничего не делает
                        })
                        .dimensions(this.width / 2 - buttonWidth / 2, y, buttonWidth, 20)
                        .build());
                y += 25;
            }

            y += 15;
            this.addDrawableChild(ButtonWidget.builder(
                    Text.literal("Back"), button -> this.client.setScreen(parent))
                    .dimensions(this.width / 2 - buttonWidth / 2, y, buttonWidth, 20)
                    .build());
        }

        @Override
        public void render(net.minecraft.client.gui.DrawContext context, int mouseX, int mouseY, float delta) {
            this.renderBackground(context, mouseX, mouseY, delta);

            context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 20, 0xFFFFFF);
            context.drawCenteredTextWithShadow(this.textRenderer,
                    Text.literal("Cloth Config compatibility issue detected."),
                    this.width / 2, 40, 0xFFAA00);
            context.drawCenteredTextWithShadow(this.textRenderer,
                    Text.literal("Using simple fallback screen."),
                    this.width / 2, 52, 0xFFAA00);

            super.render(context, mouseX, mouseY, delta);
        }

        @Override
        public boolean shouldCloseOnEsc() {
            return true;
        }

        @Override
        public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
            if (keyCode == 256) {
                this.client.setScreen(parent);
                return true;
            }
            return super.keyPressed(keyCode, scanCode, modifiers);
        }
    }
}
