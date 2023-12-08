package dev.crossvas.ic2rei.categories;

import com.google.common.collect.Lists;
import dev.crossvas.ic2rei.displays.GeneratorDisplay;
import dev.crossvas.ic2rei.utils.CategoryIDs;
import dev.crossvas.ic2rei.utils.GuiHelper;
import dev.crossvas.ic2rei.utils.IGuiHelper;
import ic2.core.platform.registries.IC2Blocks;
import me.shedaniel.math.Point;
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

public class GeneratorCategory implements DisplayCategory<GeneratorDisplay>, IGuiHelper {

    public int HEIGHT = 4 * offset + 1 + 2 * slotSize;

    public static final GeneratorCategory INSTANCE = new GeneratorCategory();

    @Override
    public CategoryIdentifier<? extends GeneratorDisplay> getCategoryIdentifier() {
        return CategoryIDs.GENERATOR;
    }

    @Override
    public Component getTitle() {
        return IC2Blocks.GENERATOR.getName();
    }

    @Override
    public List<Widget> setupDisplay(GeneratorDisplay display, Rectangle bounds) {
        List<Widget> widgets = Lists.newArrayList();
        GuiHelper.createRecipeBase(widgets, bounds);
        Point inputPoint = point(bounds.getMinX() + 2 * offset + 1, bounds.getMaxY() - 2 * offset + 1 - slotSize);
        Point progressBar = point(inputPoint.getX() + slotSize + 3 * offset, inputPoint.getY() - 1);
        Point progressBack = point(inputPoint.getX() + slotSize + 2 * offset + 1, inputPoint.getY() - 1);
        // info
        if (shouldAddEnergyInfo()) {
            GuiHelper.addLabelLeft(widgets, point(progressBack.getX() + innerOffset, progressBack.getY() - 2 * lineHeight), literal(display.getRecipe().getEnergy() + "t"));
            GuiHelper.addLabelLeft(widgets, point(progressBack.getX() - offset, progressBack.getY() - lineHeight), literal("→" + 10 + " EU/t"));
        } else {
            Point errorPoint = point(bounds.getMaxX() - offset - slotSize + 1, bounds.getMinY() + offset);
            addEUReaderErrorMessage(widgets, errorPoint, false, true);
        }
        // input
        GuiHelper.addInputSlot(widgets, inputPoint, EntryIngredients.of(display.getRecipe().getInput()));
        widgets.add(Widgets.createBurningFire(point(inputPoint.getX() + 1, inputPoint.getY() - slotSize)).animationDurationTicks(display.getRecipe().getEnergy()));
        widgets.add(Widgets.createTexturedWidget(GuiHelper.ProgressType.GENERATOR.texture, progressBack.getX(), progressBack.getY(), 90, 35, 32, 17));
        GuiHelper.addProgressBar(widgets, point(progressBar.getX(), progressBar.getY()), 3000, GuiHelper.ProgressType.GENERATOR);
        return widgets;
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(IC2Blocks.GENERATOR);
    }

    @Override
    public int getDisplayWidth(GeneratorDisplay display) {
        return 96;
    }

    @Override
    public int getDisplayHeight() {
        return HEIGHT;
    }
}
