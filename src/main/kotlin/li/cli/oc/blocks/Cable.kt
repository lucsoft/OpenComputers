package li.cli.oc.blocks

import li.cli.oc.blockentity.Cable
import li.cli.oc.blocks.commons.OCNetwork
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.function.BooleanBiFunction
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.World

class Cable: BlockWithEntity(FabricBlockSettings.of(Material.METAL).nonOpaque()), OCNetwork {

    override fun getRenderType(state: BlockState?): BlockRenderType {
        return  BlockRenderType.MODEL
    }
    override fun createBlockEntity(world: BlockView?): BlockEntity {
        return Cable()
    }
    override fun getOutlineShape(
        state: BlockState?,
        world: BlockView?,
        pos: BlockPos?,
        context: ShapeContext?
    ): VoxelShape {
        val cable = world!!.getBlockEntity(pos) as? Cable
        val base = VoxelShapes.cuboid(0.375,0.375,0.375,0.625,0.625,0.625);
        var current = base

        val connections = cable?.updateConnected() ?: return current

        if (Direction.UP in connections)
            current = VoxelShapes.combine(current,VoxelShapes.cuboid(0.375, 0.625, 0.375, 0.625, 1.0, 0.625), BooleanBiFunction.OR );

        if (Direction.DOWN in connections)
            current = VoxelShapes.combine(current,VoxelShapes.cuboid(0.375, 0.0, 0.375, 0.625, 0.625, 0.625), BooleanBiFunction.OR );

        if (Direction.SOUTH in connections)
            current = VoxelShapes.combine(current,VoxelShapes.cuboid(0.375, 0.375, 0.375, 0.625, 0.625, 1.0), BooleanBiFunction.OR );

        if (Direction.NORTH in connections)
            current = VoxelShapes.combine(current,VoxelShapes.cuboid(0.375, 0.375, .0, 0.625, 0.625, .625), BooleanBiFunction.OR );

        if (Direction.WEST in connections)
            current = VoxelShapes.combine(current,VoxelShapes.cuboid(0.0, 0.375, 0.375, 0.625, 0.625, .625), BooleanBiFunction.OR );

        if (Direction.EAST in connections)
            current = VoxelShapes.combine(current,VoxelShapes.cuboid(0.375, 0.375, .375, 1.0, 0.625, .625), BooleanBiFunction.OR );

        return current
    }
}