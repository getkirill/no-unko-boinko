package pw.dipix.unkoboinko.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pw.dipix.unkoboinko.EnderRestrictedSlot;
import pw.dipix.unkoboinko.UnkoBoinko;

@Mixin(ScreenHandler.class)
public abstract class ScreenHandlerMixin {

    @Inject(method = "addSlot(Lnet/minecraft/screen/slot/Slot;)Lnet/minecraft/screen/slot/Slot;", at = @At(value = "HEAD"), cancellable = true)
    public void enderChestFunkySlots(Slot slot, CallbackInfoReturnable<Slot> cir) {
        /*
         * Hello Traveller
         * i gave up making an elegant solution and just copied existing code, forgive me or make a PR
         */
//        if(!(slot.inventory instanceof EnderChestInventory)) return ((ScreenHandlerAccessor) instance).addSlot(slot);
//        if(!(slot instanceof EnderRestrictedSlot)) {
//            // replace slot
//            return ((ScreenHandlerAccessor) instance).addSlot(new EnderRestrictedSlot(slot));
//        } else { // ignore
//            return slot;
//        }
//        UnkoBoinko.INSTANCE.getLOGGER().info("here's what i know:\nare we in an enderchest? (instanceof EnderChestInventory): {}\nis this slot a restricted slot? {}", slot.inventory instanceof EnderChestInventory, slot instanceof EnderRestrictedSlot);
        if ((slot.inventory instanceof EnderChestInventory)) {
//            LOGGER.info("we met the conditions, replacing slot");
            EnderRestrictedSlot newSlot = new EnderRestrictedSlot(slot);
            newSlot.id = ((ScreenHandler) (Object) this).slots.size();
            ((ScreenHandler) (Object) this).slots.add(newSlot);
            ((ScreenHandler) (Object) this).trackedStacks.add(ItemStack.EMPTY);
            ((ScreenHandler) (Object) this).previousTrackedStacks.add(ItemStack.EMPTY);
            cir.setReturnValue(newSlot);
        }
    }

    @Inject(method = "internalOnSlotClick(IILnet/minecraft/screen/slot/SlotActionType;Lnet/minecraft/entity/player/PlayerEntity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/collection/DefaultedList;get(I)Ljava/lang/Object;", ordinal = 5), cancellable = true)
    public void noQuickThrow(int slotIndex, int button, SlotActionType actionType, PlayerEntity player, CallbackInfo ci) {
        UnkoBoinko.INSTANCE.getLOGGER().info("hooked quick throw (Q)");
        Slot slot = ((ScreenHandler) (Object) this).slots.get(slotIndex);
        if (slot.getStack().isIn(UnkoBoinko.INSTANCE.getRestrictedThrowing())) ci.cancel();
    }

    @Inject(method = "internalOnSlotClick(IILnet/minecraft/screen/slot/SlotActionType;Lnet/minecraft/entity/player/PlayerEntity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/ScreenHandler;getCursorStack()Lnet/minecraft/item/ItemStack;", ordinal = 5), cancellable = true)
    public void noClickThrow(int slotIndex, int button, SlotActionType actionType, PlayerEntity player, CallbackInfo ci) {
        UnkoBoinko.INSTANCE.getLOGGER().info("hooked outside window throw");
        if (((ScreenHandler) (Object) this).getCursorStack().isIn(UnkoBoinko.INSTANCE.getRestrictedThrowing()))
            ci.cancel();
    }
}
