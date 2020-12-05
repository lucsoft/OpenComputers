package li.cli.oc.blocks

import li.cli.oc.blocks.commons.TecBlock
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Material


class Hologram: TecBlock(FabricBlockSettings.of(Material.METAL)) {

    override fun emitsRedstonePower(state: BlockState?): Boolean {
        return false
    }
}