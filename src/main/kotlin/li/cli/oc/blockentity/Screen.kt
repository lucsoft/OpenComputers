package li.cli.oc.blockentity

import li.cli.oc.Components
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.nbt.CompoundTag
import net.minecraft.block.BlockState
import net.minecraft.util.Tickable
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import java.util.*
import li.cli.oc.ConfigLoader
import li.cli.oc.blockentity.commons.TextBuffer
import li.cli.oc.blocks.commons.States
import li.cli.oc.render.Color
import li.cli.oc.render.block.Flags


private fun getEntityFromTier(Tier: Int): BlockEntityType<BlockEntity> {
   return when(Tier) {
       1 -> Components.BlockEntities.ScreenOne.entityType
       2 -> Components.BlockEntities.ScreenTwo.entityType
       3 -> Components.BlockEntities.ScreenThree.entityType
       else -> null!!
   }
}

class Screen(val tier: Int): BlockEntity(getEntityFromTier(tier)), Tickable  {

    var address: UUID? = null
    var textBuffer: TextBuffer? = null
    var width = 1
    var height = 1
    var offsetX = 0
    var offsetY = 0
    var removeState = false
    var notifying = false

    var connectedAt: Int? = null

    override fun tick() {
        if(connectedAt == null && !notifying && world != null) {
            notifyOthers()
            notifySelf()
            expand()
        }
    }

    fun getColor(): Int {
        return Color.getTearColors(tier - 1)
    }

    fun notifyOthers() {
        notifying = true

        if (offsetX > 0)            getNeighbour(offsetX - 1, offsetY)?.notifySelf()
        if (offsetX + 1 < height)   getNeighbour(offsetX + 1, offsetY)?.notifySelf()
        if (offsetY > 0)            getNeighbour(offsetX, offsetY - 1)?.notifySelf()
        if (offsetY + 1 < height)   getNeighbour(offsetX, offsetY + 1)?.notifySelf()

        notifying = false
    }

    fun notifySelf() {
        val origin = getOrigin()
        if (origin == null) {
            val right = if (width > 1) getNeighbour(1, 0) else null
            val below = if (height > 1) getNeighbour(0, 1) else null
            right?.resize(width - 1, 1)
            below?.resize(width, height - 1)
            right?.expand()
            below?.expand()
            return
        }

        for (y in 0 until height) {
            for (x in 0 until width) {
                val monitor = origin.getNeighbour(x, y)
                if (monitor != null) continue

                var above: Screen? = null
                var left: Screen? = null
                var right: Screen? = null
                var below: Screen? = null
                if (y > 0) {
                    above = origin
                    above.resize(width, y)
                }
                if (x > 0) {
                    left = origin.getNeighbour(0, y)
                    left?.resize(x, 1)
                }
                if (x + 1 < width) {
                    right = origin.getNeighbour(x + 1, y)
                    right?.resize(width - (x + 1), 1)
                }
                if (y + 1 < height) {
                    below = origin.getNeighbour(0, y + 1)
                    below?.resize(width, height - (y + 1))
                }
                above?.expand()
                left?.expand()
                right?.expand()
                below?.expand()
                return
            }
        }
    }

    private fun mergeLeft(): Boolean {
        val left = getNeighbour(-1, 0)
        if (left == null || left.offsetY != 0 || left.height != height) return false
        val width = left.width + width

        if (width > ConfigLoader.getConfig()!!.multiBlocks.screenWidth) return false
        left.getOrigin()?.resize(width, height)
        left.expand()
        return true
    }

    private fun mergeRight(): Boolean {
        val right = getNeighbour(width, 0)
        if (right == null || right.offsetY != 0 || right.height != height) return false
        val width = width + right.width

        if (width > ConfigLoader.getConfig()!!.multiBlocks.screenWidth) return false
        getOrigin()?.resize(width, height)
        expand()
        return true
    }

    private fun mergeUp(): Boolean {
        val above = getNeighbour(0, height)
        if (above == null || above.offsetX != 0 || above.width != width) return false
        val height: Int = above.height + height
        if (height > ConfigLoader.getConfig()?.multiBlocks!!.screenHeight) return false
        getOrigin()?.resize(width, height)
        expand()
        return true
    }

