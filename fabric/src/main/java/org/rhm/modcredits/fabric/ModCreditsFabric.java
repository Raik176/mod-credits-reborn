package org.rhm.modcredits.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.Person;
import org.rhm.modcredits.LoaderInfo;
import org.rhm.modcredits.ModCreditsBase;
import org.rhm.modcredits.ModCreditsCommon;
import org.rhm.modcredits.ModInfo;
import java.util.ArrayList;
import java.util.List;

public class ModCreditsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ModCreditsCommon.impl = new ModCreditsBase() {
            @Override
            public List<ModInfo> getMods() {
                List<ModInfo> mods = new ArrayList<>();

                for (ModContainer mod : FabricLoader.getInstance().getAllMods()) {
                    if (mod.getMetadata().getName().equals("Generated Mod (Please Ignore)")
                            || mod.getMetadata().getId().equals("java")
                            || mod.getMetadata().getId().equals("minecraft")
                            || mod.getMetadata().getId().equals("fabricloader")) continue; // Probably not a great way
                    mods.add(new ModInfo(
                            mod.getMetadata().getName(),
                            mod.getMetadata().getAuthors().stream().map(Person::getName).toList(),
                            mod.getMetadata().getContributors().stream().map(p -> p.getName() + " (Contributor)").toList()
                    ));
                }

                return mods;
            }

            @Override
            public LoaderInfo getLoaderInfo() {
                ModContainer loader = FabricLoader.getInstance().getModContainer("fabricloader").get();
                return new LoaderInfo(
                        loader.getMetadata().getName(),
                        "FabricMC",
                        loader.getMetadata().getAuthors().stream().map(Person::getName).toList(),
                        loader.getMetadata().getContributors().stream().map(p -> p.getName() + " (Contributor)").toList()
                );
            }
        };
        ModCreditsCommon.init();
    }
}
