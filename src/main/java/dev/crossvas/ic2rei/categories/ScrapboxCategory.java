package dev.crossvas.ic2rei.categories;

import com.google.common.collect.Lists;
import dev.crossvas.ic2rei.displays.ScrapboxDisplay;
import dev.crossvas.ic2rei.utils.CategoryIDs;
import dev.crossvas.ic2rei.utils.GuiHelper;
import dev.crossvas.ic2rei.utils.IGuiHelper;
import ic2.core.platform.registries.IC2Items;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class ScrapboxCategory implements DisplayCategory<ScrapboxDisplay>, IGuiHelper {

    public static final ScrapboxCategory INSTANCE = new ScrapboxCategory();

    @Override
    public CategoryIdentifier<? extends ScrapboxDisplay> getCategoryIdentifier() {
        return CategoryIDs.SCRAPBOX;
    }

    @Override
    public Component getTitle() {
        return IC2Items.SCRAPBOX.getDescription();
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(IC2Items.SCRAPBOX);
    }

    @Override
    public List<Widget> setupDisplay(ScrapboxDisplay display, Rectangle bounds) {
        List<Widget> widgets = Lists.newArrayList();
        GuiHelper.createRecipeBase(widgets, bounds);
        GuiHelper.addProgressBar(widgets, point(getProgressBarX(bounds), getProgressBarY(bounds)), 3000, GuiHelper.ProgressType.SMELTER);
        DecimalFormat preciseFormatter = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.ROOT));
        float chance = display.getRecipe().getChance();
        float formattedChance = Float.parseFloat(preciseFormatter.format(chance));
        // info
        GuiHelper.addLabel(widgets, point(bounds.getCenterX(), bounds.getMinY() + offset), literal((formattedChance < 1 ? "< " + 1 : formattedChance) + "%"));
        // input
        GuiHelper.addInputSlot(widgets, adjustedInputPoint(bounds), EntryIngredients.of(display.getRecipe().getInput()));
        // output
        GuiHelper.addLargeOutputSlot(widgets, adjustedOutputPoint(bounds), EntryIngredients.of(display.getRecipe().getOutput()));
        return widgets;
    }

    @Override
    public int getDisplayWidth(ScrapboxDisplay display) {
        return getWidth();
    }

    @Override
    public int getDisplayHeight() {
        return getHeight();
    }
}
