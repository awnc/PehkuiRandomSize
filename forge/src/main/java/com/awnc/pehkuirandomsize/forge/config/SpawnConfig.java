/*package com.awnc.pehkuirandomsize.forge.config;


import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.ArrayList;
import java.util.List;

public class SpawnConfig {
    private final ForgeConfigSpec.BooleanValue allowResize;
    private final ForgeConfigSpec.BooleanValue useListedSize;
    private final ForgeConfigSpec.ConfigValue<List<? extends Float>> specificSizes;
    private final ForgeConfigSpec.DoubleValue minHealth;
    private final ForgeConfigSpec.DoubleValue maxHealth;
    private final ForgeConfigSpec.IntValue minLoot;
    private final ForgeConfigSpec.IntValue maxLoot;
    private final ForgeConfigSpec.IntValue minXp;
    private final ForgeConfigSpec.IntValue maxXp;

    public SpawnConfig(ForgeConfigSpec.Builder builder)
    {
        builder.comment("Should the mob can be resized？");
        this.allowResize=builder.define("allowResize",true);

        builder.comment("Should the mob use listed size?");
        this.useListedSize=builder.define("useListedSize",false);

        builder.comment("The list of specific sizes of the mob.");
        this.specificSizes=builder.defineList("specificSizes",new ArrayList<>(),s->s instanceof Float);

        builder.comment("The minimum health of the mob.");
        this.minHealth= builder.defineInRange("minHealth",0.5,0,1024);

        builder.comment("The maximum health of the mob.");
        this.maxHealth= builder.defineInRange("maxHealth",1024.0,0,1024);

        builder.comment("The minimum loot copy number.");
        this.minLoot=builder.defineInRange("minLoot",1,1,100);

        builder.comment("The maximum loot copy number.");
        this.maxLoot=builder.defineInRange("maxLoot",100,1,100);

        builder.comment("The minimum mob dropped xp.");
        this.minXp=builder.defineInRange("minXp",1,1,2147483647);

        builder.comment("The maximum mob dropped xp.");
        this.maxXp=builder.defineInRange("maxXp",2147483647,1,2147483647);

        builder.pop();
        builder.pop();

    }


}*/
