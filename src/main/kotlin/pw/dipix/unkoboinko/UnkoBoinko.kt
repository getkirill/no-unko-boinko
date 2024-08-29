package pw.dipix.unkoboinko

import net.fabricmc.api.ModInitializer
import net.minecraft.entity.damage.DamageSource
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier
import net.minecraft.world.dimension.DimensionType
import net.minecraft.world.dimension.DimensionTypes
import org.slf4j.LoggerFactory

object UnkoBoinko : ModInitializer {
    val LOGGER = LoggerFactory.getLogger(this::class.java)
    const val IDENTIFIER = "no_unko_boinko"
    val invulnerable = TagKey.of(Registries.ITEM.key, Identifier.of(IDENTIFIER, "invulnerable"));
    val restrictedBurning = TagKey.of(Registries.ITEM.key, Identifier.of(IDENTIFIER, "restricted_burning"));
    val restrictedExplosion = TagKey.of(Registries.ITEM.key, Identifier.of(IDENTIFIER, "restricted_explosion"));
    val restrictedDespawning = TagKey.of(Registries.ITEM.key, Identifier.of(IDENTIFIER, "restricted_despawning"));
    val restrictedCacti = TagKey.of(Registries.ITEM.key, Identifier.of(IDENTIFIER, "restricted_cacti"));
    val restrictedLava = TagKey.of(Registries.ITEM.key, Identifier.of(IDENTIFIER, "restricted_lava"));
    val restrictedEnderChest = TagKey.of(Registries.ITEM.key, Identifier.of(IDENTIFIER, "restricted_ender_chest"));
    val restrictedNesting = TagKey.of(Registries.ITEM.key, Identifier.of(IDENTIFIER, "restricted_nesting"));
    val restrictedThrowing = TagKey.of(Registries.ITEM.key, Identifier.of(IDENTIFIER, "restricted_throwing"));
    val restrictedVoid = TagKey.of(Registries.ITEM.key, Identifier.of(IDENTIFIER, "restricted_void"));
    val restrictedThrowingDimensions = TagKey.of(RegistryKeys.DIMENSION_TYPE, Identifier.of(IDENTIFIER, "restricted_throwing"));
    override fun onInitialize() {
    }

    fun logDamage(source: DamageSource) {
        LOGGER.info("Taking damage from $source")
//        LOGGER.info(Registries.ITEM.iterateEntries(restrictedCacti).joinToString(", "))
    }
}