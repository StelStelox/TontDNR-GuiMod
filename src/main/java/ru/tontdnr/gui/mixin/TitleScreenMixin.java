package ru.tontdnr.gui.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.tontdnr.gui.LogoTontDNRDrawer;
import ru.tontdnr.gui.MainMenu;

@Mixin(MinecraftClient.class)
public class TitleScreenMixin {
    @Inject(method = "setScreen", at = @At(value = "HEAD"), cancellable = true)
    private void onSetScreen(Screen screen, CallbackInfo ci) {
        if (screen instanceof TitleScreen) {
            MinecraftClient minecraft = (MinecraftClient) (Object) this;
            screen = new MainMenu(new LogoTontDNRDrawer(false));
            minecraft.setScreen(screen);
            ci.cancel();
        }
    }
}
