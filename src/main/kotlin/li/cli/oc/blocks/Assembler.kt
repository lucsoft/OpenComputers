package li.cli.oc.blocks

import li.cli.oc.blockentity.Assembler
import li.cli.oc.blocks.commons.TecBlock
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.block.entity.BlockEntity
import net.minecraft.world.BlockView

class Assembler: TecBlock(FabricBlockSettings.of(Material.METAL).hardness(1.5f).resistance(2.5f).nonOpaque()) {

    override fun createBlockEntity(world: BlockView?): BlockEntity? {
        return Assembler()
    }
}