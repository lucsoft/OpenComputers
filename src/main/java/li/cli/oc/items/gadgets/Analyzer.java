package li.cli.oc.items.gadgets;

import li.cli.oc.OpenComputers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Analyzer extends Item {

    public Analyzer() {
        super(new Item.Settings().group(OpenComputers.ITEM_GROUP));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) {
            user.sendMessage(Text.of("Work in progress"), true);
		}
        return new TypedActionResult<>(ActionResult.SUCCESS, (hand==Hand.MAIN_HAND) ? user.getMainHandStack() : user.getOffHandStack());
    }
}
