package li.cli.oc

import java.util.function.Supplier
import li.cli.oc.blocks.*
import li.cli.oc.client.gui.blocks.CaseScreen
import li.cli.oc.client.gui.blocks.CaseScreenHandler
import li.cli.oc.items.Analyzer
import li.cli.oc.items.commons.ComponentBlockItem
import li.cli.oc.items.commons.ComponentItem
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.item.Item
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object Components {

    var caseEntityType: BlockEntityType<*>? = null

    private fun makeType(entity: Supplier<BlockEntity>, block: Block): BlockEntityType<BlockEntity> {
        return BlockEntityType.Builder.create(entity, block).build(null)
    }

    enum class BlockEntities(val id: String, val entityType: BlockEntityType<BlockEntity>) {
        ScreenOne("screen1", makeType({ li.cli.oc.blockentity.Screen(1) }, Blocks.ScreenOne.block)),
        ScreenTwo("screen2", makeType({ li.cli.oc.blockentity.Screen(2) }, Blocks.ScreenTwo.block)),
        ScreenThree("screen3", makeType({ li.cli.oc.blockentity.Screen(3) }, Blocks.ScreenThree.block)),
        Cable("cable", makeType({ li.cli.oc.blockentity.Cable()}, Blocks.Cable.block))
    }

    enum class Blocks(val id: String, val block: Block) {
        Adapter("adapter", Adapter()),
        Assembler("assembler", Assembler()),
        Cable("cable", Cable()),
        Capacitor("capacitor", Capacitor()),
        CarpatedCapacitor("carpetedcapacitor", CarpetedCapacitor()),
        CaseOne("case1", Case(1)),
        CaseTwo("case2", Case(2)),
        CaseThree("case3", Case(3)),
        CaseCreative("casecreative", Case(4)),
        Charger("charger", Charger()),
        Disassembler("disassembler", Disassembler()),
        DiskDrive("diskdrive", DiskDrive()),
        HologramOne("hologram1", Hologram()),
        HologramTweo("hologram2", Hologram()),
        Keyboard("keyboard", Keyboard()),
        MotionSensor("motionsensor", MotionSensor()),
        netsplitter("netsplitter", Netsplitter()),
        PowerConverter("powerconverter", PowerConverter()),
        PowerDistributor("powerdistributor", PowerDistributor()),
        Printer("printer", Printer()),
        Rack("rack", Rack()),
        Raid("raid", Raid()),
        RedstoneIO("redstone", RedstoneIO()),
        Relay("relay", Relay()),
        ScreenOne("screen1", Screen(1)),
        ScreenTwo("screen2", Screen(2)),
        ScreenThree("screen3", Screen(3)),
        Transposer("transposer", Transposer()),
        Waypoint("waypoint", Waypoint()),
    }

    enum class Items(val id: String, val item: Item) {
        Adapter(Blocks.Adapter.id, ComponentBlockItem(Blocks.Adapter.block)),
        Assembler(Blocks.Assembler.id, ComponentBlockItem(Blocks.Assembler.block)),
        Cable(Blocks.Cable.id, ComponentBlockItem(Blocks.Cable.block)),
        Capacitor(Blocks.Capacitor.id, ComponentBlockItem(Blocks.Capacitor.block)),
        CarpatedCapacitor(Blocks.CarpatedCapacitor.id, ComponentBlockItem(Blocks.CarpatedCapacitor.block)),
        CaseOne(Blocks.CaseOne.id, ComponentBlockItem(Blocks.CaseOne.block)),
        CaseTwo(Blocks.CaseTwo.id, ComponentBlockItem(Blocks.CaseTwo.block)),
        CaseThree(Blocks.CaseThree.id, ComponentBlockItem(Blocks.CaseThree.block)),
        CaseCreative(Blocks.CaseCreative.id, ComponentBlockItem(Blocks.CaseCreative.block)),
        Charger(Blocks.Charger.id, ComponentBlockItem(Blocks.Charger.block)),
        Disassembler(Blocks.Disassembler.id, ComponentBlockItem(Blocks.Disassembler.block)),
        DiskDrive(Blocks.DiskDrive.id, ComponentBlockItem(Blocks.DiskDrive.block)),
        HologramOne(Blocks.HologramOne.id, ComponentBlockItem(Blocks.HologramOne.block)),
        HologramTweo(Blocks.HologramTweo.id, ComponentBlockItem(Blocks.HologramTweo.block)),
        Keyboard(Blocks.Keyboard.id, ComponentBlockItem(Blocks.Keyboard.block)),
        MotionSensor(Blocks.MotionSensor.id, ComponentBlockItem(Blocks.MotionSensor.block)),
        netsplitter(Blocks.netsplitter.id, ComponentBlockItem(Blocks.netsplitter.block)),
        PowerConverter(Blocks.PowerConverter.id, ComponentBlockItem(Blocks.PowerConverter.block)),
        PowerDistributer(Blocks.PowerDistributor.id, ComponentBlockItem(Blocks.PowerDistributor.block)),
        Printer(Blocks.Printer.id, ComponentBlockItem(Blocks.Printer.block)),
        Rack(Blocks.Rack.id, ComponentBlockItem(Blocks.Rack.block)),
        Raid(Blocks.Raid.id, ComponentBlockItem(Blocks.Raid.block)),
        RedstoneIO(Blocks.RedstoneIO.id, ComponentBlockItem(Blocks.RedstoneIO.block)),
        Relay(Blocks.Relay.id, ComponentBlockItem(Blocks.Relay.block)),
        ScreenOne(Blocks.ScreenOne.id, ComponentBlockItem(Blocks.ScreenOne.block)),
        ScreenTwo(Blocks.ScreenTwo.id, ComponentBlockItem(Blocks.ScreenTwo.block)),
        ScreenThree(Blocks.ScreenThree.id, ComponentBlockItem(Blocks.ScreenThree.block)),
        Transposer(Blocks.Transposer.id, ComponentBlockItem(Blocks.Transposer.block)),
        Waypoint(Blocks.Waypoint.id, ComponentBlockItem(Blocks.Waypoint.block)),

        Cpu1("cpu1", ComponentItem()),
        Cpu2("cpu2", ComponentItem()),
        Cpu3("cpu3", ComponentItem()),

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
        Diamondnugget("diamond_nugget", ComponentItem()),
        Grog("grog", ComponentItem()),
        Rawcircuitboard("rawcircuitboard", ComponentItem()),
//        Circuitboard("circuitboard", ComponentItem()), unused
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

        BlockEntities.values().iterator().forEach { x ->
            Registry.register(Registry.BLOCK_ENTITY_TYPE, Identifier(OpenComputers.modId, x.id), x.entityType)
        }

        Blocks.values().iterator().forEach { x ->
            Registry.register(Registry.BLOCK, Identifier(OpenComputers.modId, x.id), x.block)
        }

        Items.values().iterator().forEach { x ->
            Registry.register(Registry.ITEM, Identifier(OpenComputers.modId, x.id), x.item)
        }
    }

    val CASE1 = Case(1)
    val CASE_SCREEN_HANDLER: ScreenHandlerType<CaseScreenHandler> = ScreenHandlerRegistry.registerSimple(Identifier(OpenComputers.modId, "case1"), ::CaseScreenHandler)

}
