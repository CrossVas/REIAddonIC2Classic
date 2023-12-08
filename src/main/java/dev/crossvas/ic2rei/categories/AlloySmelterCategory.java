package dev.crossvas.ic2rei.categories;

import com.google.common.collect.Lists;
import dev.crossvas.ic2rei.displays.DoubleInputMachineDisplay;
import dev.crossvas.ic2rei.utils.CategoryIDs;
import dev.crossvas.ic2rei.utils.GuiHelper;
import dev.crossvas.ic2rei.utils.IGuiHelper;
import ic2.core.platform.registries.IC2Blocks;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;

import java.util.List;

public class AlloySmelterCategory implements DisplayCategory<DoubleInputMachineDisplay>, IGuiHelper {

    public static final AlloySmelterCategory INSTANCE = new AlloySmelterCategory();

    @Override
    public CategoryIdentifier<? extends DoubleInputMachineDisplay> getCategoryIdentifier() {
        return CategoryIDs.ALLOY_SMELTER;
    }

    @Override
    public Component getTitle() {
        return IC2Blocks.ALLOY_SMELTER.getName();
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(IC2Blocks.ALLOY_SMELTER);
    }

    @Override
    public List<Widget> setupDisplay(DoubleInputMachineDisplay display, Rectangle bounds) {
        List<Widget> widgets = Lists.newArrayList();
        GuiHelper.createRecipeBase(widgets, bounds);
        GuiHelper.addProgressBar(widgets, point(getProgressBarX(bounds) + slotSize / 2, getProgressBarY(bounds)), 3000, GuiHelper.ProgressType.SMELTER);
        // input
        GuiHelper.addInputSlot(widgets, adjustedInputPoint(bounds), EntryIngredients.ofIngredient(display.getRecipe().getInput()));
        GuiHelper.addInputSlot(widgets, point(adjustedInputPoint(bounds).getX() + slotSize + 1, adjustedInputPoint(bounds).getY()), EntryIngredients.ofIngredient(display.getRecipe().getSecondInput()));
        // output
        GuiHelper.addLargeOutputSlot(widgets, adjustedOutputPoint(bounds), EntryIngredients.ofItemStacks(display.getRecipe().getOutputs()));
        return widgets;
    }

    @Override
    public int getDisplayWidth(DoubleInputMachineDisplay display) {
        return 128;
    }

    @Override
    public int getDisplayHeight() {
        return 2 * slotSize + innerOffset;
    }
}
