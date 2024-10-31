package org.rhm.modcredits.mixin;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.WinScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.rhm.modcredits.LoaderInfo;
import org.rhm.modcredits.ModCreditsCommon;
import org.rhm.modcredits.ModInfo;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

//? if <1.19
import net.minecraft.network.chat.TextComponent;

@Mixin(WinScreen.class)
public abstract class WinScreenMixin {
    @Shadow
    @Final
    private static Component SECTION_HEADING;
    @Shadow
    @Final
    private static String NAME_PREFIX;

    @Shadow
    protected abstract void addCreditsLine(Component component, boolean bl);

    @Shadow
    protected abstract void addEmptyLine();

    @Inject(method = "init", at = @At("TAIL"))
    public void addCreditsFile(CallbackInfo ci) {
        try {
            LoaderInfo loaderInfo = ModCreditsCommon.loaderInfo.call();
            this.addCreditsLine(SECTION_HEADING, true);
            this.addCreditsLine(ModCreditsReborn$literalComponent(loaderInfo.altName()).withStyle(ChatFormatting.YELLOW), true);
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
            this.addCreditsLine(ModCreditsReborn$literalComponent("Loader").withStyle(ChatFormatting.YELLOW), true);
            this.addEmptyLine();
            this.addEmptyLine();

            this.addCreditsLine(ModCreditsReborn$literalComponent(loaderInfo.name()).withStyle(ChatFormatting.GRAY), false);
            for (String author : loaderInfo.authors()) {
                this.addCreditsLine(ModCreditsReborn$literalComponent(NAME_PREFIX).append(author).withStyle(ChatFormatting.WHITE), false);
            }
            this.addEmptyLine();
            this.addEmptyLine();

            this.addCreditsLine(ModCreditsReborn$literalComponent("Mods").withStyle(ChatFormatting.YELLOW), true);
            this.addEmptyLine();
            this.addEmptyLine();

            try {
                for (ModInfo modInfo : ModCreditsCommon.mods.call()) {
                    this.addCreditsLine(ModCreditsReborn$literalComponent(modInfo.modName()).withStyle(ChatFormatting.GRAY), false);

                    for (String author : modInfo.authors()) {
                        this.addCreditsLine(ModCreditsReborn$literalComponent(NAME_PREFIX).append(author).withStyle(ChatFormatting.WHITE), false);
                    }

                    this.addEmptyLine();
                    this.addEmptyLine();
                }
            } catch (Exception ignored) {

            }
        } catch (Exception ignored) {

        }
    }

    @Unique
    private MutableComponent ModCreditsReborn$literalComponent(String literal) {
        //? if <1.19 {
        return new TextComponent(literal);
        //?} else
        /*return Component.literal(literal);*/
    }
}