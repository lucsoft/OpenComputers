package li.cli.oc

import li.cli.oc.blocks.Case
import li.cli.oc.blocks.CaseEntity
import li.cli.oc.items.gadgets.Analyzer
import net.minecraft.block.BlockWithEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object Components {

    var caseEntityType: BlockEntityType<*>? = null
    var caseOne = Case(1);
    var caseTwo = Case(2);
    var caseThree = Case(3);
    var caseFour = Case(4);

    fun registerComponents() {

        caseEntityType = Registry.register(Registry.BLOCK_ENTITY_TYPE, Identifier(OpenComputers.modId, "case"),
            BlockEntityType.Builder.create({ CaseEntity() }, Case(4)).build(null))

        reqBlock("case1", caseOne)
        reqBlock("case2", caseTwo)
        reqBlock("case3", caseThree)
        reqBlock("casecreative", caseFour)

        regItem("cpu1")
        regItem("cpu2")
        regItem("cpu3")

        regItem("apu1")
        regItem("apu2")
        regItem("apucreative")

        regItem("ram1")
        regItem("ram2")
        regItem("ram3")
        regItem("ram4")
        regItem("ram5")
        regItem("ram6")

        regItem("hdd1")
        regItem("hdd2")
        regItem("hdd3")

        regItem("componentbus1")
        regItem("componentbus2")
        regItem("componentbus3")

        Registry.register(Registry.ITEM, Identifier(OpenComputers.modId, "analyzer"), Analyzer())

        regItem("transistor")
        regItem("microchip1")
        regItem("microchip2")
        regItem("microchip3")

        regItem("cuttingwire")
        regItem("grog")
        regItem("rawcircuitboard")
        regItem("circuitboard")
        regItem("printedcircuitboard")
        regItem("arithmeticlogicunit")
        regItem("controlunit")
        regItem("disk")
        regItem("interweb")
        regItem("buttongroup")
        regItem("arrowkeys")
        regItem("numpad")
        regItem("inkcartridge")
        regItem("inkcartridgeempty")
        regItem("chamelium")

        regItem("card")
        regItem("redstonecard1")
        regItem("redstonecard2")
        regItem("linkedcard")
        regItem("lancard")
        regItem("wlancard1")
        regItem("wlancard2")
        regItem("internetcard")
        regItem("debugcard")
        regItem("graphicscard1")
        regItem("graphicscard2")
        regItem("graphicscard3")
        regItem("datacard1")
        regItem("datacard2")
        regItem("datacard3")
        regItem("angelupgrade")
        regItem("inventorycontrollerupgrade")
        regItem("inventoryupgrade")
        regItem("leashupgrade")
        regItem("signupgrade")
        regItem("solargeneratorupgrade")
        regItem("tankcontrollerupgrade")
        regItem("tankupgrade")
        regItem("tractorbeamupgrade")
        regItem("tradingupgrade")
        regItem("navigationupgrade")
        regItem("pistonupgrade")
        regItem("chunkloaderupgrade")
        regItem("craftingupgrade")
        regItem("experienceupgrade")
        regItem("generatorupgrade")
        regItem("hoverupgrade1")
        regItem("hoverupgrade2")
        regItem("databaseupgrade1")
        regItem("databaseupgrade2")
        regItem("databaseupgrade3")
        regItem("batteryupgrade1")
        regItem("batteryupgrade2")
        regItem("batteryupgrade3")
        regItem("upgradecontainer1")
        regItem("upgradecontainer2")
        regItem("upgradecontainer3")

        /**
         * Disabled:
         * abstractbuscard
         * stickypiston
         */
    }

    private fun reqBlock(id: String, block: BlockWithEntity) {
        Registry.register(Registry.BLOCK, Identifier(OpenComputers.modId, id), block);
        Registry.register(Registry.ITEM, Identifier(OpenComputers.modId, id), BlockItem(block, Item.Settings().group(OpenComputers.ITEM_GROUP)))
    }

    private fun regItem(id: String) {
        Registry.register(Registry.ITEM, Identifier(OpenComputers.modId, id),
                Item(Item.Settings().group(OpenComputers.ITEM_GROUP)))
    }
}
