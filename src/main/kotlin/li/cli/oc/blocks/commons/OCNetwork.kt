package li.cli.oc.blocks.commons

import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.math.Direction
import java.util.*

interface OCNetwork {

    fun getUUID(blockEntity: BlockEntity?): UUID {
        return UUID.randomUUID()
    }

    fun allowedToBeConnected(state: BlockState?): Array<Direction> {
        return Direction.values()
    }
}