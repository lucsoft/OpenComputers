package li.cli.oc.blocks

import li.cli.oc.blockentity.Screen
import li.cli.oc.blocks.commons.States
import li.cli.oc.blocks.commons.TecBlock
import li.cli.oc.render.Color
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemStack
import net.minecraft.state.StateManager
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.BlockView
import net.minecraft.world.World

class Screen(var Tier: Int) : TecBlock(FabricBlockSettings.of(Material.METAL).hardness(2.5f).resistance(2.5f)) {

    override fun getColor(): Int {
        return Color.getTearColors(Tier - 1)
    }

    val pitch = States.Pitch
    val yaw = States.Yaw

    init {
        defaultState = (stateManager.defaultState as BlockState)
                .with(pitch, States.Pitches[0])
                .with(yaw, States.Yaws[0])
    }

    override fun createBlockEntity(world: BlockView?): BlockEntity {
        return Screen(Tier)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>?) {
        builder!!.add(States.Pitch, States.Yaw)
    }

    override fun getPlacementState(ctx: ItemPlacementContext?): BlockState? {
        var currentpitch = Direction.NORTH
        if (ctx?.playerLookDirection === Direction.UP || ctx?.playerLookDirection === Direction.DOWN)
            currentpitch = ctx.playerLookDirection.opposite

        return this.defaultState.with(pitch, currentpitch).with(yaw, ctx?.playerFacing?.opposite)
    }

    override fun getRenderType(state: BlockState?): BlockRenderType {
        return BlockRenderType.MODEL
    }

    override fun onBreak(world: World?, pos: BlockPos?, state: BlockState?, player: PlayerEntity?) {
        super.onBreak(world, pos, state, player)
        val entity = world?.getBlockEntity(pos) as? Screen
        entity?.removeState = true
        entity?.notifyOthers()
    }

    override fun onPlaced(
        world: World?,
        pos: BlockPos?,
        state: BlockState?,
        placer: LivingEntity?,
        itemStack: ItemStack?
    ) {
        super.onPlaced(world, pos, state, placer, itemStack)

        val entity = world?.getBlockEntity(pos) as? Screen

        entity?.notifyOthers()
        entity?.notifySelf()
        entity?.expand()
    }

    override fun allowedToBeConnected(state: BlockState?): Array<Direction> {
        val blockPitch = state?.get(pitch) ?: defaultState.get(pitch)
        val blockYaw = state?.get(yaw) ?: defaultState.get(yaw)
        return when (blockPitch) {
            Direction.UP -> Direction.values().filter { x -> x != Direction.UP }.toTypedArray()
            Direction.DOWN -> Direction.values().filter { x -> x != Direction.DOWN }.toTypedArray()
            Direction.NORTH -> Direction.values().filter { x -> x != blockYaw }.toTypedArray()
            else -> arrayOf(Direction.NORTH)
        }
    }
}
