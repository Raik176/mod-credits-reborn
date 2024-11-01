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

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Stream;

//? if <1.19
/*import net.minecraft.network.chat.TextComponent;*/

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

    // doing this so i can debug easier, as i can't use HEAD in init
    @Inject(method =
            //? if <1.18 {
            /*"init",
            *///?} else
            "addCreditsFile",
            at = @At("HEAD")
    )
    //? if <1.18 {
    /*private void init(CallbackInfo ci) {
    *///?} elif <1.19 {
    /*private void addCreditsFile(InputStreamReader inputStreamReader, CallbackInfo ci) {*/
    //?} else
    private void addCreditsFile(Reader reader, CallbackInfo callbackInfo) {
        try {
            LoaderInfo loaderInfo = ModCreditsCommon.loaderInfo.call();
            this.addCreditsLine(SECTION_HEADING, true);
            this.addCreditsLine(ModCreditsReborn$literalComponent(loaderInfo.altName()).withStyle(ChatFormatting.YELLOW), true);
            this.addCreditsLine(SECTION_HEADING, true);
            this.addEmptyLine();
            this.addEmptyLine();


            ModCreditsReborn$addCreditTitle("Loader");
            ModCreditsReborn$addCreditSection(loaderInfo.name(), Stream.concat(
                    loaderInfo.authors().stream(),
                    loaderInfo.contributors().stream()
            ).toList());


            ModCreditsReborn$addCreditTitle("Mods");
            try {
                for (ModInfo modInfo : ModCreditsCommon.mods.call()) {
                    ModCreditsReborn$addCreditSection(modInfo.modName(), Stream.concat(
                            modInfo.authors().stream(),
                            modInfo.contributors().stream()
                    ).toList());
                }
            } catch (Exception e) {
                ModCreditsCommon.logger.error("Could not add mod credits", e);
            }
        } catch (Exception e) {
            ModCreditsCommon.logger.error("Could not add loader credits", e);
        }
    }


    // really unnecessary but eh
    @Unique
    private void ModCreditsReborn$addCreditTitle(String title) {
        this.addCreditsLine(ModCreditsReborn$literalComponent(title).withStyle(ChatFormatting.YELLOW), true);
        this.addEmptyLine();
        this.addEmptyLine();
    }
    @Unique
    private void ModCreditsReborn$addCreditSection(String title, List<String> contributors) {
        this.addCreditsLine(ModCreditsReborn$literalComponent(title).withStyle(ChatFormatting.GRAY), false);

        for (String author : contributors) {
            this.addCreditsLine(ModCreditsReborn$literalComponent(NAME_PREFIX).append(author).withStyle(ChatFormatting.WHITE), false);
        }

        this.addEmptyLine();
        this.addEmptyLine();
    }

    @Unique
    private MutableComponent ModCreditsReborn$literalComponent(String literal) {
        //? if <1.19 {
        /*return new TextComponent(literal);
        *///?} else
        return Component.literal(literal);
    }
}