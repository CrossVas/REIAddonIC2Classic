package dev.crossvas.ic2rei.categories;

import com.google.common.collect.Lists;
import dev.crossvas.ic2rei.displays.ElectrolyzerDisplay;
import dev.crossvas.ic2rei.utils.CategoryIDs;
import dev.crossvas.ic2rei.utils.GuiHelper;
import dev.crossvas.ic2rei.utils.IGuiHelper;
import ic2.core.platform.registries.IC2Blocks;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;

import java.util.List;

public class ElectrolyzerCategory implements DisplayCategory<ElectrolyzerDisplay>, IGuiHelper {

    public static final ElectrolyzerCategory INSTANCE = new ElectrolyzerCategory();

    @Override
    public CategoryIdentifier<? extends ElectrolyzerDisplay> getCategoryIdentifier() {
        return CategoryIDs.ELECTROLYZER;
    }

    @Override
    public Component getTitle() {
        return IC2Blocks.ELECTROLYZER.getName();
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(IC2Blocks.ELECTROLYZER);
    }

    @Override
    public List<Widget> setupDisplay(ElectrolyzerDisplay display, Rectangle bounds) {
        List<Widget> widgets = Lists.newArrayList();
        GuiHelper.createRecipeBase(widgets, bounds);
        widgets.add(Widgets.createTexturedWidget(GuiHelper.ProgressType.ELECTROLYZER.texture, adjustedInputPoint(bounds).getX() + 2 * slotSize - innerOffset - 1, getCenterY(bounds) - 1, 75, 34, 32, 17));
        GuiHelper.addProgressBar(widgets, point(getProgressBarX(bounds), getProgressBarY(bounds) - 1), 3000, GuiHelper.ProgressType.ELECTROLYZER);
        // info
        GuiHelper.addLabel(widgets, point(bounds.getCenterX(), bounds.getMinY() + offset), format(display.getRecipeModeString()));
        GuiHelper.addLabel(widgets, point(bounds.getCenterX(), bounds.getMaxY() - offset - lineHeight), format("gui.ic2.electrolyzer.energy", display.getRecipe().getEnergy()));
        // input
        GuiHelper.addInputSlot(widgets, adjustedInputPoint(bounds), EntryIngredients.of(display.getRecipe().getInput()));
        // output
        GuiHelper.addLargeOutputSlot(widgets, adjustedOutputPoint(bounds), EntryIngredients.of(display.getRecipe().getOutput()));
        return widgets;
    }

    @Override
    public int getDisplayWidth(ElectrolyzerDisplay display) {
        return 128;
    }

    @Override
    public int getDisplayHeight() {
        return 56;
    }
}
