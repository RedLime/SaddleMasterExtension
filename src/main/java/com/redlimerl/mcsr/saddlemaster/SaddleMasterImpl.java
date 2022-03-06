package com.redlimerl.mcsr.saddlemaster;

import com.redlimerl.speedrunigt.api.SpeedRunIGTApi;
import com.redlimerl.speedrunigt.timer.running.RunCategory;

public class SaddleMasterImpl implements SpeedRunIGTApi {
    @Override
    public RunCategory registerCategory() {
        return SaddleMaster.SADDLE_MASTER_CATEGORY;
    }
}
