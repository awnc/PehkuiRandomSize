package com.awnc.pehkuirandomsize.forge.util;
import com.awnc.pehkuirandomsize.config.Config;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Mob;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import org.checkerframework.checker.units.qual.C;
import virtuoel.pehkui.api.*;

import net.minecraft.world.entity.LivingEntity;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import java.util.Random;
import java.util.UUID;

public class Utils {
    private static final UUID MODIFIER_ID_HEALTH = UUID.fromString("c0bef565-35f6-4dc5-bb4c-3644c382e6ce");


    public static void modifyScale(Mob living,float size)
    {
        ScaleTypes.MOTION.getScaleData(living).setScale(1/size);
        ScaleTypes.ATTACK_SPEED.getScaleData(living).setScale(1/size);
        if(Config.modifyHealth) {
            //ScaleTypes.HEALTH.getScaleData(living).setScaleTickDelay(0);
            //ScaleTypes.HEALTH.getScaleData(living).setTargetScale(size);
            if(size>1) {
                Utils.setMaxHealth(living, living.getMaxHealth() * size, AttributeModifier.Operation.ADDITION);
            }
            else {
                Utils.setMaxHealth(living,-living.getMaxHealth()*(1-size),AttributeModifier.Operation.ADDITION);}
            }
        ScaleTypes.BASE.getScaleData(living).setScaleTickDelay(0);
        ScaleTypes.BASE.getScaleData(living).setTargetScale((float) Math.pow(size,1.0/3.0));
        living.addTag("sized");
    }
    //Increase Max Health.Code refers to Scaling Health
    public static void setModifier(LivingEntity entity, Attribute attribute, UUID uuid, String name, double amount, AttributeModifier.Operation op) {
        AttributeInstance instance = entity.getAttribute(attribute);
        if (instance == null) return;
        AttributeModifier mod = instance.getModifier(uuid);
        if (mod != null) instance.removeModifier(mod);
        instance.addPermanentModifier(new AttributeModifier(uuid, name, amount, op));
    }

    public static void setMaxHealth(LivingEntity entity, double amount, AttributeModifier.Operation op) {
        double oldMax = entity.getMaxHealth();
        setModifier(entity, Attributes.MAX_HEALTH, MODIFIER_ID_HEALTH, "PehkuiRandomSizeModifier", amount, op);
        double newMax = entity.getMaxHealth();

        // Heal entity when increasing max health
        if (newMax > oldMax) {
            float healAmount = (float) (newMax - oldMax);
            entity.heal(healAmount);
        } else if (entity.getHealth() > newMax) {
            entity.setHealth((int) Math.ceil((newMax)));
        }
    }

    public static float getSize()
    {
        var rnd = new Random();
        return rnd.nextFloat((float)Config.minLimit, (float)Config.maxLimit);
    }

    public static double getSizeGaussian()
    {
        var rndN = new Random();
        //double max=Config.maxLimit,min=Config.minLimit;
        double size=rndN.nextGaussian()*Config.sizeStdDev+Config.meanSize;
        if(size>5||size<0.25) return 1.0;else return size;
    }

}
