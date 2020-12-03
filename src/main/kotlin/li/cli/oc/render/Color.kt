package li.cli.oc.render

enum class DyeColor(val colorCode: Int) {
    BLACK(0x444444),
    RED(0xB3312C),
    GREEN(0x339911),
    BROWN(0x51301A),
    BLUE(0x6666FF),
    PURPLE(0x7B2FBE),
    CYAN(0x66FFFF),
    SILVER(0xABABAB),
    GRAY(0x666666),
    PINK(0xD88198),
    LIME(0x66FF66),
    YELLOW(0xFFFF66),
    LIGHT_BLUE(0xAAAAFF),
    MAGENTA(0xC354CD),
    ORANGE(0xEB8844),
    WHITE(0xF0F0F0)
}

object Color {

    fun getTearColors(index: Int): Int {
        return listOf(DyeColor.SILVER.colorCode, DyeColor.YELLOW.colorCode, DyeColor.CYAN.colorCode, DyeColor.MAGENTA.colorCode)[index]
    }
}