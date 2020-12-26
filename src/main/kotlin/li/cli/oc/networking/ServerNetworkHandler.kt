package li.cli.oc.networking

import li.cli.oc.blockentity.commons.TecBlockEntity
import net.minecraft.server.MinecraftServer
import net.minecraft.util.math.Direction
import net.minecraft.world.World
import java.util.*

object ServerNetworkHandler {

    val cachedNodes = mutableMapOf<UUID, TecBlockEntity>()

    fun registerToNetwork(block: TecBlockEntity) {
        var uuid: UUID? = block.address
        if (block.address == null) {
            uuid = UUID.randomUUID()
            block.setUUIDAddress(uuid)
        }

        cachedNodes[uuid!!] = block
    }

    fun unregisterNetwork(uuid: UUID) {
        cachedNodes.remove(uuid)
    }

    fun fetchNetwork(block: TecBlockEntity) {

    }
}