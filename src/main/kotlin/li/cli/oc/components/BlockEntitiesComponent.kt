package li.cli.oc.components

import li.cli.oc.Components.Blocks
import li.cli.oc.blockentity.*
import li.cli.oc.blocks.Case
import net.minecraft.block.Block
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import java.util.function.Supplier

enum class BlockEntitiesComponent(val id: String, val entityType: BlockEntityType<BlockEntity>) {
    Adapter             ("adapter",             makeType({ Adapter() }, Blocks.Adapter)),
    Assembler           ("assembler",           makeType({ Assembler()}, Blocks.Assembler)),
    Cable               ("cable",               makeType({ Cable() }, Blocks.Cable)),
    Capacitor           ("capacitor",           makeType({ Capacitor() }, Blocks.Capacitor)),
    CarpatedCapacitor   ("carpetedcapacitor",   makeType({ CarpetedCapacitor() }, Blocks.CarpetedCapacitor)),
    Case                ("case",                makeType({ CaseEntity()}, Case(4))),
    Charger             ("charger",             makeType({ Charger()}, Blocks.Charger)),
    Disassembler        ("disassembler",        makeType({ Disassembler() }, Blocks.Disassembler)),
    DiskDrive           ("diskdrive",           makeType({ DiskDrive() }, Blocks.DiskDrive)),
    Hologram            ("hologram",            makeType({ Hologram() }, li.cli.oc.blocks.Hologram())),
    Keyboard            ("keyboard",            makeType({ Keyboard() }, Blocks.Keyboard)),
    MotionSensor        ("motionsensor",        makeType({ MotionSensor() }, Blocks.MotionSensor)),
    Netsplitter         ("netsplitter",         makeType({ Netsplitter() }, Blocks.Netsplitter)),
    PowerConverter      ("powerconverter",      makeType({ PowerConverter() }, Blocks.PowerConverter)),
    PowerDistributor    ("powerdistributor",    makeType({ PowerDistributor() }, Blocks.PowerDistributor)),
    Printer             ("printer",             makeType({ Printer() }, Blocks.Printer)),
    Rack                ("rack",                makeType({ Rack() }, Blocks.Rack)),
    Raid                ("raid",                makeType({ Raid() }, Blocks.Raid)),
    RedstoneIO          ("redstone",            makeType({ RedstoneIO() }, Blocks.RedstoneIO)),
    Relay               ("relay",               makeType({ Relay() }, Blocks.Relay)),
    ScreenOne           ("screen1",             makeType({ Screen(1) }, Blocks.ScreenOne)),
    ScreenTwo           ("screen2",             makeType({ Screen(2) }, Blocks.ScreenTwo)),
    ScreenThree         ("screen3",             makeType({ Screen(3) }, Blocks.ScreenThree)),
    Transposer          ("transposer",          makeType({ Transposer()}, Blocks.Transposer)),
    Waypoint            ("waypoint",            makeType({ Waypoint()}, Blocks.Waypoint)),
}

private fun makeType(entity:Supplier<BlockEntity>, block: Blocks): BlockEntityType<BlockEntity> {
    return makeType(entity, block.block)
}
private fun makeType(entity: Supplier<BlockEntity>, block: Block): BlockEntityType<BlockEntity> {
    return BlockEntityType.Builder.create(entity, block).build(null)
}