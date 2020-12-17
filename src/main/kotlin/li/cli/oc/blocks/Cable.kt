package li.cli.oc.blocks

import li.cli.oc.OpenComputers
import li.cli.oc.blocks.commons.BakedModelConfig
import li.cli.oc.blocks.commons.TecBlock
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.client.MinecraftClient
import net.minecraft.client.texture.Sprite
import net.minecraft.client.util.SpriteIdentifier
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.BlockRenderView
import java.util.*
import java.util.function.Supplier
import net.minecraft.client.texture.SpriteAtlasTexture
import net.minecraft.util.math.Vec3i

class CableModel : BakedModelConfig() {

    override val spriteIds = arrayOf(
        SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, Identifier(OpenComputers.modId,"block/cable")),
        SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, Identifier(OpenComputers.modId,"block/cablecap"))
    )
    override val sprites: Array<Sprite?> = arrayOfNulls(spriteIds.size)

    private fun generateCable(emitter: QuadEmitter) {
        innerConnector(emitter, Direction.UP)
        innerConnector(emitter, Direction.DOWN)
        innerConnector(emitter, Direction.NORTH)
        innerConnector(emitter, Direction.EAST)
        innerConnector(emitter, Direction.WEST)
        innerConnector(emitter, Direction.SOUTH)
    }

    private fun SideToDirectionRenderArray(side: Direction): Array<Direction> {
        return when(side) {
            Direction.UP, Direction.DOWN -> arrayOf(Direction.NORTH, Direction.EAST, Direction.WEST, Direction.SOUTH)
            Direction.NORTH, Direction.SOUTH -> arrayOf(Direction.UP, Direction.DOWN, Direction.WEST, Direction.EAST)
            Direction.WEST, Direction.EAST -> arrayOf(Direction.UP, Direction.DOWN, Direction.NORTH, Direction.SOUTH)
        }
    }

    private fun wireThing(emitter: QuadEmitter?, side: Direction) {
        when(side) {
            Direction.UP -> {
                for (i in SideToDirectionRenderArray(side)) {
                    emitter?.square(i, 0.375f, 0.375f, 0.625f, 1f, 0.375f)
                    emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                    emitter?.spriteColor(0, -1, -1, -1, -1)
                    emitter?.emit()
                }
            }
            Direction.DOWN -> {
                for (i in SideToDirectionRenderArray(side)) {
                    emitter?.square(i, 0.375f, 0f, 0.625f, 0.625f, 0.375f)
                    emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                    emitter?.spriteColor(0, -1, -1, -1, -1)
                    emitter?.emit()
                }
            }
            Direction.SOUTH -> {
                emitter?.square(Direction.UP, 0.375f, 0f, 0.625f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
                emitter?.square(Direction.DOWN, .375f, 0.375f, 0.625f, 1f, 0.375f)
                emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
                emitter?.square(Direction.WEST, 0.375f, 0.375f, 1f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
                emitter?.square(Direction.EAST, 0f, 0.375f, 0.625f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
            }

            Direction.NORTH -> {
                emitter?.square(Direction.UP, 0.375f, 0.375f, 0.625f, 1f, 0.375f)
                emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
                emitter?.square(Direction.DOWN, .375f, 0f, 0.625f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
                emitter?.square(Direction.EAST, 0.375f, 0.375f, 1f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
                emitter?.square(Direction.WEST, 0f, 0.375f, 0.625f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
            }
            Direction.WEST -> {
                emitter?.square(Direction.DOWN, 0f, 0.375f, 0.625f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
                emitter?.square(Direction.UP, 0f, 0.375f, 0.625f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
                emitter?.square(Direction.SOUTH, 0f, 0.375f, 0.625f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
                emitter?.square(Direction.NORTH, 0.375f, 0.375f, 1f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
            }
            Direction.EAST -> {
                emitter?.square(Direction.UP, 0.375f, 0.375f, 1f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
                emitter?.square(Direction.DOWN, .375f, 0.375f, 1f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
                emitter?.square(Direction.NORTH, 0f, 0.375f, 0.625f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
                emitter?.square(Direction.SOUTH, 0.375f, 0.375f, 1f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
            }
        }
    }
    private fun innerConnector(emitter: QuadEmitter?, side: Direction ) {
        renderSprite(emitter, side, sprites[1],0.375f, 0.375f, 0.625f, 0.625f, 0.3f)
        when(side) {
            Direction.UP -> {
                for (i in SideToDirectionRenderArray(side)) {
                    renderSprite(emitter, i, sprites[1],0.375f, 0.625f, 0.625f, 0.7f, 0.375f)
                }
            }
            Direction.DOWN -> {
                for (i in SideToDirectionRenderArray(side)) {
                    renderSprite(emitter, i, sprites[1],0.375f, 0.3f, 0.625f, 0.375f, 0.375f)
                }
            }
            Direction.NORTH -> {
                renderSprite(emitter, Direction.UP, sprites[1],0.375f, 0.625f, 0.625f, 0.7f, 0.375f)
                renderSprite(emitter, Direction.DOWN, sprites[1],0.375f, 0.3f, 0.625f, 0.375f, 0.375f)
                renderSprite(emitter, Direction.WEST, sprites[1],0.30f, 0.375f, 0.375f, 0.625f, 0.375f)
                renderSprite(emitter, Direction.EAST, sprites[1],0.625f, 0.375f, 0.7f, 0.625f, 0.375f)
            }
            Direction.EAST -> {
                renderSprite(emitter, Direction.UP, sprites[1],0.625f, 0.375f, 0.7f, 0.625f, 0.375f)
                renderSprite(emitter, Direction.DOWN, sprites[1],0.625f, 0.375f, 0.7f, 0.625f, 0.375f)
                renderSprite(emitter, Direction.NORTH, sprites[1],0.30f, 0.375f, 0.375f, 0.625f, 0.375f)
                renderSprite(emitter, Direction.SOUTH, sprites[1],0.625f, 0.375f, 0.7f, 0.625f, 0.375f)
            }
            Direction.SOUTH -> {
                renderSprite(emitter, Direction.UP, sprites[1],0.375f, 0.3f, 0.625f, 0.375f, 0.375f)
                renderSprite(emitter, Direction.DOWN, sprites[1],0.375f, 0.625f, 0.625f, 0.7f, 0.375f)
                renderSprite(emitter, Direction.EAST, sprites[1],0.30f, 0.375f, 0.375f, 0.625f, 0.375f)
                renderSprite(emitter, Direction.WEST, sprites[1],0.625f, 0.375f, 0.7f, 0.625f, 0.375f)
            }
            Direction.WEST -> {
                renderSprite(emitter, Direction.UP, sprites[1],0.30f, 0.375f, 0.375f, 0.625f, 0.375f)
                renderSprite(emitter, Direction.DOWN, sprites[1],0.30f, 0.375f, 0.375f, 0.625f, 0.375f)
                renderSprite(emitter, Direction.SOUTH, sprites[1],0.30f, 0.375f, 0.375f, 0.625f, 0.375f)
                renderSprite(emitter, Direction.NORTH, sprites[1],0.625f, 0.375f, 0.7f, 0.625f, 0.375f)
            }
        }
    }

    override fun emitBlockQuads(
        blockRenderView: BlockRenderView?,
        blockState: BlockState?,
        blockPos: BlockPos?,
        supplier: Supplier<Random>?,
        renderContext: RenderContext?
    ) {
        val emitter = renderContext?.emitter!!

        val connections = mutableListOf<Direction>()
        val world = MinecraftClient.getInstance().world!!;

        var checkForCable = listOf(
            Vec3i(0,-1,0), Vec3i(0,1,0),
            Vec3i(0,0,-1), Vec3i(0,0,1),
            Vec3i(-1,0,0), Vec3i(1,0,0)
        )

        checkForCable.forEachIndexed { index, vec3i ->
            val block = world.getBlockState(blockPos?.add(vec3i)).block
            if(block is Cable)
                connections.add(Direction.byId(index))
            else if(block is TecBlock) {
                connections.add(Direction.byId(index))
            }
        }

        if (connections.isEmpty()) generateCable(emitter)
        else {
            if(connections.size == 1) {
                wireThing(emitter, connections[0])
                innerConnector(emitter, connections[0].opposite)
            } else connections.forEach { x ->
                wireThing(emitter, x)
            }
        }
    }

    override fun emitItemQuads(p0: ItemStack?, p1: Supplier<Random>?, p2: RenderContext?) { }
}

class Cable: Block(FabricBlockSettings.of(Material.METAL).nonOpaque().noCollision())