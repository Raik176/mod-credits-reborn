package org.rhm.modcredits;

//? if <1.16 {
/*import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
*///?} else {
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//?}

import java.util.List;
import java.util.concurrent.Callable;

public class ModCreditsCommon {
    // idk why im using callables for this
    public static Callable<List<ModInfo>> mods;
    public static Callable<LoaderInfo> loaderInfo;
    //? if <1.16 {
    /*public static final Logger logger = LogManager.getLogger("Mod Credits Reborn");
    *///?} else
    public static final Logger logger = LoggerFactory.getLogger("Mod Credits Reborn");
    public static void init() {

    }
}
