package org.rhm.modcredits;

import java.util.ArrayList;
import java.util.List;

public record ModInfo(String modName, List<String> authors, List<String> contributors) {
    public ModInfo(String modName, List<String> authors) {
        this(modName, authors, new ArrayList<>());
    }
}
