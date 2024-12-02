package org.rhm.modcredits;

//? if <1.16 {
/*import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
*///?} else {
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//?}

public class ModCreditsCommon {
    public static ModCreditsBase impl;
    //? if <1.16 {
    /*public static final Logger logger = LogManager.getLogger("Mod Credits Reborn");
    *///?} else
    public static final Logger logger = LoggerFactory.getLogger("Mod Credits Reborn");
    public static void init() {

    }
}
