package li.cli.oc.items.gadgets.commons

import li.cli.oc.OpenComputers
import li.cli.oc.blocks.commons.TecBlock
import net.minecraft.block.Block
import net.minecraft.item.BlockItem

class ComponentBlockItem(val block: Any?) : BlockItem(block as Block, Settings().group(OpenComputers.ITEM_GROUP)) {

    fun getColor(): Int? {
        if(block is TecBlock)
            return block.getColor()
        return null
    }
}
