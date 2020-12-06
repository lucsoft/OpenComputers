package li.cli.oc.client

import li.cli.oc.Components
import li.cli.oc.OpenComputers
import li.cli.oc.blocks.CableModel
import li.cli.oc.blocks.Case
import li.cli.oc.blocks.Screen
import li.cli.oc.items.commons.ComponentBlockItem
import li.cli.oc.render.BaseModelProvider
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.minecraft.block.BlockState
import net.minecraft.client.render.RenderLayer
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockRenderView

object ClientInit : ClientModInitializer {
    override fun onInitializeClient() {

        ModelLoadingRegistry.INSTANCE.registerResourceProvider { BaseModelProvider(Identifier(OpenComputers.modId, "block/cable"), CableModel()) }

        ColorProviderRegistry.BLOCK.register(handleBlockColor,
            Components.Blocks.CaseOne.block,
            Components.Blocks.CaseTwo.block,
            Components.Blocks.CaseThree.block,
            Components.Blocks.CaseCreative.block,
            Components.Blocks.ScreenOne.block,
            Components.Blocks.ScreenTwo.block,
            Components.Blocks.ScreenThree.block,
        )

        ColorProviderRegistry.ITEM.register(handleItemColor,
            Components.Items.CaseOne.item,
            Components.Items.CaseTwo.item,
            Components.Items.CaseThree.item,
            Components.Items.CaseCreative.item,
            Components.Items.ScreenOne.item,
            Components.Items.ScreenTwo.item,
            Components.Items.ScreenThree.item,
        )

        BlockRenderLayerMap.INSTANCE.putBlock(Components.Blocks.Assembler.block, RenderLayer.getTranslucent());
    }

    private  val handleItemColor = fun (item: ItemStack, tintIndex: Int): Int {
        if(item.item is ComponentBlockItem)
            return (item.item as ComponentBlockItem).getColor() ?: 0xFF0000
        return 0xFF0000
    }

    private val handleBlockColor = fun (state: BlockState, view: BlockRenderView?, pos: BlockPos?, tintIndex: Int): Int {
        if(state.block is Case)
            return (state.block as Case).getColor()
        else if(state.block is Screen)
            return (state.block as Screen).getColor()
        return 0xFF0000
    }
}
