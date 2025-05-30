package com.awnc.pehkuirandomsize.forge.config;

import com.google.common.collect.Lists;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobType;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class ConfigSpec {
    public static final ForgeConfigSpec commonSpec;
    public static final CommonConfigVals commonConf;

    private static final ArrayList<String> defaultEntityType = new ArrayList<>();


    static class CommonConfigVals {
        public final ForgeConfigSpec.DoubleValue minLimit;
        public final ForgeConfigSpec.DoubleValue maxLimit;

        public final ForgeConfigSpec.DoubleValue sizeMean;

        public final ForgeConfigSpec.DoubleValue sizeStdDev;
        public final ForgeConfigSpec.BooleanValue modifyHealth;

        public final ForgeConfigSpec.BooleanValue increaseDrop;

        public final ForgeConfigSpec.BooleanValue usingGaussianDistribution;

        public final ForgeConfigSpec.ConfigValue<List<? extends String>> blackList;

        public final ForgeConfigSpec.ConfigValue<List<? extends String>> blackListType;

        public final ForgeConfigSpec.BooleanValue onlyEnemyMob;





        public CommonConfigVals(ForgeConfigSpec.Builder builder)
        {
            //factors
            builder.comment("Random Size Settings").push("size");
            builder.comment("Minimum size.Range (0,1)");
            this.minLimit=builder.defineInRange("Minimum size",0.75,0,1);
            builder.comment("Maximum size.Range (1,20)");
            this.maxLimit=builder.defineInRange("Maximum size",2.0,1.0,20.0);
            builder.pop();
            //Is modify health
            builder.comment("Is modified Health").push("health");
            this.modifyHealth=builder.define("Modify Health",true);
            builder.pop();

            builder.comment("Is increased drop by size.Small size will not be affected.").push("increaseDrop");
            this.increaseDrop=builder.define("Increase Drop",false);
            builder.pop();

            builder.comment("If enabled,The size of Mobs will obey a normal distribution").comment("Size that greater than 5.0 or smaller than 0.20 will be set to 1.0").push("normalDistribution");
            this.usingGaussianDistribution=builder.define("Normal Distribution",false);
            builder.pop();

            builder.comment("The mean value of size,using as a parameter of normal distribution").push("meanValue");
            this.sizeMean=builder.defineInRange("Size Mean",1.0,0,100);
            builder.pop();

            builder.comment("The standard deviation of size,using as a parameter of normal distribution").push("standardDeviation");
            this.sizeStdDev=builder.defineInRange("Standard Deviation",0.2,0,100);
            builder.pop();

            //Black List
            builder.comment("Black List Entities that will not be resized.").comment("E.g.[\"minecraft:pig\",\"minecraft:cat\"]").push("blacklist");
            this.blackList=builder.defineList("Black List",new ArrayList<>(),s->s instanceof String);
            builder.pop();
            //Black List Type
            builder.comment("If matched,This Type of Entities will not be resized.").push("blacklistType");
            this.blackListType=builder.defineList("Black List Type",defaultEntityType,s->s instanceof String);
            builder.pop();

            builder.comment("If enabled,Only enemy mobs will be resized").push("onlyenemymob");
            this.onlyEnemyMob=builder.define("Only Enemy Mob",false);
            builder.pop();

        }
    }

    static {
        Pair<CommonConfigVals, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(CommonConfigVals::new);
        commonSpec = specPair.getRight();
        commonConf = specPair.getLeft();
        defaultEntityType.add("dragonmounts");
    }

}
