package com.awnc.pehkuirandomsize.forge.events;

import com.awnc.pehkuirandomsize.config.Config;

import com.awnc.pehkuirandomsize.forge.util.Utils;


import net.minecraft.resources.ResourceLocation;


import net.minecraft.world.entity.Mob;

import net.minecraft.world.entity.monster.Enemy;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;


public class EventHandler {
    @SubscribeEvent
    public void onSpawnFinalized(MobSpawnEvent.FinalizeSpawn event) {
        Mob mob = event.getEntity();
        ResourceLocation id = Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(mob.getType()));
        if(!Config.blackList.contains(id.toString()))
        {
            if(Config.onlyEnemyMob)
            {
                if (mob instanceof Enemy) Utils.modifyScale(mob, (float)Config.minLimit,(float)Config.maxLimit);
            }
            else Utils.modifyScale(mob, (float)Config.minLimit,(float)Config.maxLimit);
        }
    }
    public EventHandler()
    {
    }

}
