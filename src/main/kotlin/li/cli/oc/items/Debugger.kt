package li.cli.oc.items

import li.cli.oc.OpenComputers
import li.cli.oc.blocks.commons.OCNetwork
import net.minecraft.block.Block
import net.minecraft.client.MinecraftClient
import net.minecraft.client.MinecraftClientGame
import net.minecraft.item.Item
import net.minecraft.item.ItemUsageContext
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText
import net.minecraft.util.ActionResult
import net.minecraft.util.Language
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction

class Debugger: Item(Settings().group(OpenComputers.ITEM_GROUP)) {

    override fun useOnBlock(context: ItemUsageContext?): ActionResult {
        val world = context?.world ?: return ActionResult.FAIL
        val blockEntity = world.getBlockState(context.blockPos)?.block
        if(blockEntity !is OCNetwork) return ActionResult.PASS;
        if(!world.isClient) return ActionResult.PASS;
        context.player?.sendMessage(Text.of("$blockEntity"), true)
        if (context.player?.isSneaking == false) {
            val list = mutableSetOf<BlockPos>()

            fun checkNetworkNode(pos: BlockPos, direction: Direction?) {
                val blockState = world.getBlockState(pos)
                val block = blockState.block
                if (block !is OCNetwork || pos in list) return;
                val allowedConnected = block.allowedToBeConnected(blockState)
                if (direction != null && !allowedConnected.contains(direction)) return

                list.add(pos)
                allowedConnected.forEach { x -> checkNetworkNode(pos.add(x.vector), x.opposite) }
            }
            checkNetworkNode(context.blockPos, null)

            val countedSet = mutableMapOf<Block, Int>()

            list.forEach { x ->
                val block = world.getBlockState(x).block;
                if (countedSet[block] == null)
                    countedSet[block] = 1
                else countedSet[block] = countedSet[block]!!.plus(1)
            }

            context.player?.sendMessage(Text.of("${list.count()} Nodes"), true)

            context.player?.sendMessage(
                Text.of(
                    "Scanned Nodes: \n${
                        countedSet.map { entry -> "x${entry.value} ${Language.getInstance().get(entry.key.translationKey)}§r" }.joinToString("\n")
                    }"
                ), false
            )
        } else {
            context.player?.sendMessage(Text.of("NodeID: ${blockEntity.getUUID(world.getBlockEntity(context.blockPos))}"), true)
        }
        return ActionResult.SUCCESS;
    }

}