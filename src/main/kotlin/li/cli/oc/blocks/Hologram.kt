package li.cli.oc.blocks

import li.cli.oc.blockentity.Hologram
import li.cli.oc.blocks.commons.TecBlock
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.block.ShapeContext
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView


class Hologram: TecBlock(FabricBlockSettings.of(Material.METAL).nonOpaque()) {

    override fun emitsRedstonePower(state: BlockState?): Boolean {
        return false
    }

    override fun getOutlineShape(
        state: BlockState?,
        world: BlockView?,
        pos: BlockPos?,
        context: ShapeContext?
    ): VoxelShape {
        return VoxelShapes.cuboid(0.0, 0.0, 0.0, 1.0, 0.4, 1.0)
    }

    override fun allowedToBeConnected(state: BlockState?): Array<Direction> {
        return arrayOf(Direction.DOWN)
    }

    override fun createBlockEntity(world: BlockView?): BlockEntity? {
        return Hologram()
    }
}