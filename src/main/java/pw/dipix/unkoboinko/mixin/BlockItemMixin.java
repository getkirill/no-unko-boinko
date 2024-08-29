package pw.dipix.unkoboinko.mixin;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pw.dipix.unkoboinko.UnkoBoinko;

@Mixin(BlockItem.class)
public abstract class BlockItemMixin {
    @Inject(method = "canBeNested()Z", at = @At("HEAD"), cancellable = true)
    public void shulkerPatch(CallbackInfoReturnable<Boolean> cir) {
        if(Registries.ITEM.getEntry((Item)(Object)this).isIn(UnkoBoinko.INSTANCE.getRestrictedNesting())) cir.setReturnValue(false);
    }
}
