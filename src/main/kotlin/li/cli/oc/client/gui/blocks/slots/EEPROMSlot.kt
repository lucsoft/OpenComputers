package li.cli.oc.client.gui.blocks.slots

import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.slot.Slot

class EEPROMSlot(inventory: Inventory, index: Int, x: Int, y: Int) : Slot(inventory, index, x, y) {

/*    override fun canInsert(stack: ItemStack?): Boolean {
        if (stack != null) {
            return stack.item is EEPROM // TODO: Rename once BIOS Card item class is created
        }

        return false
    }*/
}