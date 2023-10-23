package com.awnc.pehkuirandomsize.forge;

import com.awnc.pehkuirandomsize.PehkuiRandomSize;
import com.awnc.pehkuirandomsize.forge.config.ConfigLoder;
import com.awnc.pehkuirandomsize.forge.config.ConfigSpec;
import com.awnc.pehkuirandomsize.forge.events.EventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(PehkuiRandomSize.MOD_ID)
public class PehkuiRandomSizeForge {
    public static final Logger LOG = LogManager.getLogger("PehkuiRandomSize");
    public PehkuiRandomSizeForge() {
        PehkuiRandomSize.init();
        EventHandler handler = new EventHandler();

        MinecraftForge.EVENT_BUS.register(handler);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigSpec.commonSpec,"pehkuirandomsize.toml");
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(PehkuiRandomSizeForge::conf);
    }
    static void conf(ModConfigEvent event)
    {
        ConfigLoder.load();
    }
}