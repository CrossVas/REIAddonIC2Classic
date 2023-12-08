package dev.crossvas.ic2rei.handlers;

import dev.crossvas.ic2rei.IC2REIPlugin;
import ic2.core.IC2;
import ic2.core.item.inv.container.CraftingUpgradeContainer;
import ic2.core.networking.buffers.data.NBTBuffer;
import ic2.core.platform.registries.IC2Items;
import ic2.core.utils.collection.CollectionUtils;
import ic2.core.utils.helpers.StackUtil;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import me.shedaniel.rei.api.client.registry.transfer.TransferHandler;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.plugin.common.BuiltinPlugin;
import me.shedaniel.rei.plugin.common.displays.crafting.DefaultCraftingDisplay;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.ItemStack;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CraftingUpgradeTransferHandler implements TransferHandler {

    @Override
    public Result handle(Context context) {
        Display display = context.getDisplay();
        if (display.getCategoryIdentifier() == BuiltinPlugin.CRAFTING) {
            if (context.getMenu() instanceof CraftingUpgradeContainer) {
                final List<EntryIngredient> ingredients = ((DefaultCraftingDisplay<?>) display).getOrganisedInputEntries(3, 3);
                Map<ItemStack, IntList> slots = CollectionUtils.createLinkedMap();

                for (int i = 0; i < 9; i++) {
                    List<ItemStack> stacks = IC2REIPlugin.convertIngredientToItemStacks(ingredients.get(i));
                    for (ItemStack stack : stacks) {
                        this.getSlot(slots, stack).add(i);
                    }
                }

                ListTag list = new ListTag();
                for (Map.Entry<ItemStack, IntList> entry : slots.entrySet()) {
                    CompoundTag data = entry.getKey().save(new CompoundTag());
                    data.putIntArray("slots", entry.getValue().toIntArray());
                    list.add(data);
                }
                if (context.isActuallyCrafting()) {
                    // jei id is whitelisted
                    IC2.NETWORKING.get(false).sendClientItemBuffer(new ItemStack(IC2Items.CRAFTING_UPGRADE), "jei", new NBTBuffer("RecipeItems", list));
                }
                return Result.createSuccessful().blocksFurtherHandling();
            }
        }
        return Result.createNotApplicable();
    }

    public IntList getSlot(Map<ItemStack, IntList> slots, ItemStack stack) {
        Iterator<Map.Entry<ItemStack, IntList>> entryIterator = slots.entrySet().iterator();

        Map.Entry<ItemStack, IntList> entry;
        do {
            if (!entryIterator.hasNext()) {
                IntList set = new IntArrayList();
                slots.put(stack, set);
                return set;
            }

            entry = entryIterator.next();
        } while(!StackUtil.isStackEqual(entry.getKey(), stack) || entry.getKey().getCount() != stack.getCount());

        return entry.getValue();
    }
}
