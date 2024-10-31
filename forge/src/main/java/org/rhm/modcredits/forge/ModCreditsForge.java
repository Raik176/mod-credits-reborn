package org.rhm.modcredits.forge;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import org.rhm.modcredits.LoaderInfo;
import org.rhm.modcredits.ModCreditsCommon;
import org.rhm.modcredits.ModInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mod("modcredits")
public class ModCreditsForge {
    public ModCreditsForge() {
        ModCreditsCommon.loaderInfo = () -> new LoaderInfo(
                "Forge Loader",
                "Forge",
                List.of("Forge Team")
        );
        ModCreditsCommon.mods = () -> {
            List<ModInfo> mods = new ArrayList<>();

            ModList.get().forEachModContainer((id, container) -> {
                if (!(id.equals("minecraft")
                        || id.equals("forge")
                        || id.startsWith("generated_"))) {
                    String[] authors = new String[]{};
                    // yikes
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
        };
        ModCreditsCommon.init();
    }
}
