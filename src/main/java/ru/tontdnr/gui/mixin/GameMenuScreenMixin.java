package ru.tontdnr.gui.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.MessageScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.tontdnr.gui.LogoTontDNRDrawer;
import ru.tontdnr.gui.MainMenu;

@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin {
    @Inject(method = "disconnect", at = @At("HEAD"), cancellable = true)
    private void onDisconnect(CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        boolean isSingleplayer = client.isInSingleplayer();
        assert client.world != null;
        client.world.disconnect();
        if (isSingleplayer) {
            client.disconnect(new MessageScreen(Text.translatable("menu.savingLevel")));
        } else {
            client.disconnect();
        }
        MainMenu mainMenu = new MainMenu(new LogoTontDNRDrawer(false));
        client.setScreen(mainMenu);
        ci.cancel();
    }
}
