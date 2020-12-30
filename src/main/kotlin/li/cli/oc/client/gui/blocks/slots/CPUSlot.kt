package li.cli.oc.client.gui.blocks.slots

import li.cli.oc.items.CPU
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.slot.Slot

class CPUSlot(inventory: Inventory, index: Int, x: Int, y: Int, level: Int) : Slot(inventory, index, x, y) {
    private val tier: Int = 0

/*    override fun canInsert(stack: ItemStack?): Boolean {
        if (stack != null) {
            return stack.item is CPU && CPU.tier <= tier
        }

        return false
    }*/
}