package li.cli.oc.blockentity

import li.cli.oc.blockentity.commons.TecBlockEntity
import li.cli.oc.blocks.Cable
import li.cli.oc.blocks.commons.TecBlock
import li.cli.oc.components.BlockEntitiesComponent
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3i

class Cable: TecBlockEntity(BlockEntitiesComponent.Cable) {

    var connections: MutableList<Direction>? = null

    fun updateConnected(): MutableList<Direction> {
        val checkForCable = listOf(
            Vec3i(0,-1,0), Vec3i(0,1,0),
            Vec3i(0,0,-1), Vec3i(0,0,1),
            Vec3i(-1,0,0), Vec3i(1,0,0)
        )
        val connection = mutableListOf<Direction>()
        checkForCable.forEachIndexed { index, vec3i ->
            val blockstate = world!!.getBlockState(pos?.add(vec3i))
            val block = blockstate.block
            val direction =  Direction.byId(index)
            if(block is Cable)
                connection.add(direction)
            else if(block is TecBlock && direction.opposite in block.allowedToBeConnected(blockstate)) {
                connection.add(direction)
            }
        }

        connections = connection;
        return connection;
    }
}