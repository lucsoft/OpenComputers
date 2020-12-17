package li.cli.oc

import com.google.gson.JsonDeserializer
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.block.Blocks
import net.minecraft.client.MinecraftClient
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import net.minecraft.util.JsonSerializer
import java.io.File
import java.nio.charset.Charset
import com.google.gson.FieldNamingPolicy

import com.google.gson.GsonBuilder

import com.google.gson.Gson




object OpenComputers: ModInitializer {

	const val modId = "opencomputers"

	val ITEM_GROUP = FabricItemGroupBuilder.build(Identifier(modId, "general")) { ItemStack(Blocks.NETHERITE_BLOCK) }

	val GSON = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).setPrettyPrinting().create()

	override fun onInitialize() {
		ConfigLoader.initializeConfig()
		println(if (ConfigLoader.getConfig()?.energieCost?.screen != null) "OpenComputers config loaded" else "Failed to load OpenComputers")
		Components.registerComponents()
	}


}
