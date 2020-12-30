package li.cli.oc.client.gui.blocks.slots

import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.slot.Slot

class ExpSlot(inventory: Inventory, index: Int, x: Int, y: Int, level: Int) : Slot(inventory, index, x, y) {
    private val level: Int = 0

/*    override fun canInsert(stack: ItemStack?): Boolean {
        if (stack != null) {
            return stack.item is ExpansionCard && ExpansionCard.level <= level// TODO: Rename once Expansion Card item class is created
        }

        return false
    }*/
}