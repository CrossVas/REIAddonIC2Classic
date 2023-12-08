package dev.crossvas.ic2rei.categories;

import dev.crossvas.ic2rei.displays.WoodGassifierDisplay;
import dev.crossvas.ic2rei.utils.CategoryIDs;
import dev.crossvas.ic2rei.utils.GuiHelper;
import dev.crossvas.ic2rei.utils.IGuiHelper;
import ic2.core.platform.registries.IC2Blocks;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class WoodGassifierCategory implements DisplayCategory<WoodGassifierDisplay>, IGuiHelper {

    public static final WoodGassifierCategory INSTANCE = new WoodGassifierCategory();

    @Override
    public CategoryIdentifier<? extends WoodGassifierDisplay> getCategoryIdentifier() {
        return CategoryIDs.WOOD_GASSIFIER;
    }

    @Override
    public Component getTitle() {
        return IC2Blocks.WOOD_GASSIFIER.getName();
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(IC2Blocks.ELECTRIC_WOOD_GASSIFIER);
    }

    @Override
    public List<Widget> setupDisplay(WoodGassifierDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        GuiHelper.createRecipeBase(widgets, bounds);
        Point progress = point(bounds.getMinX() + offset + slotSize + innerOffset, getCenterY(bounds));
        GuiHelper.addProgressBar(widgets, point(progress.getX(), progress.getY()), 3000, GuiHelper.ProgressType.SMELTER);
        // input
        GuiHelper.addInputSlot(widgets, point(bounds.getMinX() + offset + 2, adjustedInputPoint(bounds).getY()), EntryIngredients.ofItemStacks(display.getRecipe().getInputs()));
        // out
        Point output = point(bounds.getMaxX() - offset - 2 * slotSize - 2 * innerOffset - 1, adjustedInputPoint(bounds).getY());
        GuiHelper.addLargeOutputSlot(widgets, output, EntryIngredients.of(display.getRecipe().getOutput()));
        // tank
        EntryIngredient fluidEntry = EntryIngredients.of(display.getRecipe().getFluidOutput());
        GuiHelper.addTank(widgets, bounds.getMaxX() - offset - slotSize - 1, bounds.getMinY() + offset + 1, fluidEntry);
        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 72;
    }

    @Override
    public int getDisplayWidth(WoodGassifierDisplay display) {
        return 105;
    }

    public static List<ItemStack> getLogList() {
        List<ItemStack> logs = new ObjectArrayList<>();
        ForgeRegistries.ITEMS.forEach(item -> {
            ItemStack stack = new ItemStack(item);
            if (stack.is(ItemTags.LOGS)) {
                logs.add(stack);
            }
        });
        return logs;
    }
}
