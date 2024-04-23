package com.awnc.pehkuirandomsize.forge.config;

import com.awnc.pehkuirandomsize.config.Config;

public class ConfigLoder {
    public static void load()
    {
        Config.maxLimit=ConfigSpec.commonConf.maxLimit.get();
        Config.minLimit=ConfigSpec.commonConf.minLimit.get();
        Config.meanSize=ConfigSpec.commonConf.sizeMean.get();
        Config.sizeStdDev=ConfigSpec.commonConf.sizeStdDev.get();
        Config.modifyHealth=ConfigSpec.commonConf.modifyHealth.get();
        Config.increaseDrop=ConfigSpec.commonConf.increaseDrop.get();
        Config.usingGaussianDistribution=ConfigSpec.commonConf.usingGaussianDistribution.get();
        Config.blackList=ConfigSpec.commonConf.blackList.get();
        Config.blackListType=ConfigSpec.commonConf.blackListType.get();
        Config.onlyEnemyMob=ConfigSpec.commonConf.onlyEnemyMob.get();
    }
}
