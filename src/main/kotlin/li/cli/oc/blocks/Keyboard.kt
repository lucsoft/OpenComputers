package li.cli.oc.blocks

import li.cli.oc.blockentity.Keyboard
import li.cli.oc.blocks.commons.TecBlock
import li.cli.oc.blocks.commons.States
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.BlockRenderType
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.block.ShapeContext
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView


class Keyboard: TecBlock(FabricBlockSettings.of(Material.METAL).nonOpaque()) {

    val pitch = States.Pitch;
    val yaw = States.Yaw;

    init {
        defaultState = (stateManager.defaultState as BlockState)
            .with(pitch, States.Pitches[0])
            .with(yaw, States.Yaws[0])

    }

    override fun getPlacementState(ctx: ItemPlacementContext?): BlockState? {
        var currentpitch = Direction.NORTH;
        if(ctx?.playerLookDirection === Direction.UP || ctx?.playerLookDirection === Direction.DOWN)
            currentpitch = ctx.playerLookDirection.opposite;

        return this.defaultState.with(pitch, currentpitch).with(yaw, ctx?.playerFacing?.opposite);
    }

    override fun emitsRedstonePower(state: BlockState?): Boolean {
        return false
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>?) {
        builder!!.add(States.Pitch, States.Yaw);
    }

    override fun getOutlineShape(state: BlockState?, view: BlockView?, pos: BlockPos?, context: ShapeContext?): VoxelShape? {
        //TODO Add more shape styles
        return when(state?.get(pitch)) {
            Direction.UP -> when(state.get(yaw)) {
                Direction.SOUTH, Direction.NORTH -> VoxelShapes.cuboid(0.06, 0.0, 0.25, 0.94, 0.07, 0.75)
                Direction.WEST, Direction.EAST -> VoxelShapes.cuboid(0.25, 0.0, 0.06, 0.75, 0.07, 0.94)

                else -> null!!
            }
            Direction.DOWN -> when(state.get(yaw)) {
                Direction.SOUTH, Direction.NORTH -> VoxelShapes.cuboid(0.06, 0.93, 0.25, 0.94, 1.0, 0.75)
                Direction.WEST, Direction.EAST -> VoxelShapes.cuboid(0.25, 0.93, 0.06, 0.75, 1.0, 0.94)

                else -> null!!
            }
            else -> when(state?.get(yaw)) {
                Direction.SOUTH -> VoxelShapes.cuboid(0.06, 0.25, 0.0, 0.94, 0.75, 0.07)
                Direction.NORTH -> VoxelShapes.cuboid(0.06, 0.25, 0.93, 0.94, 0.75, 1.0)
                Direction.WEST -> VoxelShapes.cuboid(0.93, 0.25, 0.06, 1.0, 0.75, 0.94)
                Direction.EAST -> VoxelShapes.cuboid(0.0, 0.25, 0.06, 0.07, 0.75, 0.94)

                else -> null!!
            }
        }
    }

    override fun createBlockEntity(world: BlockView?): BlockEntity? {
        return Keyboard()
    }
}
