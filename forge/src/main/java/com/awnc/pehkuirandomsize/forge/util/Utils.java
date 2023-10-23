package com.awnc.pehkuirandomsize.forge.util;
import com.awnc.pehkuirandomsize.config.Config;

import net.minecraft.world.entity.Mob;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;

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


    public static void modifyScale(Mob living,float min,float max)
    {

        var random = new Random();
        float size = random.nextFloat(min,max);
        ScaleTypes.MOTION.getScaleData(living).setScale(1/size);
        ScaleTypes.ATTACK_SPEED.getScaleData(living).setScale(1/size);
        if(Config.modifyHealth) {
            Utils.setMaxHealth(living, living.getMaxHealth()*size,AttributeModifier.Operation.ADDITION );
            }
        ScaleTypes.BASE.getScaleData(living).setScaleTickDelay(0);
        ScaleTypes.BASE.getScaleData(living).setTargetScale(size);
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
            entity.setHealth((float) newMax);
        }


    }


}
