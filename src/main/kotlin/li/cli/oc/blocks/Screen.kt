package li.cli.oc.blocks;

import li.cli.oc.Components
import li.cli.oc.blocks.commons.RedstoneAware;
import li.cli.oc.blocks.commons.RedstoneAwareEntity;
import li.cli.oc.blocks.commons.States
import li.cli.oc.render.Color
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.Direction
import net.minecraft.world.BlockView;

class Screen(var Tear: Int) : RedstoneAware(FabricBlockSettings.of(Material.METAL)) {


    override fun getColor(): Int {
        return Color.getTearColors(Tear - 1);
    }

    val pitch = States.Pitch;
    val yaw = States.Yaw;

    init {
        defaultState = (stateManager.defaultState as BlockState)
                .with(pitch, States.Pitches[0])
                .with(yaw, States.Yaws[0])
    }

    override fun createBlockEntity(world: BlockView?): BlockEntity? {
        return null;
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

    override fun getRenderType(state: BlockState?): BlockRenderType {
        return BlockRenderType.MODEL;
    }
}
