package li.cli.oc.items

import li.cli.oc.Components
import li.cli.oc.OpenComputers
import li.cli.oc.blockentity.commons.TecBlockEntity
import li.cli.oc.blocks.commons.OCNetwork
import li.cli.oc.networking.ExecutionNode
import li.cli.oc.networking.ServerNetworkHandler
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsageContext
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

class Debugger: Item(Settings().group(OpenComputers.ITEM_GROUP)) {

    override fun useOnBlock(context: ItemUsageContext?): ActionResult {
        val world = context?.world ?: return ActionResult.FAIL
        val block = world.getBlockState(context.blockPos)?.block
        val blockEntity = world.getBlockEntity(context.blockPos) as? TecBlockEntity

        if(block !is OCNetwork) return ActionResult.PASS;
        if(!world.isClient) return ActionResult.PASS;

        context.player?.sendMessage(Text.of(blockEntity?.address.toString()), true);

        return ActionResult.SUCCESS;
    }

    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack> {
        if(world?.isClient == false) {
            println("Server: " + ServerNetworkHandler.cachedNodes.map { x -> x.value.pos }.toString())
            println("Server: " + ServerNetworkHandler.cachedNodes.toString())
        }
        ExecutionNode().startNode()
        return TypedActionResult.success(ItemStack(Components.Items.Debugger.item), false);
    }

}