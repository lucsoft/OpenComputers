package li.cli.oc

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.block.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

object OpenComputers: ModInitializer {

	const val modId = "opencomputers"

	val ITEM_GROUP = FabricItemGroupBuilder.build(Identifier(modId, "general")) { ItemStack(Blocks.NETHERITE_BLOCK) }

	override fun onInitialize() {
		Components.registerComponents()
	}
}
