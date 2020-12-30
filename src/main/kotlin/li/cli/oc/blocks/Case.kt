package li.cli.oc.blocks;

import li.cli.oc.blockentity.CaseEntity
import li.cli.oc.blocks.commons.TecBlock
import li.cli.oc.render.Color
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.BlockView
import net.minecraft.world.World

class Case(var Tier: Int) : TecBlock(FabricBlockSettings.of(Material.METAL)) {

    override fun getColor(): Int {
        return Color.getTearColors(Tier - 1)
    }

    val facing = HorizontalFacingBlock.FACING
    val running = BooleanProperty.of("running")

    init {
        defaultState = (stateManager.defaultState as BlockState)
                .with(facing, Direction.NORTH)
                .with(running, false)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>?) {
        builder!!.add(HorizontalFacingBlock.FACING, BooleanProperty.of("running"))
    }

    override fun getPlacementState(ctx: ItemPlacementContext?): BlockState? {
        return this.defaultState.with(facing, ctx?.playerFacing?.opposite)
    }

    override fun createBlockEntity(world: BlockView?): BlockEntity? {
        return CaseEntity(Tier);
    }

    override fun getRenderType(state: BlockState?): BlockRenderType {
        return BlockRenderType.MODEL;
    }

    override fun onUse(state: BlockState, world: World, pos: BlockPos?, player: PlayerEntity, hand: Hand?, hit: BlockHitResult?): ActionResult? {
        // Open the case screen if we can
        if (!world.isClient) {
            val screenHandlerFactory = state.createScreenHandlerFactory(world, pos)
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory)
            }
        }
        return ActionResult.SUCCESS
    }
}
