package li.cli.oc

import net.fabricmc.loader.api.FabricLoader
import java.io.File
import java.io.IOException

import java.io.FileWriter

import java.io.FileNotFoundException

import java.io.FileReader

import java.io.BufferedReader

object ConfigLoader {

    private var file: File? = null;
    private var config: Config? = null;

    fun prepareConfigFile() {
        if (file != null)
            return;
        file = File(FabricLoader.getInstance().configDir.toString(), OpenComputers.modId + ".json")
    }

    fun initializeConfig(): Config {
        if (config != null) return config!!;

        config = Config();
        load();

        return config!!;
    }
    private fun load() {
        prepareConfigFile()
        try {
            if (file?.exists() == false) {
                save()
            }
            if (file?.exists() == true) {
                val br = BufferedReader(FileReader(file))
                val parsed = OpenComputers.GSON.fromJson(br, Config::class.java)
                if (parsed != null) {
                    config = parsed
                }
            }
        } catch (e: FileNotFoundException) {
            System.err.println("Couldn't load OpenComputers configuration file; reverting to defaults")
            e.printStackTrace()
        }
    }

    fun save() {
        prepareConfigFile()
        val jsonString = OpenComputers.GSON.toJson(config)
        try {
            FileWriter(file).use { fileWriter -> fileWriter.write(jsonString) }
        } catch (e: IOException) {
            System.err.println("Couldn't save OpenComputers configuration file")
            e.printStackTrace()
        }
    }

    fun getConfig(): Config {
        return config ?: Config()
    }
}

data class EnergieCost(val screen: Double = 0.05)

data class MultiBlocks(
    val screenHeight: Int = 6,
    val screenWidth: Int = 8
)

data class Config(
    val energieCost: EnergieCost = EnergieCost(),
    val multiBlocks: MultiBlocks = MultiBlocks()
)

