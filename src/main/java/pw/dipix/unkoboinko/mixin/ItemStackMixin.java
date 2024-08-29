package pw.dipix.unkoboinko.mixin;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pw.dipix.unkoboinko.UnkoBoinko;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @SuppressWarnings("ConstantValue")
    @Inject(method = "takesDamageFrom(Lnet/minecraft/entity/damage/DamageSource;)Z", at = @At("HEAD"), cancellable = true)
    public void takesDamageFrom(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        UnkoBoinko.INSTANCE.logDamage(source);
        // intellij is confused
        if ((source.isOf(DamageTypes.CACTUS) && ((ItemStack) (Object) this).isIn(UnkoBoinko.INSTANCE.getRestrictedCacti()))
                || (source.isOf(DamageTypes.LAVA) && ((ItemStack) (Object) this).isIn(UnkoBoinko.INSTANCE.getRestrictedLava()))
                || (source.isOf(DamageTypes.IN_FIRE) && ((ItemStack) (Object) this).isIn(UnkoBoinko.INSTANCE.getRestrictedBurning()))
                || (source.isOf(DamageTypes.ON_FIRE) && ((ItemStack) (Object) this).isIn(UnkoBoinko.INSTANCE.getRestrictedBurning()))
                || (source.isIn(DamageTypeTags.IS_EXPLOSION) && ((ItemStack) (Object) this).isIn(UnkoBoinko.INSTANCE.getRestrictedExplosion()))
                || (((ItemStack) (Object) this).isIn(UnkoBoinko.INSTANCE.getInvulnerable())))
            cir.setReturnValue(false);
    }
}
