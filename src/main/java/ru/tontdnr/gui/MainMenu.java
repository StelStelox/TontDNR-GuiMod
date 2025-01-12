package ru.tontdnr.gui;

import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.AccessibilityOptionsScreen;
import net.minecraft.client.gui.screen.option.LanguageOptionsScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.PressableTextWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class MainMenu extends Screen {
    private static final Text COPYRIGHT = Text.literal("By TontDNR Team");
    private ServerInfo selectedEntry;
    private final LogoTontDNRDrawer logoDrawer;

    public MainMenu(LogoTontDNRDrawer logoDrawer) {
        super(Text.translatable("narrator.screen.title"));
        this.logoDrawer = logoDrawer;
    }

    @Override
    protected void init() {
        int i = this.textRenderer.getWidth(COPYRIGHT);
        int j = this.width - i - 2;
        int k = 24;
        int l = this.height / 4 + 48;
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.singleplayer"), (button) -> this.client.setScreen(new SelectWorldScreen(this))).dimensions(this.width / 2 - 100, l + k, 200, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("main_menu.button.play"), (button) -> {
            this.selectedEntry = new ServerInfo("TontDNR", "play.tontdnr.ru", false);
            connect(this.selectedEntry);
        }).dimensions(this.width / 2 - 100, l, 200, 20).build());
        this.addDrawableChild(new TexturedButtonWidget(this.width / 2 - 124, l + 72 + 12, 20, 20, 0, 106, 20, ButtonWidget.WIDGETS_TEXTURE, 256, 256, (button) -> this.client.setScreen(new LanguageOptionsScreen(this, this.client.options, this.client.getLanguageManager())), Text.translatable("narrator.button.language")));
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.options"), (button) -> this.client.setScreen(new OptionsScreen(this, this.client.options))).dimensions(this.width / 2 - 100, l + 72 + 12, 98, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.quit"), (button) -> this.client.scheduleStop()).dimensions(this.width / 2 + 2, l + 72 + 12, 98, 20).build());
        this.addDrawableChild(new TexturedButtonWidget(this.width / 2 + 104, l + 72 + 12, 20, 20, 0, 0, 20, ButtonWidget.ACCESSIBILITY_TEXTURE, 32, 64, (button) -> this.client.setScreen(new AccessibilityOptionsScreen(this, this.client.options)), Text.translatable("narrator.button.accessibility")));
        this.addDrawableChild(new PressableTextWidget(j, this.height - 10, i, 10, COPYRIGHT, (button) -> this.client.setScreen(new PrivacyPolice(this)), this.textRenderer));
    }

    private void connect(ServerInfo entry) {
        ConnectScreen.connect(this, this.client, ServerAddress.parse(entry.address), entry, false);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        Identifier backgroundTexture = new Identifier("tontdnr-gui", "textures/gui/background.png");
        context.drawTexture(backgroundTexture, 0, 0, 0, 0, this.width, this.height, this.width, this.height);
        String  string = "Minecraft " + SharedConstants.getGameVersion().getName();
        if (MinecraftClient.getModStatus().isModded()) {
            string = string + I18n.translate("menu.modded");
        }
        context.drawTextWithShadow(this.textRenderer, string, 2, this.height - 10, 16777215);
        super.render(context, mouseX, mouseY, delta);
        this.logoDrawer.draw(context, this.width, MathHelper.clamp(1000.0F - 1.0F, 0.0F, 1.0F));
    }
}