package li.cli.oc;

import li.cli.oc.items.Components;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class OpenComputers implements ModInitializer {

	public static final String modId = "opencomputers";

	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(modId, "general"), () -> new ItemStack(Blocks.NETHERITE_BLOCK));

	@Override
	public void onInitialize() {
		Components.registerComponents();
	}
}
