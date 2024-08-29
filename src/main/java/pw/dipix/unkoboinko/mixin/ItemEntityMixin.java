package pw.dipix.unkoboinko.mixin;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.dimension.DimensionTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pw.dipix.unkoboinko.UnkoBoinko;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {
    @Inject(method = "<init>*", at = @At("RETURN"))
    public void constructorStuff(CallbackInfo ci) {
        if (((ItemEntity) (Object) this).getStack().isIn(UnkoBoinko.INSTANCE.getRestrictedDespawning())) {
            ((ItemEntity) (Object) this).setNeverDespawn();
        }
        if (((ItemEntity) (Object) this).getStack().isIn(UnkoBoinko.INSTANCE.getInvulnerable())) {
            ((ItemEntity) (Object) this).setInvulnerable(true);
        }
    }

//    @Inject(method = "damage(Lnet/minecraft/entity/damage/DamageSource;F)Z", at = @At("HEAD"), cancellable = true)
//    public void aVoidDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
//        UnkoBoinko.INSTANCE.getLOGGER().info(source.getName());
//        UnkoBoinko.INSTANCE.getLOGGER().info("is out of world? {}", source.isOf(DamageTypes.OUT_OF_WORLD));
//        UnkoBoinko.INSTANCE.getLOGGER().info("is in restricted throw dim? {}", (((ItemEntity) (Object) this).getEntityWorld().getDimensionEntry().isIn(UnkoBoinko.INSTANCE.getRestrictedThrowingDimensions())));
//        UnkoBoinko.INSTANCE.getLOGGER().info("is in restricted throw item list? {}", ((ItemEntity) (Object) this).getStack().isIn(UnkoBoinko.INSTANCE.getRestrictedThrowing()));
//        //noinspection ConstantValue
//
//        if ((source.isOf(DamageTypes.OUT_OF_WORLD)
//                && ((ItemEntity) (Object) this).getStack().isIn(UnkoBoinko.INSTANCE.getRestrictedThrowing()))
//                && (((ItemEntity) (Object) this).getEntityWorld().getDimensionEntry().isIn(UnkoBoinko.INSTANCE.getRestrictedThrowingDimensions()))) {
//
//        }
//    }
}
