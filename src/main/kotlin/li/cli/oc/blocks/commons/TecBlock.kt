package li.cli.oc.blocks.commons

import net.minecraft.block.BlockRenderType
import net.minecraft.block.BlockState
import net.minecraft.block.BlockWithEntity
import net.minecraft.block.entity.BlockEntity
import net.minecraft.world.BlockView

open class TecBlock(settings: Settings?) : BlockWithEntity(settings), OCNetwork {

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
}
