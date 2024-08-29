package pw.dipix.unkoboinko.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.dimension.DimensionTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pw.dipix.unkoboinko.UnkoBoinko;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(method = "tickInVoid", at = @At("HEAD"), cancellable = true)
    public void noTickInVoid(CallbackInfo ci) {
        //noinspection ConstantValue
        if (((Object) this) instanceof ItemEntity
                && ((ItemEntity) (Object) this).getStack().isIn(UnkoBoinko.INSTANCE.getRestrictedVoid())
                /*&& (((ItemEntity) (Object) this).getEntityWorld().getDimensionEntry().isIn(UnkoBoinko.INSTANCE.getRestrictedThrowingDimensions()))*/) {
            UnkoBoinko.INSTANCE.getLOGGER().info("Item {} dropped in void! Teleporting back to dimension origin", ((ItemEntity) (Object) (this)).getName().getString());
            ((ItemEntity) (Object) this).setPosition((((ItemEntity) (Object) this).getEntityWorld().getDimensionEntry().matchesKey(DimensionTypes.THE_END) ? ServerWorld.END_SPAWN_POS : ((ItemEntity) (Object) this).getEntityWorld().getSpawnPos()).toCenterPos());
            ci.cancel();
        }
    }
}
