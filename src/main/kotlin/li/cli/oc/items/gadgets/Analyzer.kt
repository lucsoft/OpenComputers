package li.cli.oc.items.gadgets

import li.cli.oc.OpenComputers
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

class Analyzer : Item(Settings().group(OpenComputers.ITEM_GROUP)) {

    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack> {
        if (world!!.isClient) {
            user!!.sendMessage(Text.of("Work in progress"), true)
        }
        return TypedActionResult(ActionResult.SUCCESS, if (hand == Hand.MAIN_HAND) user!!.mainHandStack else user!!.offHandStack)
    }

}
