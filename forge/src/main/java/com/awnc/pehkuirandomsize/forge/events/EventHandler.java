package com.awnc.pehkuirandomsize.forge.events;

import com.awnc.pehkuirandomsize.config.Config;

import com.awnc.pehkuirandomsize.forge.util.Utils;


import net.minecraft.resources.ResourceLocation;


import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.Random;


public class EventHandler {

    //public float size = Utils.getRandomNumber();
    @SubscribeEvent
    public void onSpawnFinalized(MobSpawnEvent.FinalizeSpawn event) {
        Mob mob = event.getEntity();
       float size=1;
        ResourceLocation id = Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(mob.getType()));
        boolean canResize=true;
        if(!Config.blackList.contains(id.toString()))
        {
            for(String type:Config.blackListType)
            {
                if(id.toString().contains(type))
                {
                    canResize=false;
                    break;
                }
            }
            if(canResize)
            {
                if(Config.usingGaussianDistribution) size = (float) Utils.getSizeNormal();
                else size=Utils.getSize();
                if(Config.onlyEnemyMob)
                {
                    if (mob instanceof Enemy) Utils.modifyScale(mob, size);
                }
                else Utils.modifyScale(mob,size);
            }
        }
    }
    @SubscribeEvent
    public void onMobDropItem(LivingDropsEvent event)
    {
        //int dropAmount = event.getLootingLevel();
        if(Config.increaseDrop&&Utils.getSize()>1)
        {
            var itemEntities = event.getDrops();
            for(ItemEntity itemEntity:itemEntities)
            {
                ItemStack itemStack = itemEntity.getItem();
                //itemStack.copyWithCount(5);//(int) (rnd.nextFloat((float) Config.minLimit, (float) Config.maxLimit))+1);
                Level world = event.getEntity().level();
                var epos= event.getEntity().blockPosition();
                for(int i = 1; i<=Utils.getSize(); i++)
                {
                    world.addFreshEntity(new ItemEntity(world, epos.getX(), epos.getY()+1, epos.getZ(), itemStack));
                }
            }
        }
    }
    public EventHandler()
    {
    }
}
