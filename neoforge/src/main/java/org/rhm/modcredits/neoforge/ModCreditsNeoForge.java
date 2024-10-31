package org.rhm.modcredits.neoforge;

import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import org.rhm.modcredits.LoaderInfo;
import org.rhm.modcredits.ModCreditsCommon;
import org.rhm.modcredits.ModInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mod("modcredits")
public class ModCreditsNeoForge {
	public ModCreditsNeoForge() {
		ModCreditsCommon.loaderInfo = () -> new LoaderInfo(
                "NeoForge Loader",
                "NeoForge",
                List.of("Neoforged Team")
        );
		ModCreditsCommon.mods = () -> {
			List<ModInfo> mods = new ArrayList<>();

			ModList.get().forEachModContainer((id, container) -> {
				if (!(id.equals("minecraft")
						|| id.equals("neoforge")
						|| id.startsWith("generated_")))  {
					String[] authors = new String[] {};
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
