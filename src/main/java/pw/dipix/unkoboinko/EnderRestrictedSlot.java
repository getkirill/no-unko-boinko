package pw.dipix.unkoboinko;

import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class EnderRestrictedSlot extends Slot {
    public EnderRestrictedSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    public EnderRestrictedSlot(Slot slot) {
        this(slot.inventory, slot.getIndex(), slot.x, slot.y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        if (stack.isIn(UnkoBoinko.INSTANCE.getRestrictedEnderChest())) return false;
        return super.canInsert(stack);
    }
}
