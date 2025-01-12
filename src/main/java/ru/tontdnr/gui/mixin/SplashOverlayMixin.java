package ru.tontdnr.gui.mixin;

import net.minecraft.client.gui.screen.SplashOverlay;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = SplashOverlay.class, priority = 3000)
public abstract class SplashOverlayMixin {
//    @Shadow
//    @Final
//    static final Identifier LOGO = new Identifier("tontdnr-gui:textures/gui/title/tontdnr_title.png");
}