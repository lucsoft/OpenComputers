package li.cli.oc.client

import li.cli.oc.Components
import li.cli.oc.blocks.Case
import li.cli.oc.render.DyeColor
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockRenderView

object ClientInit : ClientModInitializer {
    override fun onInitializeClient() {
        ColorProviderRegistry.BLOCK.register(handleColor, Components.caseOne, Components.caseTwo, Components.caseThree, Components.caseFour )

    }
    private val handleColor = fun (state: BlockState, view: BlockRenderView?, pos: BlockPos?, tintIndex: Int): Int {
        return (state.block as Case).getColor()
    }
}