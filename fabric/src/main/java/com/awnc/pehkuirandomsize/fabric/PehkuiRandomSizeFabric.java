package com.awnc.pehkuirandomsize.fabric;

import com.awnc.pehkuirandomsize.PehkuiRandomSize;
import net.fabricmc.api.ModInitializer;

public class PehkuiRandomSizeFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        PehkuiRandomSize.init();
    }
}