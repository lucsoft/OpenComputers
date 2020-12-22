package li.cli.oc.util

import li.cli.oc.Components.Items as CItems
import li.cli.oc.OpenComputers
import net.minecraft.inventory.CraftingInventory
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.recipe.*
import net.minecraft.util.Identifier
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.registry.Registry
import net.minecraft.world.World

class SimpleCrafting(val grid: Array<Item>, val result: Item, id: String, val rserializer: RecipeSerializer<*>): SpecialCraftingRecipe(Identifier( OpenComputers.modId, id) ) {

    override fun matches(inv: CraftingInventory?, world: World?): Boolean {
        return grid.filterIndexed { i, item -> inv?.getStack(i)?.item?.equals(item) == true
        }.count() == 9
    }

    override fun craft(inv: CraftingInventory?): ItemStack {
        return if (grid.filterIndexed { i, item ->
                inv?.getStack(i)?.item?.equals(item) == true
            }.count() == 9) ItemStack(result) else ItemStack.EMPTY
    }

    override fun fits(width: Int, height: Int): Boolean {
        return width * height >= 2;
    }

    override fun getSerializer(): RecipeSerializer<*> {
        return rserializer;
    }

    override fun isIgnoredInRecipeBook(): Boolean {
        return false
    }

    override fun getPreviewInputs(): DefaultedList<Ingredient> {
        val defaultedList = DefaultedList.of<Ingredient>()
        grid.forEachIndexed { index, item ->
            defaultedList.add(index, Ingredient.ofStacks(ItemStack(item)))
        }
        return defaultedList

    }

    override fun getRecipeKindIcon(): ItemStack {
        return ItemStack(result)
    }

    override fun getOutput(): ItemStack {
        return ItemStack(result);
    }

    override fun getType(): RecipeType<*> {
        return RecipeType.CRAFTING;
    }
}

object CraftingComponents {

//    val WaterPotion: Item = PotionUtil.setPotion(ItemStack(Items.POTION), Potions.WATER).item;

    enum class SpecialCrafting(val crafting: SimpleCrafting) {
//        NavigationUpgrade(SimpleCrafting(
//            arrayOf(
//                Items.GOLD_INGOT,         Items.COMPASS,  Items.GOLD_INGOT,
//                CItems.Microchip2.item,   Items.MAP,      CItems.Microchip2.item,
//                Items.GOLD_INGOT,         WaterPotion,    Items.GOLD_INGOT
//            ), CItems.Navigationupgrade.item, CItems.Navigationupgrade.id, SpecialSerializer.NavigationUpgrade.serializer)
//        )
    }

    enum class SpecialSerializer(val id: String, val serializer: RecipeSerializer<*>) {
//        NavigationUpgrade( CItems.Navigationupgrade.id,SpecialRecipeSerializer { SpecialCrafting.NavigationUpgrade.crafting })
    }

    fun registerSpecialCrafting() {
        SpecialSerializer.values().forEach { x ->
            Registry.register(Registry.RECIPE_SERIALIZER, Identifier(OpenComputers.modId,x.id), x.serializer)
        }
    }
}