package net.terminator;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class TextButtonWidget extends ButtonWidget {
    private final Identifier normalTexture;
    private final Identifier hoverTexture;
    private final int textColor;
    private final int hoverColor;

    public TextButtonWidget(int x, int y, int width, int height,
                            Text message,
                            PressAction onPress,
                            int textColor, int hoverColor,
                            Identifier normalTexture, Identifier hoverTexture) {
        super(x, y, width, height, message, onPress, ButtonWidget.DEFAULT_NARRATION_SUPPLIER);
        this.textColor = textColor;
        this.hoverColor = hoverColor;
        this.normalTexture = normalTexture;
        this.hoverTexture = hoverTexture;
    }

    /**
     * В Yarn 1.21.4 метод render(...) в ClickableWidget объявлен final,
     * поэтому переопределяем renderWidget(...) (а не render(...)).
     */
    @Override
    public void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        Identifier tex = isHovered() ? hoverTexture : normalTexture;

        // Первый аргумент: Function<Identifier, RenderLayer>
        // Лямбда id -> RenderLayer.getGui() подходит под Function<Identifier,RenderLayer>
        context.drawTexture(
            id -> RenderLayer.getGui(), // :contentReference[oaicite:24]{index=24}
            tex,
            this.getX(), this.getY(),
            0f, 0f,
            this.width, this.height,
            this.width, this.height
        );

        context.drawTextWithShadow(
            MinecraftClient.getInstance().textRenderer,
            getMessage().asOrderedText(),
            this.getX() + 10,
            this.getY() + (this.height - 9) / 2,
            isHovered() ? hoverColor : textColor
        );
    }
}
