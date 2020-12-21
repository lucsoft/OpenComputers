package li.cli.oc.blocks.commons

import com.mojang.datafixers.util.Pair
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter
import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel
import net.minecraft.block.BlockState
import net.minecraft.client.render.model.*
import net.minecraft.client.render.model.json.ModelOverrideList
import net.minecraft.client.render.model.json.ModelTransformation
import net.minecraft.client.texture.Sprite
import net.minecraft.client.util.SpriteIdentifier
import net.minecraft.client.util.math.Vector4f
import net.minecraft.util.Identifier
import net.minecraft.util.math.Direction
import java.util.*
import java.util.function.Function

abstract class BakedModelConfig: UnbakedModel, BakedModel, FabricBakedModel {
    abstract val spriteIds: Array<SpriteIdentifier>
    abstract val sprites: Array<Sprite?>

    override fun getModelDependencies(): MutableCollection<Identifier> {
        return Collections.emptyList()
    }

    override fun getTextureDependencies(
        unbakedModelGetter: Function<Identifier, UnbakedModel>?,
        unresolvedTextureReferences: MutableSet<Pair<String, String>>?
    ): Collection<SpriteIdentifier> {
        return spriteIds.asList()
    }
    fun renderSprite(
        emitter: QuadEmitter?,
        direction: Direction,
        texture: Sprite?,
        vector: Vector4f,
        customRotation: Int = MutableQuadView.BAKE_LOCK_UV,
        color: Int = -1
    ){
        renderSprite(emitter,
                direction,
                texture,
            vector.x,
            vector.y,
            vector.z,
                vector.w,
                0.0f,
                customRotation,
                color)
    }

    fun renderSprite(
        emitter: QuadEmitter?,
        direction: Direction,
        texture: Sprite?,
        left: Float,
        bottom: Float,
        right: Float,
        top: Float,
        depth: Float,
        customRotation: Int = MutableQuadView.BAKE_LOCK_UV,
        color: Int = -1
    ){
        emitter?.square(direction, left, bottom, right, top, depth)
        emitter?.spriteBake(0, texture, customRotation)
        emitter?.spriteColor(0, color,color, color, color)
        emitter?.emit()
    }

    override fun bake(
        loader: ModelLoader?,
        textureGetter: Function<SpriteIdentifier, Sprite>?,
        rotationContainer: ModelBakeSettings?,
        modelId: Identifier?
    ): BakedModel? {

        for (i in spriteIds.indices) {
            sprites[i] = textureGetter?.apply(spriteIds[i])
        }

        return this
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
        return sprites[0]
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
}