package li.cli.oc.items

import li.cli.oc.OpenComputers
import li.cli.oc.blocks.commons.OCNetwork
import li.cli.oc.blocks.commons.TecBlock
import net.minecraft.item.Item
import net.minecraft.item.ItemUsageContext
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3i

class Debugger: Item(Settings().group(OpenComputers.ITEM_GROUP)) {

    override fun useOnBlock(context: ItemUsageContext?): ActionResult {
        val world = context?.world ?: return ActionResult.FAIL
        val blockEntity = world.getBlockState(context.blockPos)?.block
        if(blockEntity !is OCNetwork) return ActionResult.PASS;
        if(!world.isClient) return ActionResult.PASS;
        context.player?.sendMessage(Text.of("$blockEntity"), true)

        val list = mutableSetOf<BlockPos>()

        fun checkNetworkNode(pos: BlockPos, direction: Direction?) {
            val blockState = world.getBlockState(pos)
            val block = blockState.block
            if(block !is OCNetwork) return;
            if(pos in list) return;

            val allowedConnected = block.allowedToBeConnected(blockState)
            if(direction != null) {
                if(!allowedConnected.contains(direction)) return;
            }
            list.add(pos)
            allowedConnected.forEach { x -> checkNetworkNode(pos.add(x.vector), x.opposite) }
        }
        checkNetworkNode(context.blockPos, null)
        context.player?.sendMessage(Text.of("${list.count()} Nodes"), true)

        return ActionResult.SUCCESS;
    }



}