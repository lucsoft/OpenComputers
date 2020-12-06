package li.cli.oc.blocks

import li.cli.oc.blocks.commons.TecBlock
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Material


class Printer: TecBlock(FabricBlockSettings.of(Material.METAL).nonOpaque()) {

    override fun emitsRedstonePower(state: BlockState?): Boolean {
        return false
    }
}