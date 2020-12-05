package li.cli.oc.blocks

import li.cli.oc.blocks.commons.TecBlock
import li.cli.oc.blocks.commons.States
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.BlockRenderType
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.util.math.Direction


class Keyboard: TecBlock(FabricBlockSettings.of(Material.METAL).nonOpaque().noCollision()) {

    val pitch = States.Pitch;
    val yaw = States.Yaw;

    init {
        defaultState = (stateManager.defaultState as BlockState)
            .with(pitch, States.Pitches[0])
            .with(yaw, States.Yaws[0])

    }

    override fun getPlacementState(ctx: ItemPlacementContext?): BlockState? {
        var currentpitch = Direction.NORTH;
        if(ctx?.playerLookDirection === Direction.UP || ctx?.playerLookDirection === Direction.DOWN)
            currentpitch = ctx.playerLookDirection.opposite;

        return this.defaultState.with(pitch, currentpitch).with(yaw, ctx?.playerFacing?.opposite);
    }

    override fun emitsRedstonePower(state: BlockState?): Boolean {
        return false
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>?) {
        builder!!.add(States.Pitch, States.Yaw);
    }
}