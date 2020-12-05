package li.cli.oc.blocks

import com.mojang.datafixers.util.Pair
import li.cli.oc.OpenComputers
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter
import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.model.*
import net.minecraft.client.render.model.json.ModelOverrideList
import net.minecraft.client.render.model.json.ModelTransformation
import net.minecraft.client.texture.Sprite
import net.minecraft.client.util.SpriteIdentifier
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.BlockRenderView
import java.util.*
import java.util.function.Function
import java.util.function.Supplier
import net.minecraft.client.texture.SpriteAtlasTexture

class CableModel : UnbakedModel, BakedModel, FabricBakedModel {

    private val spriteIds = arrayOf(
        SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, Identifier(OpenComputers.modId,"block/cablecap")),
        SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, Identifier(OpenComputers.modId,"block/cable"))
    )
    private val sprites = arrayOfNulls<Sprite>(2)

    override fun getModelDependencies(): MutableCollection<Identifier> {
        return Collections.emptyList()
    }

    override fun getTextureDependencies(
        unbakedModelGetter: Function<Identifier, UnbakedModel>?,
        unresolvedTextureReferences: MutableSet<Pair<String, String>>?
    ): Collection<SpriteIdentifier> {
        return spriteIds.asList()
    }
    fun generateCable(emitter: QuadEmitter) {
        InnerConnector(emitter, Direction.UP)
        InnerConnector(emitter, Direction.DOWN)
        InnerConnector(emitter, Direction.NORTH)
        InnerConnector(emitter, Direction.EAST)
        InnerConnector(emitter, Direction.WEST)
        InnerConnector(emitter, Direction.SOUTH)
    }
    override fun bake(
        loader: ModelLoader?,
        textureGetter: Function<SpriteIdentifier, Sprite>?,
        rotationContainer: ModelBakeSettings?,
        modelId: Identifier?
    ): BakedModel? {

        for (i in 0..1) {
            sprites[i] = textureGetter?.apply(spriteIds[i])
        }

        return this
    }

    fun SideToDirectionRenderArray(side: Direction): Array<Direction> {
        return when(side) {
            Direction.UP, Direction.DOWN -> arrayOf(Direction.NORTH, Direction.EAST, Direction.WEST, Direction.SOUTH)
            Direction.NORTH, Direction.SOUTH -> arrayOf(Direction.UP, Direction.DOWN, Direction.WEST, Direction.EAST)
            Direction.WEST, Direction.EAST -> arrayOf(Direction.UP, Direction.DOWN, Direction.NORTH, Direction.SOUTH)
        }
    }
    private fun Wire(emitter: QuadEmitter?,
                     side: Direction) {
        when(side) {
            Direction.UP -> {
                for (i in SideToDirectionRenderArray(side)) {
                    emitter?.square(i, 0.375f, 0.375f, 0.625f, 1f, 0.375f)
                    emitter?.spriteBake(0, sprites[1], MutableQuadView.BAKE_LOCK_UV)
                    emitter?.spriteColor(0, -1, -1, -1, -1)
                    emitter?.emit()
                }
            }
            Direction.DOWN -> {
                for (i in SideToDirectionRenderArray(side)) {
                    emitter?.square(i, 0.375f, 0f, 0.625f, 0.625f, 0.375f)
                    emitter?.spriteBake(0, sprites[1], MutableQuadView.BAKE_LOCK_UV)
                    emitter?.spriteColor(0, -1, -1, -1, -1)
                    emitter?.emit()
                }
            }
            Direction.SOUTH -> {
                emitter?.square(Direction.UP, 0.375f, 0f, 0.625f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[1], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
                emitter?.square(Direction.DOWN, .375f, 0.375f, 0.625f, 1f, 0.375f)
                emitter?.spriteBake(0, sprites[1], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
                emitter?.square(Direction.WEST, 0.375f, 0.375f, 1f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[1], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
                emitter?.square(Direction.EAST, 0f, 0.375f, 0.625f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[1], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
            }

            Direction.NORTH -> {
                emitter?.square(Direction.UP, 0.375f, 0.375f, 0.625f, 1f, 0.375f)
                emitter?.spriteBake(0, sprites[1], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
                emitter?.square(Direction.DOWN, .375f, 0f, 0.625f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[1], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
                emitter?.square(Direction.EAST, 0.375f, 0.375f, 1f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[1], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
                emitter?.square(Direction.WEST, 0f, 0.375f, 0.625f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[1], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
            }
            Direction.WEST -> {
                emitter?.square(Direction.DOWN, 0f, 0.375f, 0.625f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[1], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
                emitter?.square(Direction.UP, 0f, 0.375f, 0.625f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[1], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
                emitter?.square(Direction.SOUTH, 0f, 0.375f, 0.625f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[1], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
                emitter?.square(Direction.NORTH, 0.375f, 0.375f, 1f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[1], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
            }
            Direction.EAST -> {
                emitter?.square(Direction.UP, 0.375f, 0.375f, 1f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[1], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
                emitter?.square(Direction.DOWN, .375f, 0.375f, 1f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[1], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
                emitter?.square(Direction.NORTH, 0f, 0.375f, 0.625f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[1], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
                emitter?.square(Direction.SOUTH, 0.375f, 0.375f, 1f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[1], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
            }
        }
    }
    private fun InnerConnector(emitter: QuadEmitter?, side: Direction ) {
        emitter?.square(side, 0.375f, 0.375f, 0.625f, 0.625f, 0.3f)
        emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
        emitter?.spriteColor(0, -1, -1, -1, -1)
        emitter?.emit()
        when(side) {
            Direction.UP -> {
                for (i in SideToDirectionRenderArray(side)) {
                    emitter?.square(i, 0.375f, 0.625f, 0.625f, 0.7f, 0.375f)
                    emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                    emitter?.spriteColor(0, -1, -1, -1, -1)
                    emitter?.emit()
                }
            }
            Direction.DOWN -> {
                for (i in SideToDirectionRenderArray(side)) {
                    emitter?.square(i, 0.375f, 0.3f, 0.625f, 0.375f, 0.375f)
                    emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                    emitter?.spriteColor(0, -1, -1, -1, -1)
                    emitter?.emit()
                }
            }
            Direction.NORTH -> {
                emitter?.square(Direction.UP,  0.375f, 0.625f, 0.625f, 0.7f, 0.375f)
                emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()

                emitter?.square(Direction.DOWN,  0.375f, 0.3f, 0.625f, 0.375f, 0.375f)
                emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()

                emitter?.square(Direction.WEST, 0.30f, 0.375f, 0.375f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()

                emitter?.square(Direction.EAST, 0.625f, 0.375f, 0.7f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
            }
            Direction.EAST -> {
                emitter?.square(Direction.UP,   0.625f, 0.375f, 0.7f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()

                emitter?.square(Direction.DOWN,  0.625f, 0.375f, 0.7f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()

                emitter?.square(Direction.NORTH, 0.30f, 0.375f, 0.375f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()

                emitter?.square(Direction.SOUTH, 0.625f, 0.375f, 0.7f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
            }
            Direction.SOUTH -> {
                emitter?.square(Direction.UP, 0.375f, 0.3f, 0.625f, 0.375f, 0.375f)
                emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()

                emitter?.square(Direction.DOWN,  0.375f, 0.625f, 0.625f, 0.7f, 0.375f)
                emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()

                emitter?.square(Direction.EAST, 0.30f, 0.375f, 0.375f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()

                emitter?.square(Direction.WEST, 0.625f, 0.375f, 0.7f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
            }
            Direction.WEST -> {
                emitter?.square(Direction.UP, 0.30f, 0.375f, 0.375f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()

                emitter?.square(Direction.SOUTH, 0.30f, 0.375f, 0.375f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()

                emitter?.square(Direction.DOWN, 0.30f, 0.375f, 0.375f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()

                emitter?.square(Direction.NORTH, 0.625f, 0.375f, 0.7f, 0.625f, 0.375f)
                emitter?.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV)
                emitter?.spriteColor(0, -1, -1, -1, -1)
                emitter?.emit()
            }
        }
    }
    override fun getQuads(state: BlockState?, face: Direction?, random: Random?): List<BakedQuad>? { return null }

    override fun useAmbientOcclusion(): Boolean { return true }

    override fun hasDepth(): Boolean {
        return false
    }

    override fun isSideLit(): Boolean {
        return false
    }

    override fun isBuiltin(): Boolean {
        return false
    }

    override fun getSprite(): Sprite? {
        return sprites[1]
    }

    override fun getTransformation(): ModelTransformation? {
        return null
    }

    override fun getOverrides(): ModelOverrideList? {
        return null
    }

    override fun isVanillaAdapter(): Boolean {
        return false
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

        if(world.getBlockState(blockPos?.add(0, 1, 0)).block is Cable) {
            connections.add(Direction.UP)
        }
        if(world.getBlockState(blockPos?.add(0, -1, 0)).block is Cable) {
            connections.add(Direction.DOWN)
        }
        if(world.getBlockState(blockPos?.add(0, 0, 1)).block is Cable) {
            connections.add(Direction.SOUTH)
        }
        if(world.getBlockState(blockPos?.add(0, 0, -1)).block is Cable) {
            connections.add(Direction.NORTH)
        }
        if(world.getBlockState(blockPos?.add(1, 0, 0)).block is Cable) {
            connections.add(Direction.EAST)
        }
        if(world.getBlockState(blockPos?.add(-1, 0, 0)).block is Cable) {
            connections.add(Direction.WEST)
        }

        if (connections.isEmpty()) generateCable(emitter)
        else {
            if(connections.size == 1) {
                Wire(emitter, connections[0])
                InnerConnector(emitter, connections[0].opposite)
            } else connections.forEach { x ->
                Wire(emitter, x)
            }
        }
    }

    override fun emitItemQuads(p0: ItemStack?, p1: Supplier<Random>?, p2: RenderContext?) { }

}

class Cable: Block(FabricBlockSettings.of(Material.METAL).nonOpaque().noCollision()) {
}