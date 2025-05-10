package net.terminator.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
@Mixin(TitleScreen.class)
public class TerminatorMixin extends Screen {
    private static final Logger LOGGER = LoggerFactory.getLogger(TerminatorMixin.class);

    protected TerminatorMixin(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("RETURN"))
    private void addModsButton(CallbackInfo ci) {
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("Termiantor"), (button) -> {
            this.client.setScreen(new SelectWorldScreen(this));
        }).dimensions(this.width / 2 - 200 + 252, this.height / 4 + 48, 50, 20).build());

        LOGGER.info("Mixins Were Successfully Initialized !");
    }
}
