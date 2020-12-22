package li.cli.oc.blocks.commons

import net.minecraft.block.BlockState
import net.minecraft.util.math.Direction

interface OCNetwork {

    fun allowedToBeConnected(state: BlockState?): Array<Direction> {
        return Direction.values()
    }
}