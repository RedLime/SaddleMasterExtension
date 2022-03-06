package com.redlimerl.mcsr.saddlemaster;

import com.redlimerl.speedrunigt.timer.running.RunCategory;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class SaddleMaster implements ClientModInitializer {

    public static final RunCategory SADDLE_MASTER_CATEGORY = new RunCategory("saddle_master", "mc_juice#Saddle_Master", "Saddle Master");

    @Override
    public void onInitializeClient() {}
}
