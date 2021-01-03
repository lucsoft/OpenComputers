package li.cli.oc.blocks

import li.cli.oc.blockentity.Rack
import li.cli.oc.blocks.commons.TecBlock
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.HorizontalFacingBlock
import net.minecraft.block.Material
import net.minecraft.block.entity.BlockEntity
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.util.math.Direction
import net.minecraft.world.BlockView

class Rack: TecBlock(FabricBlockSettings.of(Material.METAL).hardness(1.5f).resistance(2.5f)) {

    val facing = HorizontalFacingBlock.FACING

    init {
        defaultState = (stateManager.defaultState as BlockState)
            .with(facing, Direction.NORTH)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>?) {
        builder!!.add(HorizontalFacingBlock.FACING)
    }

    override fun getPlacementState(ctx: ItemPlacementContext?): BlockState? {
        return this.defaultState.with(facing, ctx?.playerFacing?.opposite)
    }

    override fun emitsRedstonePower(state: BlockState?): Boolean {
        return false
    }

    override fun allowedToBeConnected(state: BlockState?): Array<Direction> {
        val blockFaceing = state?.get(facing)!!
        return arrayOf(Direction.UP, Direction.DOWN, blockFaceing.opposite, blockFaceing.rotateYCounterclockwise(), blockFaceing.rotateYClockwise())
    }

    override fun createBlockEntity(world: BlockView?): BlockEntity? {
        return Rack()
    }
}