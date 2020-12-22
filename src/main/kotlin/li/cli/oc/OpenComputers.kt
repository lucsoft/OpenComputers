package li.cli.oc

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import li.cli.oc.util.CraftingComponents

object OpenComputers: ModInitializer {

	const val modId = "opencomputers"

	val ITEM_GROUP = FabricItemGroupBuilder.build(Identifier(modId, "general")) { ItemStack(Components.Blocks.CaseOne.block) }

	val GSON = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).setPrettyPrinting().create()

	override fun onInitialize() {
		ConfigLoader.initializeConfig()
		Components.registerComponents()
		CraftingComponents.registerSpecialCrafting()
	}


}
