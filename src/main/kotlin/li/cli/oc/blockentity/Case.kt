package li.cli.oc.blockentity

import li.cli.oc.blocks.commons.RedstoneAwareEntity
import li.cli.oc.client.gui.blocks.CaseScreenHandler
import li.cli.oc.components.BlockEntitiesComponent
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.ScreenHandler
import net.minecraft.text.Text

class CaseEntity(val Tier: Int) : RedstoneAwareEntity(BlockEntitiesComponent.Case), NamedScreenHandlerFactory {
    override fun createMenu(syncId: Int, inv: PlayerInventory, player: PlayerEntity): ScreenHandler {
        return CaseScreenHandler(syncId, inv, Tier)
    }

    override fun getDisplayName(): Text {
        return Text.of("Computer")
    }
}