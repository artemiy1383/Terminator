package net.terminator;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;   // :contentReference[oaicite:28]{index=28}
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.pack.PackScreen;             // :contentReference[oaicite:29]{index=29}
import net.minecraft.resource.ResourcePackManager;                // 
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import net.fabricmc.loader.api.FabricLoader;                      // :contentReference[oaicite:32]{index=32}

import java.nio.file.Path;                                        // 
import java.util.function.Consumer;                               // :contentReference[oaicite:34]{index=34}

public class TerminatorMenuScreen extends Screen {
    public TerminatorMenuScreen() {
        super(Text.literal("Terminator Menu"));
    }

    @Override
    protected void init() {
        int x = 20;
        int y = 40;
        int spacing = 30;
        int width = 200;
        int height = 20;

        // Загрузка текстур через Identifier.of(...) (вместо приватного конструктора)
        Identifier normalTexture = Identifier.of("terminator", "textures/gui/button_normal.png");  // 
        Identifier hoverTexture  = Identifier.of("terminator", "textures/gui/button_hover.png");   // 

        // 1) Singleplayer / Одиночная игра
        this.addDrawableChild(new TextButtonWidget(
            x, y, width, height,
            Text.translatable("menu.singleplayer"),    // Локализуется из lang/ru_ru.json 
            button -> client.setScreen(new SelectWorldScreen(this)),
            0xFF000000, 0xFFFFFFFF,
            normalTexture, hoverTexture
        ));

        // 2) Multiplayer / Сетевая игра
        this.addDrawableChild(new TextButtonWidget(
            x, y + spacing, width, height,
            Text.translatable("menu.multiplayer"),
            button -> client.setScreen(new MultiplayerScreen(this)),
            0xFF000000, 0xFFFFFFFF,
            normalTexture, hoverTexture
        ));

        // 3) Options… / Настройки…
        this.addDrawableChild(new TextButtonWidget(
            x, y + spacing * 2, width, height,
            Text.translatable("menu.options"),
            button -> client.setScreen(new OptionsScreen(this, client.options)),
            0xFF000000, 0xFFFFFFFF,
            normalTexture, hoverTexture
        ));

        // 4) Mods / Моды — vanilla 1.21.4 не содержит ModsScreen;
        // Либо уберите блок, либо замените на ModMenuScreen (если подключён ModMenu).
        /*
        this.addDrawableChild(new TextButtonWidget(
            x, y + spacing * 3, width, height,
            Text.translatable("menu.mods"),
            button -> client.setScreen(new ModMenuScreen()), // пример для ModMenu 
            0xFF000000, 0xFFFFFFFF,
            normalTexture, hoverTexture
        ));
        */

        // 5) Resource Packs / Наборы ресурсов
        this.addDrawableChild(new TextButtonWidget(
            x, y + spacing * 3, width, height,
            Text.translatable("resourcePack.title"),
            button -> {
                // 5.1) ResourcePackManager из клиента
                ResourcePackManager rpm = client.getResourcePackManager(); // 

                // 5.2) Путь к папке resourcepacks через FabricLoader
                Path packDir = FabricLoader.getInstance()
                                   .getGameDir()
                                   .resolve("resourcepacks");          // :contentReference[oaicite:40]{index=40}

                // 5.3) Пустой Consumer<ResourcePackManager>, если не нужно сохранять изменения
                Consumer<ResourcePackManager> emptyConsumer = mgr -> {};     // :contentReference[oaicite:41]{index=41}

                // 5.4) Вызываем правильный конструктор PackScreen(ResourcePackManager, Consumer<ResourcePackManager>, Path, Text)
                client.setScreen(new PackScreen(
                    rpm,                                   // ResourcePackManager 
                    emptyConsumer,                         // Consumer<ResourcePackManager> :contentReference[oaicite:43]{index=43}
                    packDir,                               // Path до resourcepacks :contentReference[oaicite:44]{index=44}
                    Text.translatable("resourcePack.title")// Заголовок (локализуется) 
                ));
            },
            0xFF000000, 0xFFFFFFFF,
            normalTexture, hoverTexture
        ));

        // 6) Quit Game / Выйти из игры
        this.addDrawableChild(new TextButtonWidget(
            x, y + spacing * 4, width, height,
            Text.translatable("menu.quit"),
            button -> client.scheduleStop(),
            0xFF000000, 0xFFFFFFFF,
            normalTexture, hoverTexture
        ));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // renderBackground в 1.21.4 требует 4 аргумента
        renderBackground(context, mouseX, mouseY, delta); // 
        super.render(context, mouseX, mouseY, delta);
    }
}