    private fun mergeDown(): Boolean {
        val below= getNeighbour(0, -1)
        if (below == null || below.offsetX != 0 || below.width != width) return false
        val height = height + below.height
        if (height > ConfigLoader.getConfig()?.multiBlocks!!.screenHeight) return false
        below.getOrigin()?.resize(width, height)
        below.expand()
        return true
    }

    fun expand() {
        while (mergeLeft() || mergeRight() || mergeUp() || mergeDown()) { }
    }
    private fun resize(width: Int, height: Int) {
        if (offsetX != 0 || offsetY != 0) textBuffer = null
        offsetX = 0
        offsetY = 0
        this.width = width
        this.height = height

        textBuffer = if(textBuffer == null) TextBuffer() else null


    //  textBuffer?.setResolution()

        for (x in 0 until width) {
            for (y in 0 until height) {
                val monitor = getNeighbour(x, y) ?: continue
                monitor.offsetX = x
                monitor.offsetY = y
                monitor.width = width
                monitor.height = height
                monitor.textBuffer = textBuffer
                monitor.updateBlockState()
                monitor.updateBlock()
            }
        }
    }
    override fun toTag(tag: CompoundTag): CompoundTag {
        super.toTag(tag)
        if(address != null) tag.putUuid("address", address)
        tag.putInt("width", width)
        tag.putInt("height", height)
        tag.putInt("offsetX", offsetX)
        tag.putInt("offsetY", offsetY)
        tag.putInt("screenConnect", connectedAt?: 0)
        return tag
    }

    override fun fromTag(state: BlockState?, tag: CompoundTag) {
        super.fromTag(state,tag)
//        address = tag.getUuid("address")

        val oldOffsetX = offsetX
        val oldOffsetY = offsetY
        val oldWidth = width
        val oldHeight = height

        width = tag.getInt("width")
        height = tag.getInt("height")
        offsetX = tag.getInt("offsetX")
        offsetY = tag.getInt("offsetY")
        connectedAt = tag.getInt("screenConnect")

        if (offsetX != offsetX || offsetY != offsetY) {
        //  if (oldOffsetX == 0 && oldOffsetY == 0 && textBuffer != null) textBuffer.destroy()
            textBuffer = null
        }

        if (offsetX == 0 && offsetY == 0 && textBuffer == null)
            textBuffer = TextBuffer()

        if (oldOffsetX != offsetX || oldOffsetY != offsetY || oldWidth != width || oldHeight != height)
            updateBlock()

    }

    fun getNeighbour(x: Int, y: Int): Screen? {

        val xOffset = -offsetX + x
        val yOffset = -offsetY + y
        try {
            return getSimilarMonitorAt(pos.offset(getRight(), xOffset).offset(getDown(), yOffset))
        } catch(e: NullPointerException) {
            return null
        }
    }

    private fun getOrigin(): Screen? { return getNeighbour(0, 0) }

    private fun getSimilarMonitorAt(pos: BlockPos): Screen? {
        if (pos == getPos()) return this
        val world = getWorld()
        if (world == null || !world.isRegionLoaded(pos, pos)) return null
        val tile: Screen?;
        try {
            tile = world.getBlockEntity(pos) as? Screen
        } catch (e: StackOverflowError) {
            return null
        }
        val monitor = tile as Screen
        return if (
            !monitor.notifying
            && !monitor.removeState
            && tier == monitor.tier
            && getBlockYaw() === monitor.getBlockYaw()
            && getBlockPitch() === monitor.getBlockPitch()
        ) monitor else null
    }

    private fun updateBlockState() {
        connectedAt = Flags.from(offsetY < height - 1, offsetY > 0, offsetX > 0, offsetX < width - 1)
    }

    private fun updateBlock() {
        markDirty()
        if(world == null) return;
        val state: BlockState = world!!.getBlockState(pos)
        world!!.updateListeners(pos, state, state, 3)
    }

    fun getBlockYaw(): Direction {
        val state: BlockState = world!!.getBlockState(pos)
        return state.get(States.Yaw) ?: Direction.NORTH
    }

    fun getBlockPitch(): Direction {
        val state: BlockState = world!!.getBlockState(pos)
        return state.get(States.Pitch) ?: Direction.NORTH
    }

    fun getRight(): Direction? {
        return getBlockYaw().rotateYCounterclockwise()
    }

    fun getDown(): Direction? {
        if (getBlockPitch() == Direction.NORTH) return Direction.UP
        return if (getBlockPitch() == Direction.DOWN) getBlockYaw() else getBlockYaw().opposite
    }

}