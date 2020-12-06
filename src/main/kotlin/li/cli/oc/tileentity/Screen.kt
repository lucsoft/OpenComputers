package li.cli.oc.tileentity

import li.cli.oc.Components
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType

private fun getEntityFromTier(Tier: Int): BlockEntityType<BlockEntity> {
   return when(Tier) {
       1 -> Components.BlockEntities.ScreenOne.entityType
       2 -> Components.BlockEntities.ScreenTwo.entityType
       3 -> Components.BlockEntities.ScreenThree.entityType
       else -> null!!
   }
}

class Screen(Tier: Int): BlockEntity(getEntityFromTier(Tier)) {

}