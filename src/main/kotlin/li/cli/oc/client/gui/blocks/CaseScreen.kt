package li.cli.oc.client.gui.blocks

import com.mojang.blaze3d.systems.RenderSystem
import li.cli.oc.OpenComputers
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.gui.widget.TexturedButtonWidget
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.ScreenHandler
import net.minecraft.text.Text
import net.minecraft.util.Identifier

class CaseScreen(handler: ScreenHandler?, inventory: PlayerInventory?, title: Text?) : HandledScreen<ScreenHandler?>(
    handler,
    inventory,
    title
) {

    override fun drawBackground(matrices: MatrixStack?, delta: Float, mouseX: Int, mouseY: Int) {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f)
        assert(client != null)
        client!!.textureManager.bindTexture(TEXTURE)
        val x = (width - backgroundWidth) / 2
        val y = (height - backgroundHeight) / 2
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight)

        client!!.textureManager.bindTexture(COMP_TEXTURE)
        drawTexture(matrices, x, y + 1, 0, 0, 256, 256)

        client!!.textureManager.bindTexture(SLOT_TEXTURE)
        val tier = (handler as CaseScreenHandler).tier

        drawTexture(matrices, x + 42, y + 34, 0, 0, 18, 18)

        drawInvSlots(tier!!, x, y) // Tier should almost certainly be set by the time we are drawing the background
    }


    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        renderBackground(matrices)
        super.render(matrices, mouseX, mouseY, delta)
        drawMouseoverTooltip(matrices, mouseX, mouseY)
    }

    override fun init() {
        super.init()
        val x = (width - backgroundWidth) / 2
        val y = (height - backgroundHeight) / 2
        // Center the title
        titleX = 7
        val powerButton = TexturedButtonWidget(
            x + (backgroundWidth / 2) - 18,
            y + 34,
            18,
            18,
            0,
            0,
            0,
            BUTTON_TEXTURE,
            36,
            36
        ) { button ->
            TODO(
                "Computer start/stop here"
            )
        }
        addButton(powerButton)
    }

    private fun drawInvSlots(tier: Int, x: Int, y: Int) {
        var invSlotMatrix = MatrixStack()

        client!!.textureManager.bindTexture(SLOT_TEXTURE)

        val xOffset = 93
        val yOffset = 12

        //drawTexture(invSlotMatrix, x + xOffset, y + yOffset, 0, 0, 18, 18)

        drawTexture(invSlotMatrix, x + xOffset + 3, y + yOffset + 3, 0, 0, 18, 18)
        drawTexture(invSlotMatrix, x + xOffset + 3, y + yOffset + 21, 0, 0, 18, 18)

        drawTexture(invSlotMatrix, x + xOffset + 3 + 18 + 5, y + yOffset + 3, 0, 0, 18, 18)
        drawTexture(invSlotMatrix, x + xOffset + 3 + 18 + 5, y + yOffset + 21, 0, 0, 18, 18)
        drawTexture(invSlotMatrix, x + xOffset + 3 + 18 + 5, y + yOffset + 39, 0, 0, 18, 18)

        drawTexture(invSlotMatrix, x + xOffset + 3 + 36 + 10, y + yOffset + 3, 0, 0, 18, 18)
    }

    companion object {
        private val TEXTURE = Identifier(OpenComputers.modId, "textures/gui/background.png")
        private val COMP_TEXTURE = Identifier(OpenComputers.modId, "textures/gui/computer.png")
        private val BUTTON_TEXTURE = Identifier(OpenComputers.modId, "textures/gui/button_power.png")
        private val SLOT_TEXTURE = Identifier(OpenComputers.modId, "textures/gui/slot.png")
    }

}