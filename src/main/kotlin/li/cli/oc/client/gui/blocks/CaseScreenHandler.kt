package li.cli.oc.client.gui.blocks

import li.cli.oc.Components
import li.cli.oc.client.gui.blocks.slots.*
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SimpleInventory
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.slot.Slot

class CaseScreenHandler(syncId: Int, playerInventory: PlayerInventory, val tier: Int) : ScreenHandler(Components.CASE_SCREEN_HANDLER, syncId) {
    private val inventory = SimpleInventory(10)

    override fun canUse(player: PlayerEntity?): Boolean {
        return inventory.canPlayerUse(player)
    }

    init {
        // BIOS Slot to the left of the power button
        addSlot(Slot(inventory, 0, 20, 20))

        // Two Expansion Slot Cards to the left of the component grid
        addSlot(Slot(inventory, 1, 80, 10))
        addSlot(Slot(inventory, 2, 80, 30))

        // One CPU Slot in the center top of the component grid
        addSlot(Slot(inventory, 3, 100, 10))

        // Two RAM Slots in the direct middle and bottom middle of the component grid
        addSlot(Slot(inventory, 4, 100, 30))
        addSlot(Slot(inventory, 5, 100, 50))

        // One HDD Slot in the top right of the component grid
        addSlot(Slot(inventory, 6, 120, 10))
    }
}