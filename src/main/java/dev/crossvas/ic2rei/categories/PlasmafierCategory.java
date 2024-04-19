package dev.crossvas.ic2rei.categories;

import com.google.common.collect.Lists;
import dev.crossvas.ic2rei.IC2REIPlugin;
import dev.crossvas.ic2rei.displays.PlasmafierDisplay;
import dev.crossvas.ic2rei.utils.CategoryIDs;
import dev.crossvas.ic2rei.utils.GuiHelper;
import dev.crossvas.ic2rei.utils.IGuiHelper;
import ic2.core.platform.registries.IC2Blocks;
import ic2.core.platform.registries.IC2Items;
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

public class PlasmafierCategory implements DisplayCategory<PlasmafierDisplay>, IGuiHelper {

    public static final PlasmafierCategory INSTANCE = new PlasmafierCategory();

    @Override
    public CategoryIdentifier<? extends PlasmafierDisplay> getCategoryIdentifier() {
        return CategoryIDs.PLASMAFIER;
    }

    @Override
    public Component getTitle() {
        return IC2Blocks.PLASMAFIER.getName();
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(IC2Blocks.PLASMAFIER);
    }

    @Override
    public List<Widget> setupDisplay(PlasmafierDisplay display, Rectangle bounds) {
        List<Widget> widgets = Lists.newArrayList();
        int maxX = bounds.getMaxX();
        int minX = bounds.getMinX();
        int maxY = bounds.getMaxY();
        int minY = bounds.getMinY();
        GuiHelper.createRecipeBase(widgets, bounds);
        // Comps
        Component tierComp = format("rei.cat.plasmafier.tier.ev");
        Component ticksComp = format("rei.cat.plasmafier.ticks", display.getRecipe().getTicks());
        Component energyPerTick = literal("→" + 10240 + " EU/t");
        // Points
        Point ticksPoint = point(minX + 5, maxY - 5 - lineHeight);
        Point tierPoint = point(minX + 5, minY + 5);
        Point energyPerTickPoint = point(maxX - 5, maxY - 5 - lineHeight);
        Point inputPoint = point(minX + offset + slotSize, maxY - lineHeight - slotSize - 2 * offset);
        Point auxInputPoint = point(maxX - offset - 2 * slotSize, maxY - lineHeight - (2 * slotSize) - 2 * offset - 2);
        Point outputPoint = point(maxX - offset - 2 * slotSize, maxY - lineHeight - slotSize - 2 * offset);
        // base
        widgets.add(Widgets.createTexturedWidget(IC2REIPlugin.getTexture("gui"), inputPoint.getX() + slotSize + 9, inputPoint.getY(), 0, 231, 32, 17));
        GuiHelper.addProgressBar(widgets, point(inputPoint.getX() + slotSize + 13, inputPoint.getY()), 3000, GuiHelper.ProgressType.ELECTROLYZER);
        // info
        if (shouldAddEnergyInfo()) {
            GuiHelper.addLabelLeft(widgets, tierPoint, tierComp);
            GuiHelper.addLabelLeft(widgets, ticksPoint, ticksComp);
            GuiHelper.addLabelRight(widgets, energyPerTickPoint, energyPerTick);
        } else {
            addEUReaderErrorMessage(widgets, tierPoint, false, false);
        }

        // input
        GuiHelper.addInputSlot(widgets, inputPoint, EntryIngredients.of(display.getRecipe().getInput()));
        GuiHelper.addInputSlot(widgets, auxInputPoint, EntryIngredients.of(IC2Items.CELL_EMPTY.getDefaultInstance()));
        // output
        GuiHelper.addBaseOutputSlot(widgets, outputPoint, EntryIngredients.of(display.getRecipe().getOutput()));
        return widgets;
    }

    @Override
    public int getDisplayWidth(PlasmafierDisplay display) {
        return 132;
    }

    @Override
    public int getDisplayHeight() {
        return 64;
    }
}
