package li.cli.oc

import li.cli.oc.blocks.Case
import li.cli.oc.blocks.CaseEntity
import li.cli.oc.blocks.Screen
import li.cli.oc.items.gadgets.Analyzer
import li.cli.oc.items.gadgets.commons.ComponentBlockItem
import li.cli.oc.items.gadgets.commons.ComponentItem
import net.minecraft.block.Block
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object Components {

    var caseEntityType: BlockEntityType<*>? = null

    enum class Blocks(val id: String, val block: Block) {
        ScreenOne("screen1", Screen(1)),
        ScreenTwo("screen2", Screen(2)),
        ScreenThree("screen3", Screen(3)),

        CaseOne("case1", Case(1)),
        CaseTwo("case2", Case(2)),
        CaseThree("case3", Case(3)),
        CaseCreative("casecreative", Case(4))
    }

    enum class Items(val id: String, val item: Item) {

        ScreenOne("screen1", ComponentBlockItem(Blocks.ScreenOne.block)),
        ScreenTwo("screen2", ComponentBlockItem(Blocks.ScreenTwo.block)),
        ScreenThree("screen3", ComponentBlockItem(Blocks.ScreenThree.block)),

        CaseOne("case1", ComponentBlockItem(Blocks.CaseOne.block)),
        CaseTwo("case2", ComponentBlockItem(Blocks.CaseTwo.block)),
        CaseThree("case3", ComponentBlockItem(Blocks.CaseThree.block)),
        CaseCreative("casecreative", ComponentBlockItem(Blocks.CaseCreative.block)),

        Cpu1("cpu1",ComponentItem()),
        Cpu2("cpu2",ComponentItem()),
        Cpu3("cpu3",ComponentItem()),

        Apu1("apu1", ComponentItem()),
        Apu2("apu2", ComponentItem()),
        Apucreative("apucreative", ComponentItem()),

        Ram1("ram1", ComponentItem()),
        Ram2("ram2", ComponentItem()),
        Ram3("ram3", ComponentItem()),
        Ram4("ram4", ComponentItem()),
        Ram5("ram5", ComponentItem()),
        Ram6("ram6", ComponentItem()),
        Hdd1("hdd1", ComponentItem()),
        Hdd2("hdd2", ComponentItem()),
        Hdd3("hdd3", ComponentItem()),
        Componentbus1("componentbus1", ComponentItem()),
        Componentbus2("componentbus2", ComponentItem()),
        Componentbus3("componentbus3", ComponentItem()),

        Analyzer("analyzer", Analyzer()),
        Transistor("transistor", ComponentItem()),
        Microchip1("microchip1", ComponentItem()),
        Microchip2("microchip2", ComponentItem()),
        Microchip3("microchip3", ComponentItem()),
        Cuttingwire("cuttingwire", ComponentItem()),
        Grog("grog", ComponentItem()),
        Rawcircuitboard("rawcircuitboard", ComponentItem()),
        Circuitboard("circuitboard", ComponentItem()),
        Printedcircuitboard("printedcircuitboard", ComponentItem()),
        Arithmeticlogicunit("arithmeticlogicunit", ComponentItem()),
        Controlunit("controlunit", ComponentItem()),
        Disk("disk", ComponentItem()),
        Interweb("interweb", ComponentItem()),
        Buttongroup("buttongroup", ComponentItem()),
        Arrowkeys("arrowkeys", ComponentItem()),
        Numpad("numpad", ComponentItem()),
        Inkcartridge("inkcartridge", ComponentItem()),
        Inkcartridgeempty("inkcartridgeempty", ComponentItem()),
        Chamelium("chamelium", ComponentItem()),
        Card("card", ComponentItem()),
        Redstonecard1("redstonecard1", ComponentItem()),
        Redstonecard2("redstonecard2", ComponentItem()),
        Linkedcard("linkedcard", ComponentItem()),
        Lancard("lancard", ComponentItem()),
        Wlancard1("wlancard1", ComponentItem()),
        Wlancard2("wlancard2", ComponentItem()),
        Internetcard("internetcard", ComponentItem()),
        Debugcard("debugcard", ComponentItem()),
        Graphicscard1("graphicscard1", ComponentItem()),
        Graphicscard2("graphicscard2", ComponentItem()),
        Graphicscard3("graphicscard3", ComponentItem()),
        Datacard1("datacard1", ComponentItem()),
        Datacard2("datacard2", ComponentItem()),
        Datacard3("datacard3", ComponentItem()),
        Angelupgrade("angelupgrade", ComponentItem()),
        Inventorycontrollerupgrade("inventorycontrollerupgrade", ComponentItem()),
        Inventoryupgrade("inventoryupgrade", ComponentItem()),
        Leashupgrade("leashupgrade", ComponentItem()),
        Signupgrade("signupgrade", ComponentItem()),
        Solargeneratorupgrade("solargeneratorupgrade", ComponentItem()),
        Tankcontrollerupgrade("tankcontrollerupgrade", ComponentItem()),
        Tankupgrade("tankupgrade", ComponentItem()),
        Tractorbeamupgrade("tractorbeamupgrade", ComponentItem()),
        Tradingupgrade("tradingupgrade", ComponentItem()),
        Navigationupgrade("navigationupgrade", ComponentItem()),
        Pistonupgrade("pistonupgrade", ComponentItem()),
        Chunkloaderupgrade("chunkloaderupgrade", ComponentItem()),
        Craftingupgrade("craftingupgrade", ComponentItem()),
        Experienceupgrade("experienceupgrade", ComponentItem()),
        Generatorupgrade("generatorupgrade", ComponentItem()),
        Hoverupgrade1("hoverupgrade1", ComponentItem()),
        Hoverupgrade2("hoverupgrade2", ComponentItem()),
        Databaseupgrade1("databaseupgrade1", ComponentItem()),
        Databaseupgrade2("databaseupgrade2", ComponentItem()),
        Databaseupgrade3("databaseupgrade3", ComponentItem()),
        Batteryupgrade1("batteryupgrade1", ComponentItem()),
        Batteryupgrade2("batteryupgrade2", ComponentItem()),
        Batteryupgrade3("batteryupgrade3", ComponentItem()),
        Upgradecontainer1("upgradecontainer1", ComponentItem()),
        Upgradecontainer2("upgradecontainer2", ComponentItem()),
        Upgradecontainer3("upgradecontainer3", ComponentItem())
    }

    fun registerComponents() {

        caseEntityType = Registry.register(Registry.BLOCK_ENTITY_TYPE, Identifier(OpenComputers.modId, "case"),
            BlockEntityType.Builder.create({ CaseEntity() }, Case(4)).build(null))

        Blocks.values().iterator().forEach { x ->
            Registry.register(Registry.BLOCK, Identifier(OpenComputers.modId, x.id), x.block)
        }

        Items.values().iterator().forEach { x ->
            Registry.register(Registry.ITEM, Identifier(OpenComputers.modId, x.id), x.item)
        }
    }

}

