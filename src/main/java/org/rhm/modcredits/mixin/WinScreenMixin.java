package org.rhm.modcredits.mixin;

import org.rhm.modcredits.LoaderInfo;
import org.rhm.modcredits.ModInfo;
import org.rhm.modcredits.ModCreditsCommon;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.WinScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.Reader;

@Mixin(WinScreen.class)
public abstract class WinScreenMixin {
    @Shadow protected abstract void addCreditsLine(Component component, boolean bl);

    @Shadow @Final private static Component SECTION_HEADING;

    @Shadow protected abstract void addEmptyLine();

    @Shadow @Final private static String NAME_PREFIX;

    @Inject(method = "addCreditsFile", at = @At("TAIL"))
    public void addCreditsFile(Reader reader, CallbackInfo ci) {
        try {
            LoaderInfo loaderInfo = ModCreditsCommon.loaderInfo.call();
            this.addCreditsLine(SECTION_HEADING, true);
            this.addCreditsLine(Component.literal(loaderInfo.altName()).withStyle(ChatFormatting.YELLOW), true);
            this.addCreditsLine(SECTION_HEADING, true);
            this.addEmptyLine();
            this.addEmptyLine();

            /*
            this.addCreditsLine(Component.literal(loaderInfo.name()).withStyle(ChatFormatting.YELLOW), true);
            this.addEmptyLine();
            this.addEmptyLine();

            this.addCreditsLine(Component.literal("Author(s)").withStyle(ChatFormatting.GRAY), false);
            for (String author : loaderInfo.authors()) {
                this.addCreditsLine(Component.literal(NAME_PREFIX).append(author).withStyle(ChatFormatting.WHITE), false);
            }
            this.addEmptyLine();
            this.addEmptyLine();

            try {
                for (ModInfo modInfo : ModCreditsCommon.mods.call()) {
                    this.addCreditsLine(Component.literal(modInfo.modName()).withStyle(ChatFormatting.YELLOW), true);
                    this.addEmptyLine();
                    this.addEmptyLine();

                    this.addCreditsLine(Component.literal("Author(s)").withStyle(ChatFormatting.GRAY), false);
                    for (String author : modInfo.authors()) {
                        this.addCreditsLine(Component.literal(NAME_PREFIX).append(author).withStyle(ChatFormatting.WHITE), false);
                    }

                    this.addEmptyLine();
                    this.addEmptyLine();
                }
            } catch (Exception ignored) {

            }
            */
            this.addCreditsLine(Component.literal("Loader").withStyle(ChatFormatting.YELLOW), true);
            this.addEmptyLine();
            this.addEmptyLine();

            this.addCreditsLine(Component.literal(loaderInfo.name()).withStyle(ChatFormatting.GRAY), false);
            for (String author : loaderInfo.authors()) {
                this.addCreditsLine(Component.literal(NAME_PREFIX).append(author).withStyle(ChatFormatting.WHITE), false);
            }
            this.addEmptyLine();
            this.addEmptyLine();

            this.addCreditsLine(Component.literal("Mods").withStyle(ChatFormatting.YELLOW), true);
            this.addEmptyLine();
            this.addEmptyLine();

            try {
                for (ModInfo modInfo : ModCreditsCommon.mods.call()) {
                    this.addCreditsLine(Component.literal(modInfo.modName()).withStyle(ChatFormatting.GRAY), false);

                    for (String author : modInfo.authors()) {
                        this.addCreditsLine(Component.literal(NAME_PREFIX).append(author).withStyle(ChatFormatting.WHITE), false);
                    }

                    this.addEmptyLine();
                    this.addEmptyLine();
                }
            } catch (Exception ignored) {

            }
        } catch (Exception ignored) {

        }
    }
}