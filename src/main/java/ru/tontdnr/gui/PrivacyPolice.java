package ru.tontdnr.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ConfirmLinkScreen;
import net.minecraft.client.gui.screen.CreditsScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.SimpleOptionsScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.*;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Util;

@Environment(EnvType.CLIENT)
public class PrivacyPolice extends Screen {
    private static final int SPACING = 8;
    private static final int BUTTON_WIDTH = 210;
    private static final Text TITLE = Text.translatable("privacy_policy.screen.title");
    private static final Text WEB_SITE = Text.translatable("privacy_policy.button.web_site");
    private static final Text TELEGRAM_LINK = Text.translatable("privacy_policy.button.telegram_link");
    private static final Text WEB_MAP = Text.translatable("privacy_policy.button.web_map");
    private final Screen parent;
    private final ThreePartsLayoutWidget layout = new ThreePartsLayoutWidget(this);

    public PrivacyPolice(Screen parent) {
        super(TITLE);
        this.parent = parent;
    }

    protected void init() {
        this.layout.addHeader(new TextWidget(this.getTitle(), this.textRenderer));
        GridWidget gridWidget = ((GridWidget)this.layout.addBody(new GridWidget())).setSpacing(8);
        gridWidget.getMainPositioner().alignHorizontalCenter();
        GridWidget.Adder adder = gridWidget.createAdder(1);
        adder.add(ButtonWidget.builder(WEB_SITE, ConfirmLinkScreen.opening("https://tontdnr.ru", this, true)).width(210).build());
        adder.add(ButtonWidget.builder(TELEGRAM_LINK, ConfirmLinkScreen.opening("https://t.me/+YtGhg0cnLtc5NWI6", this, true)).width(210).build());
        adder.add(ButtonWidget.builder(WEB_MAP, ConfirmLinkScreen.opening("https://map.tontdnr.ru", this, true)).width(210).build());
        this.layout.addFooter(ButtonWidget.builder(ScreenTexts.DONE, (button) -> this.close()).build());
        this.layout.refreshPositions();
        this.layout.forEachChild(this::addDrawableChild);
    }

    protected void initTabNavigation() {
        this.layout.refreshPositions();
    }

    public void close() {
        this.client.setScreen(this.parent);
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
    }
}
