package li.cli.oc.blocks.commons

import net.minecraft.block.BlockRenderType
import net.minecraft.block.BlockState
import net.minecraft.block.BlockWithEntity
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.math.Direction
import net.minecraft.world.BlockView

open class TecBlock(settings: Settings?) : BlockWithEntity(settings) {

    open fun getColor(): Int? {
        return null
    }

    override fun emitsRedstonePower(state: BlockState?): Boolean {
        return false
    }

    override fun createBlockEntity(world: BlockView?): BlockEntity? {
        return null
    }

    override fun getRenderType(state: BlockState?): BlockRenderType {
        return BlockRenderType.MODEL
    }

    open fun allowedToBeConnected(state: BlockState?): Array<Direction> {
        return Direction.values()
    }
}
