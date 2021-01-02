package li.cli.oc.blocks

import li.cli.oc.blockentity.Waypoint
import li.cli.oc.blocks.commons.States
import li.cli.oc.blocks.commons.TecBlock
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.block.entity.BlockEntity
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.util.math.Direction
import net.minecraft.world.BlockView


class Waypoint: TecBlock(FabricBlockSettings.of(Material.METAL).hardness(2.5f).resistance(2.5f)) {

    val pitch = States.Pitch;
    val yaw = States.Yaw;

    override fun emitsRedstonePower(state: BlockState?): Boolean {
        return false
    }

    init {
        defaultState = (stateManager.defaultState as BlockState)
            .with(pitch, States.Pitches[0])
            .with(yaw, States.Yaws[0])
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>?) {
        builder!!.add(States.Pitch, States.Yaw);
    }

    override fun getPlacementState(ctx: ItemPlacementContext?): BlockState? {
        var currentpitch = Direction.NORTH;
        if(ctx?.playerLookDirection === Direction.UP || ctx?.playerLookDirection === Direction.DOWN)
            currentpitch = ctx.playerLookDirection.opposite;

        return this.defaultState.with(pitch, currentpitch).with(yaw, ctx?.playerFacing?.opposite);
    }

    override fun createBlockEntity(world: BlockView?): BlockEntity? {
        return Waypoint()
    }
}