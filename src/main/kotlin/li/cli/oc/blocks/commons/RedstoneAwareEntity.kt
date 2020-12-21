package li.cli.oc.blocks.commons;

import li.cli.oc.client.gui.blocks.CaseScreenHandler
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.ScreenHandler
import net.minecraft.text.Text
import net.minecraft.util.math.Direction;
import java.util.*

open class RedstoneAwareEntity(type: BlockEntityType<*>?) : BlockEntity(type) {

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
