package dev.crossvas.ic2rei.handlers;

import dev.crossvas.ic2rei.IC2REIPlugin;
import ic2.core.IC2;
import ic2.core.block.machines.containers.nv.IndustrialWorkbenchContainer;
import ic2.core.networking.buffers.data.NBTBuffer;
import me.shedaniel.rei.api.client.registry.transfer.TransferHandler;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.plugin.common.BuiltinPlugin;
import me.shedaniel.rei.plugin.common.displays.crafting.DefaultCraftingDisplay;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class IndustrialWorktableTransferHandler implements TransferHandler {

    @Override
    public Result handle(Context context) {
        Display display = context.getDisplay();
        if (display.getCategoryIdentifier() == BuiltinPlugin.CRAFTING) {
            if (context.getMenu() instanceof IndustrialWorkbenchContainer worktable) {
                ListTag inputs = new ListTag();
                final List<EntryIngredient> ingredients = ((DefaultCraftingDisplay<?>) display).getOrganisedInputEntries(3, 3);
                for (int i = 0; i < 9; i++) {
                    CompoundTag nbt = new CompoundTag();
                    nbt.putInt("SlotID", i);
                    EntryIngredient ingredient = ingredients.get(i);
                    List<ItemStack> stacks = IC2REIPlugin.convertIngredientToItemStacks(ingredient);
                    stacks.forEach(stack -> stack.save(nbt));
                    inputs.add(nbt);
                }
                if (context.isActuallyCrafting()) {
                    // jei id is whitelisted
                    IC2.NETWORKING.get(false).sendClientTileDataBufferEvent(worktable.getHolder(), "jei", new NBTBuffer("RecipeData", inputs));
                }
                return Result.createSuccessful().blocksFurtherHandling();
            }
        }
        return Result.createNotApplicable();
    }
}
