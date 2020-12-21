package li.cli.oc.render.block.rotations

import li.cli.oc.render.block.Flags
import net.minecraft.client.texture.Sprite
import net.minecraft.util.math.Direction

class ScreenRotations(val sprites: Array<Sprite?>) {

    object SIDE {
        val front = mapOf(
            Flags.RIGHT or Flags.UP to 1,
            Flags.LEFT or Flags.RIGHT or Flags.UP to 2,
            Flags.LEFT or Flags.UP to 3,
            Flags.LEFT to 4,
            Flags.LEFT or  Flags.RIGHT to 5,
            Flags.RIGHT to 6,
            Flags.RIGHT or Flags.UP or Flags.DOWN to 7,
            Flags.LEFT or Flags.RIGHT or Flags.UP  or Flags.DOWN to 8,
            Flags.LEFT or Flags.UP or Flags.DOWN to 9,
            Flags.RIGHT or Flags.DOWN to 10,
            Flags.LEFT or Flags.RIGHT or Flags.DOWN to 11,
            Flags.LEFT or Flags.DOWN to 12,
            Flags.UP to 13,
            Flags.UP or Flags.DOWN to 14,
            Flags.DOWN to 15
        )

        var horizontal = mapOf(
            Flags.LEFT or Flags.RIGHT to 5,
            Flags.LEFT or Flags.RIGHT or Flags.DOWN to 5,
            Flags.LEFT or Flags.RIGHT or Flags.UP to 5,
            Flags.UP or Flags.DOWN or Flags.LEFT to 5,
            Flags.UP or Flags.DOWN or Flags.RIGHT to 5,
            Flags.RIGHT to 4,
            Flags.RIGHT or Flags.DOWN to 4,
            Flags.RIGHT or Flags.UP to 4,
            Flags.LEFT to 6,
            Flags.LEFT or Flags.DOWN to 6,
            Flags.LEFT or Flags.UP to 6
        )

        var side = mapOf(
            Flags.DOWN or Flags.LEFT to 15,
            Flags.DOWN or Flags.RIGHT to 15,
            Flags.DOWN or Flags.LEFT or Flags.UP to 14,
            Flags.DOWN or Flags.RIGHT or Flags.UP to 14,
            Flags.UP or Flags.LEFT to 13,
            Flags.UP or Flags.RIGHT to 13,
        )
    }

    object UP {
        val up = mapOf(
            Flags.LEFT or Flags.DOWN to 6,
            Flags.LEFT or Flags.DOWN or Flags.RIGHT to 5,
            Flags.RIGHT or Flags.DOWN to 4,
        );
        val down = mapOf(
            Flags.LEFT or Flags.UP to 4,
            Flags.LEFT or Flags.UP or Flags.RIGHT to 5,
            Flags.RIGHT or Flags.UP to 6,
        );
        val right = mapOf(
            Flags.LEFT or Flags.DOWN to 4,
            Flags.LEFT or Flags.DOWN or Flags.UP to 5,
            Flags.LEFT or Flags.UP to 6,
        );
        val left = mapOf(
            Flags.RIGHT or Flags.DOWN to 6,
            Flags.RIGHT or Flags.DOWN or Flags.UP to 5,
            Flags.RIGHT or Flags.UP to 4,
        )
    }

    object DOWN {
        val up = mapOf(
            Flags.UP or Flags.LEFT to 6,
            Flags.UP or Flags.RIGHT to 4,
            Flags.UP or Flags.LEFT or Flags.RIGHT to 5,
        );
        val down = mapOf(
            Flags.DOWN or Flags.LEFT to 4,
            Flags.DOWN or Flags.RIGHT to 6,
            Flags.DOWN or Flags.LEFT or Flags.RIGHT to 5,
        );
        val right = mapOf(
            Flags.LEFT or Flags.DOWN to 6,
            Flags.LEFT or Flags.DOWN or Flags.UP to 5,
            Flags.LEFT or Flags.UP to 4,
        );
        val left = mapOf(
            Flags.RIGHT or Flags.DOWN to 4,
            Flags.RIGHT or Flags.DOWN or Flags.UP to 5,
            Flags.RIGHT or Flags.UP to 6,
        )
    }

    fun getFrontTexture(data: Int?, isFront: Boolean = false): Sprite? {
        return toSprite((SIDE.front[data] ?: 0) + (if (isFront) 16 else 0))
    }

    fun getVerticalHTexture(data: Int?): Sprite? {
        return toSprite(SIDE.horizontal[data] ?: 0)
    }
    fun getVerticalSideTexture(data: Int?): Sprite? {
        return toSprite(SIDE.side[data] ?: 0)
    }

    fun getHorizontalFaceTexture(isUp: Boolean,connectionData: Int?, blockRotation: Direction, face: Direction): Sprite? {
        val up = if(isUp) UP.up else DOWN.up;
        val down = if(isUp) UP.down else DOWN.down;
        val left = if(isUp) UP.left else DOWN.left;
        val right = if(isUp) UP.right else DOWN.right;

        return toSprite(when (blockRotation) {
            face -> down[connectionData]
            face.opposite -> up[connectionData]
            face.rotateYClockwise() -> right[connectionData]
            face.rotateYCounterclockwise() -> left[connectionData]
            else -> 0
        } ?: 0)
    }


    private fun toSprite(id: Int): Sprite? {
        return sprites[id]
    }
}
