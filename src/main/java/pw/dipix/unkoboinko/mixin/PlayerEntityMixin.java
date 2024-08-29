package pw.dipix.unkoboinko.mixin;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pw.dipix.unkoboinko.UnkoBoinko;

@Mixin(ServerPlayerEntity.class)
public abstract class PlayerEntityMixin {
    @Shadow
    public abstract void playerTick();

    @Inject(method = "dropSelectedItem(Z)Z", at = @At("HEAD"), cancellable = true)
    public void preventThrow(boolean entireStack, CallbackInfoReturnable<Boolean> cir) {
        PlayerInventory playerInventory = ((ServerPlayerEntity) (Object) this).getInventory();
//        UnkoBoinko.INSTANCE.getLOGGER().info("Dimension: {}", ((ServerPlayerEntity) (Object) this).getEntityWorld().getDimensionEntry().toString());
        if (((ServerPlayerEntity) (Object) this).getEntityWorld().getDimensionEntry().isIn(UnkoBoinko.INSTANCE.getRestrictedThrowingDimensions())
                && playerInventory.getStack(playerInventory.selectedSlot).isIn(UnkoBoinko.INSTANCE.getRestrictedThrowing())) {
            // client is funni and will pretend item is thrown even though it's not
            // this will reassure it that it is in fact wrong
//            ((ServerPlayerEntity) (Object) this).getInventory().updateItems();
            ((ServerPlayerEntity) (Object) this).networkHandler.sendPacket(new ScreenHandlerSlotUpdateS2CPacket(0, 0, 36 + ((ServerPlayerEntity) (Object) this).getInventory().selectedSlot, ((ServerPlayerEntity) (Object) this).getInventory().main.get(((ServerPlayerEntity) (Object) this).getInventory().selectedSlot)));
            cir.setReturnValue(false);
        }
    }
}
