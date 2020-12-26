package li.cli.oc.blocks.commons;

import li.cli.oc.blockentity.commons.TecBlockEntity
import li.cli.oc.components.BlockEntitiesComponent
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.util.math.Direction;

open class RedstoneAwareEntity(type: BlockEntitiesComponent) : TecBlockEntity(type) {

//    val input = Arrays.asList(-1, -1, -1, -1, -1, -1)
    val output = listOf(0, 0, 0, 0, 0, 0)

    fun getOutput(direction: Direction): Int {

        for (i in 0..Direction.values().size) {
            if (Direction.byId(i) == direction) {
                return output[i];
            }
        }
        return 0;
    }
}
