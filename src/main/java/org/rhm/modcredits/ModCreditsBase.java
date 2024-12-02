package org.rhm.modcredits;

import java.util.List;

public interface ModCreditsBase {
    List<ModInfo> getMods();
    LoaderInfo getLoaderInfo();
}
