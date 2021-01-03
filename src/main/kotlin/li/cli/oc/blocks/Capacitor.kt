package li.cli.oc.blocks

import li.cli.oc.blockentity.Capacitor
import li.cli.oc.blocks.commons.TecBlock
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.block.entity.BlockEntity
import net.minecraft.world.BlockView


class Capacitor: TecBlock(FabricBlockSettings.of(Material.METAL).hardness(1.5f).resistance(2.5f)) {

    override fun createBlockEntity(world: BlockView?): BlockEntity? {
        return Capacitor()
    }
}