package li.cli.oc.gui;

enum class GuiCategory {
    Block,
    Entity,
    Item
}

data class GuiType(val name: String, val category: GuiCategory)