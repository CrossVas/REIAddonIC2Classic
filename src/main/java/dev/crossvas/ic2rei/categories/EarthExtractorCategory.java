package dev.crossvas.ic2rei.categories;

import com.google.common.collect.Lists;
import dev.crossvas.ic2rei.displays.EarthExtractorDisplay;
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

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class EarthExtractorCategory implements DisplayCategory<EarthExtractorDisplay>, IGuiHelper {

    public static final EarthExtractorCategory INSTANCE = new EarthExtractorCategory();

    @Override
    public CategoryIdentifier<? extends EarthExtractorDisplay> getCategoryIdentifier() {
        return CategoryIDs.EARTH_EXTRACTOR;
    }

    @Override
    public Component getTitle() {
        return IC2Blocks.RARE_EARTH_EXTRACTOR.getName();
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(IC2Blocks.RARE_EARTH_EXTRACTOR);
    }

    @Override
    public List<Widget> setupDisplay(EarthExtractorDisplay display, Rectangle bounds) {
        List<Widget> widgets = Lists.newArrayList();
        widgets.add(Widgets.createRecipeBase(bounds));
        // info
        DecimalFormat formatter = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.ROOT));
        GuiHelper.addLabelRight(widgets, point(bounds.getMaxX() - offset, bounds.getMinY() + offset), format("rei.cat.earth_extractor.points", formatter.format(display.getRecipe().getPoints())));
        GuiHelper.addLabelLeft(widgets, point(bounds.getMinX() + offset, bounds.getMaxY() - offset - lineHeight), format("rei.cat.earth_extractor.total", display.getRecipe().getTotal()));
        // input
        GuiHelper.addInputSlot(widgets, adjustedInputPoint(bounds), EntryIngredients.of(display.getRecipe().getInput()));
        // output
        GuiHelper.addLargeOutputSlot(widgets, adjustedOutputPoint(bounds), EntryIngredients.of(display.getRecipe().getOutput()));
        GuiHelper.addProgressBar(widgets, point(getProgressBarX(bounds), getProgressBarY(bounds)), 3000, GuiHelper.ProgressType.EARTH_EXTRACTOR);
        return widgets;
    }

    @Override
    public int getDisplayWidth(EarthExtractorDisplay display) {
        return getWidth();
    }

    @Override
    public int getDisplayHeight() {
        return 64;
    }
}
