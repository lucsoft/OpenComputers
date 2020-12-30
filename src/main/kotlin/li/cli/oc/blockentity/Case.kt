package li.cli.oc.blockentity

import li.cli.oc.blocks.commons.RedstoneAwareEntity
import li.cli.oc.client.gui.blocks.CaseScreenHandler
import li.cli.oc.components.BlockEntitiesComponent
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.PacketByteBuf
import net.minecraft.screen.ScreenHandler
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text

class CaseEntity(var Tier: Int) : RedstoneAwareEntity(BlockEntitiesComponent.Case), ExtendedScreenHandlerFactory {
    init {
        markDirty()
    }

    override fun createMenu(syncId: Int, inv: PlayerInventory, player: PlayerEntity): ScreenHandler {
        return CaseScreenHandler(syncId, inv, Tier)
    }

    override fun getDisplayName(): Text {
        return Text.of("Computer")
    }

    override fun writeScreenOpeningData(playerEntity: ServerPlayerEntity?, buf: PacketByteBuf?) {
        buf?.writeInt(Tier)
    }

    override fun toTag(tag: CompoundTag): CompoundTag {
        super.toTag(tag)
        tag.putInt("tier", Tier)
        return tag
    }

    override fun fromTag(state: BlockState?, tag: CompoundTag?) {
        super.fromTag(state, tag)
        Tier = tag?.getInt("tier")!!
    }
}