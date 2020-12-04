package li.cli.oc.blocks.commons

import net.minecraft.state.property.DirectionProperty
import net.minecraft.util.math.Direction

object States {
    val Pitches = listOf(Direction.DOWN, Direction.UP, Direction.NORTH)
    val Yaws = listOf(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST)
    val Pitch: DirectionProperty = DirectionProperty.of("pitch", Pitches)
    val Yaw: DirectionProperty = DirectionProperty.of("yaw", Yaws)
}