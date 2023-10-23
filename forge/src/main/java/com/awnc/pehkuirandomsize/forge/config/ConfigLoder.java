package com.awnc.pehkuirandomsize.forge.config;

import com.awnc.pehkuirandomsize.config.Config;

public class ConfigLoder {
    public static void load()
    {
        Config.maxLimit=ConfigSpec.commonConf.maxLimit.get();
        Config.minLimit=ConfigSpec.commonConf.minLimit.get();
        Config.modifyHealth=ConfigSpec.commonConf.modifyHealth.get();
        Config.blackList=ConfigSpec.commonConf.blackList.get();
        Config.onlyEnemyMob=ConfigSpec.commonConf.onlyEnemyMob.get();
    }
}
