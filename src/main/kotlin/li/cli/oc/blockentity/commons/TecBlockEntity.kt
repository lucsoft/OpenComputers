package li.cli.oc.blockentity.commons

import li.cli.oc.components.BlockEntitiesComponent
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.nbt.CompoundTag
import java.util.*

open class TecBlockEntity(type: BlockEntitiesComponent) : BlockEntity(type.entityType), BlockEntityClientSerializable  {

    var address: UUID? = null

    override fun toTag(tag: CompoundTag): CompoundTag {
        super.toTag(tag)
        if(address != null) tag?.putUuid("address", address)
        return tag
    }

    fun setUUIDAddress(id: UUID) {
        address = id;
        markDirty()
    }

    override fun fromTag(state: BlockState?, tag: CompoundTag?) {
        super.fromTag(state, tag)
        address = tag?.getUuid("address")
    }

    override fun fromClientTag(tag: CompoundTag?) {
        fromTag(null, tag)
    }

    override fun toClientTag(tag: CompoundTag): CompoundTag {
        return toTag(tag)
    }
}