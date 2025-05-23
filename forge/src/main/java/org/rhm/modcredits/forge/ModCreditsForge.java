package org.rhm.modcredits.forge;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import org.rhm.modcredits.LoaderInfo;
import org.rhm.modcredits.ModCreditsBase;
import org.rhm.modcredits.ModCreditsCommon;
import org.rhm.modcredits.ModInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mod("modcredits")
public class ModCreditsForge {
    public ModCreditsForge() {
        ModCreditsCommon.impl = new ModCreditsBase() {
            @Override
            public List<ModInfo> getMods() {
                List<ModInfo> mods = new ArrayList<>();

                ModList.get().forEachModContainer((id, container) -> {
                    if (!(id.equals("minecraft")
                            || id.equals("forge")
                            || id.startsWith("generated_"))) {
                        String[] authors = new String[]{};
                        // yikes
                        //? if <1.16 {
                        /*Optional<Object> modAuthors = container.getModInfo().getModConfig().getOptional("authors");
                         *///?} else
                        Optional<Object> modAuthors = container.getModInfo().getConfig().getConfigElement("authors");
                        if (modAuthors.isPresent())
                            authors = modAuthors.get().toString().split("[,\\s]+"); // should match most things
                        mods.add(new ModInfo(
                                container.getModInfo().getDisplayName(),
                                List.of(authors)
                        ));
                    }
                });

                return mods;
            }

            @Override
            public LoaderInfo getLoaderInfo() {
                return new LoaderInfo(
                        "Forge Loader",
                        "Forge",
                        List.of("Forge Team"));
            }
        };
        ModCreditsCommon.init();
    }
}
