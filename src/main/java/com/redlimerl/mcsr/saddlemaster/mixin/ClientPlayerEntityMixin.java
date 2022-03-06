package com.redlimerl.mcsr.saddlemaster.mixin;

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.redlimerl.mcsr.saddlemaster.SaddleMaster;
import com.redlimerl.speedrunigt.timer.InGameTimer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin extends PlayerEntity {

    public ClientPlayerEntityMixin(World world, BlockPos blockPos, GameProfile gameProfile) {
        super(world, blockPos, gameProfile);
    }

    @Inject(method = "tickRiding", at = @At("TAIL"))
    public void onRide(CallbackInfo ci) {
        InGameTimer timer = InGameTimer.getInstance();
        if (timer.getCategory() == SaddleMaster.SADDLE_MASTER_CATEGORY && timer.isPlaying()) {
            if (timer.getMoreData(0) == 0 && this.getVehicle() instanceof HorseBaseEntity) {
                timer.updateMoreData(0, 1);
            }
            if (timer.getMoreData(1) == 0 && this.getVehicle() instanceof StriderEntity) {
                ArrayList<Item> items = Lists.newArrayList(
                        this.getEquippedStack(EquipmentSlot.MAINHAND) != null ? this.getEquippedStack(EquipmentSlot.MAINHAND).getItem() : Items.AIR,
                        this.getEquippedStack(EquipmentSlot.OFFHAND) != null ? this.getEquippedStack(EquipmentSlot.OFFHAND).getItem() : Items.AIR);
                if (items.contains(Items.WARPED_FUNGUS_ON_A_STICK)) timer.updateMoreData(1, 1);
            }
            if (timer.getMoreData(2) == 0 && this.getVehicle() instanceof PigEntity) {
                ArrayList<Item> items = Lists.newArrayList(
                        this.getEquippedStack(EquipmentSlot.MAINHAND) != null ? this.getEquippedStack(EquipmentSlot.MAINHAND).getItem() : Items.AIR,
                        this.getEquippedStack(EquipmentSlot.OFFHAND) != null ? this.getEquippedStack(EquipmentSlot.OFFHAND).getItem() : Items.AIR);
                if (items.contains(Items.CARROT_ON_A_STICK)) timer.updateMoreData(2, 1);
            }

            if (timer.getMoreData(0) + timer.getMoreData(1) + timer.getMoreData(2) == 3) {
                InGameTimer.complete();
            }
        }
    }
}
