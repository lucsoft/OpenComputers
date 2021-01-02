package li.cli.oc

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import li.cli.oc.blockentity.commons.TecBlockEntity
import li.cli.oc.networking.ServerNetworkHandler
import li.cli.oc.utility.CraftingComponents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerBlockEntityEvents
import java.util.*

object OpenComputers: ModInitializer {

	const val modId = "opencomputers"

	val ITEM_GROUP = FabricItemGroupBuilder.build(Identifier(modId, "general")) { ItemStack(Components.Blocks.CaseOne.block) }

	val GSON = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).setPrettyPrinting().create()

	override fun onInitialize() {
		ConfigLoader.initializeConfig()
		Components.registerComponents()
		CraftingComponents.registerSpecialCrafting()

		ServerBlockEntityEvents.BLOCK_ENTITY_LOAD.register { blockEntity, _ ->
			if(blockEntity !is TecBlockEntity) return@register;
			ServerNetworkHandler.registerToNetwork(blockEntity)
		}
		ServerBlockEntityEvents.BLOCK_ENTITY_UNLOAD.register { blockEntity, _ ->
			if(blockEntity !is TecBlockEntity) return@register;
			if(blockEntity.address != null) ServerNetworkHandler.unregisterNetwork(blockEntity.address!!)
		}

	}
}
