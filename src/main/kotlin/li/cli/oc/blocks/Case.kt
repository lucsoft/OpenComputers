package li.cli.oc.blocks;

import li.cli.oc.Components
import li.cli.oc.blocks.commons.TecBlock;
import li.cli.oc.blocks.commons.RedstoneAwareEntity;
import li.cli.oc.render.Color
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.Direction
import net.minecraft.world.BlockView;

class CaseEntity : RedstoneAwareEntity(Components.caseEntityType) {

}

class Case(var Tear: Int) : TecBlock(FabricBlockSettings.of(Material.METAL)) {


    override fun getColor(): Int {
        return Color.getTearColors(Tear - 1);
    }

    val facing = HorizontalFacingBlock.FACING;
    val running = BooleanProperty.of("running");

    init {
        defaultState = (stateManager.defaultState as BlockState)
                .with(facing, Direction.NORTH)
                .with(running, false)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>?) {
        builder!!.add(HorizontalFacingBlock.FACING, BooleanProperty.of("running"));
    }

    override fun getPlacementState(ctx: ItemPlacementContext?): BlockState? {
        return this.defaultState.with(facing, ctx?.playerFacing?.opposite);
    }

    override fun createBlockEntity(world: BlockView?): BlockEntity? {
        return CaseEntity();
    }

    override fun getRenderType(state: BlockState?): BlockRenderType {
        return BlockRenderType.MODEL;
    }
}
