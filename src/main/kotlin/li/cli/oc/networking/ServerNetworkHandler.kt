package li.cli.oc.networking

import li.cli.oc.blockentity.commons.TecBlockEntity
import java.util.*

object ServerNetworkHandler {

    val chachedNodes = mutableMapOf<UUID, TecBlockEntity>()

    fun registerToNetwork(block: TecBlockEntity): UUID {
        var uuid: UUID? = block.address
        if (block.address == null) {
            uuid = UUID.randomUUID()
            block.setUUIDAddress(uuid)
        }

        chachedNodes[uuid!!] = block
        return uuid;
    }

    fun unregisterNetwork(uuid: UUID) {
        chachedNodes.remove(uuid)
    }
}