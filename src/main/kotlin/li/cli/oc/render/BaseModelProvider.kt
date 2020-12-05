package li.cli.oc.render

import net.fabricmc.fabric.api.client.model.ModelProviderContext
import net.fabricmc.fabric.api.client.model.ModelResourceProvider
import net.minecraft.client.render.model.UnbakedModel
import net.minecraft.util.Identifier

class BaseModelProvider(private val tag: Identifier, val model: UnbakedModel): ModelResourceProvider  {

    override fun loadModelResource(identifier: Identifier?, p1: ModelProviderContext?): UnbakedModel? {
        if (identifier!! == tag)
            return model
        return null
    }

}