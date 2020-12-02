package li.cli.oc.items;

import li.cli.oc.OpenComputers;
import li.cli.oc.items.gadgets.Analyzer;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Components {

    Components() {
    }

    public static void registerComponents() {

        regItem("cpu1");
        regItem("cpu2");
        regItem("cpu3");

        regItem("apu1");
        regItem("apu2");
        regItem("apucreative");

        regItem("ram1");
        regItem("ram2");
        regItem("ram3");
        regItem("ram4");
        regItem("ram5");
        regItem("ram6");

        regItem("hdd1");
        regItem("hdd2");
        regItem("hdd3");

        regItem("componentbus1");
        regItem("componentbus2");
        regItem("componentbus3");

        Registry.register(Registry.ITEM, new Identifier(OpenComputers.modId, "analyzer"), new Analyzer());
        
        regItem("transistor");
        regItem("microchip1");
        regItem("microchip2");
        regItem("microchip3");

        regItem("cuttingwire");
        regItem("grog");
        regItem("rawcircuitboard");
        regItem("circuitboard");
        regItem("printedcircuitboard");
        regItem("arithmeticlogicunit");
        regItem("controlunit");
        regItem("disk");
        regItem("interweb");
        regItem("buttongroup");
        regItem("arrowkeys");
        regItem("numpad");
        regItem("inkcartridge");
        regItem("inkcartridgeempty");
        regItem("chamelium");

        regItem("card");    
        regItem("redstonecard1");
        regItem("redstonecard2");
        regItem("linkedcard");
        regItem("lancard");
        regItem("wlancard1");
        regItem("wlancard2");
        regItem("internetcard");
        regItem("debugcard");
        regItem("graphicscard1");
        regItem("graphicscard2");
        regItem("graphicscard3");
        regItem("datacard1");
        regItem("datacard2");
        regItem("datacard3");
        regItem("angelupgrade");
        regItem("inventorycontrollerupgrade");
        regItem("inventoryupgrade");
        regItem("leashupgrade");
        regItem("signupgrade");
        regItem("solargeneratorupgrade");
        regItem("tankcontrollerupgrade");
        regItem("tankupgrade");
        regItem("tractorbeamupgrade");
        regItem("tradingupgrade");
        regItem("navigationupgrade");
        regItem("pistonupgrade");
        regItem("chunkloaderupgrade");
        regItem("craftingupgrade");
        regItem("experienceupgrade");
        regItem("generatorupgrade");
        regItem("hoverupgrade1");
        regItem("hoverupgrade2");
        regItem("databaseupgrade1");
        regItem("databaseupgrade2");
        regItem("databaseupgrade3");
        regItem("batteryupgrade1");
        regItem("batteryupgrade2");
        regItem("batteryupgrade3");
        regItem("upgradecontainer1");
        regItem("upgradecontainer2");
        regItem("upgradecontainer3");
        
        /**
         * Disabled:
         * abstractbuscard
         * stickypiston
         */
    }

    static void regItem(String id) {
        Registry.register(Registry.ITEM, new Identifier(OpenComputers.modId, id),
                new Item(new Item.Settings().group(OpenComputers.ITEM_GROUP)));
    }
}
