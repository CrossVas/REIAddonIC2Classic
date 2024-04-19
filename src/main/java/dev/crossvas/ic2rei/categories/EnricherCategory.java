package dev.crossvas.ic2rei.categories;

import com.google.common.collect.Lists;
import dev.crossvas.ic2rei.IC2REIPlugin;
import dev.crossvas.ic2rei.displays.EnricherDisplay;
import dev.crossvas.ic2rei.utils.CategoryIDs;
import dev.crossvas.ic2rei.utils.GuiHelper;
import dev.crossvas.ic2rei.utils.IGuiHelper;
import ic2.core.platform.registries.IC2Blocks;
import me.shedaniel.math.Dimension;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class EnricherCategory implements DisplayCategory<EnricherDisplay>, IGuiHelper {

    public static final EnricherCategory INSTANCE = new EnricherCategory();

    @Override
    public CategoryIdentifier<? extends EnricherDisplay> getCategoryIdentifier() {
        return CategoryIDs.ENRICHER;
    }

    @Override
    public Component getTitle() {
        return IC2Blocks.URANIUM_ENRICHER.getName();
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(IC2Blocks.URANIUM_ENRICHER);
    }

    @Override
    public List<Widget> setupDisplay(EnricherDisplay display, Rectangle bounds) {
        List<Widget> widgets = Lists.newArrayList();
        // base
        GuiHelper.createRecipeBase(widgets, bounds);
        GuiHelper.addProgressBar(widgets, point(bounds.getMinX() + 2 * offset + slotSize, bounds.getMinY() + offset), 3000, GuiHelper.ProgressType.SMELTER);
        GuiHelper.addProgressBar(widgets, point(getProgressBarX(bounds), getProgressBarY(bounds) - slotSize / 2), 3000, GuiHelper.ProgressType.ENRICHER);
        Point fuelPoint = point(getCenterX(bounds) - slotSize + offset, bounds.getMinY() + offset);
        widgets.add(Widgets.createTexturedWidget(IC2REIPlugin.getTexture("gui"), fuelPoint.getX(), fuelPoint.getY(), 0, 134, 6, 35));
        widgets.add(Widgets.createFilledRectangle(new Rectangle(point(fuelPoint.getX() + 1, fuelPoint.getY() + 1), new Dimension(4, 33)), display.getColor()));
        // input
        EntryIngredient input = EntryIngredients.ofItemStacks(display.getInputs());
        EntryIngredient fuels = EntryIngredients.ofItemStacks(display.getFuels());
        Point inputFuel = point(bounds.getMinX() + offset + 1, bounds.getMinY() + offset + 1);
        Point inputPoint = point(getCenterX(bounds) - slotSize - offset + 1, fuelPoint.getY() + 35 + 2);
        GuiHelper.addInputSlot(widgets, inputPoint, input);
        GuiHelper.addInputSlot(widgets, inputFuel, fuels);
        // output
        GuiHelper.addLargeOutputSlot(widgets, adjustedOutputPoint(bounds), EntryIngredients.of(display.getRecipe().getOutput()));
        //info
        Point requiredPoints = point(bounds.getMaxX() - offset, bounds.getMinY() + offset);
        Point requiredPointsValue = point(bounds.getMaxX() - offset, bounds.getMinY() + offset + lineHeight);
        Point points = point(inputFuel.getX(), inputFuel.getY() + slotSize + innerOffset);
        Point pointsValue = point(inputFuel.getX(), inputFuel.getY() + slotSize + innerOffset + lineHeight);
        GuiHelper.addLabelRight(widgets, requiredPoints, format("gui.ic2.uranium_enricher.per_ingot"));
        GuiHelper.addLabelRight(widgets, requiredPointsValue, literal(String.valueOf(display.getPointsPerIngot())));
        if (display.getFuels().size() > 1) {
            // tooltip
            List<Component> comps = new ArrayList<>();
            display.getFuels().forEach(fuel -> comps.add(fuel.getDisplayName().copy().append(": " + display.getRecipe().getFuelValue(fuel))));
            Point hover = point(inputFuel.getX(), inputFuel.getY() + slotSize + innerOffset);
            Component hoverComp = Component.translatable("rei.info.enricher.points").withStyle(ChatFormatting.BLUE);
            GuiHelper.addLabelLeft(widgets, hover, hoverComp);
            widgets.add(Widgets.createTooltip(new Rectangle(hover, new Dimension(font.width(hoverComp.getString()), 8)), comps));
        } else {
            GuiHelper.addLabelLeft(widgets, points, format("gui.ic2.uranium_enricher.points"));
            GuiHelper.addLabelLeft(widgets, pointsValue, literal("" + display.getRecipe().getFuelValue(display.getFuels().get(0))));
        }

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 4 * slotSize;
    }
}
