package org.rhm.modcredits;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.Callable;

public class ModCreditsCommon {
    // idk why im using callables for this
    public static Callable<List<ModInfo>> mods;
    public static Callable<LoaderInfo> loaderInfo;
    public static final Logger logger = LoggerFactory.getLogger("Mod Credits Reborn");

    public static void init() {

    }
}
