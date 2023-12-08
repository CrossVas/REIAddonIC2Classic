package dev.crossvas.ic2rei.categories;

import com.google.common.collect.Lists;
import dev.crossvas.ic2rei.displays.MassFabricatorDisplay;
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

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class MassFabricatorCategory implements DisplayCategory<MassFabricatorDisplay>, IGuiHelper {

    public static final MassFabricatorCategory INSTANCE = new MassFabricatorCategory();

    @Override
    public CategoryIdentifier<? extends MassFabricatorDisplay> getCategoryIdentifier() {
        return CategoryIDs.MASS_FABRICATOR;
    }

    @Override
    public Component getTitle() {
        return IC2Blocks.MASS_FABRICATOR.getName();
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(IC2Blocks.MASS_FABRICATOR);
    }

    @Override
    public List<Widget> setupDisplay(MassFabricatorDisplay display, Rectangle bounds) {
        List<Widget> widgets = Lists.newArrayList();
        int maxX = bounds.getMaxX();
        int minX = bounds.getMinX();
        int maxY = bounds.getMaxY();
        int minY = bounds.getMinY();
        int centerX = bounds.getCenterX() - slotSize / 2 + 1;
        int centerY = bounds.getCenterY();
        int energy = 7000000;
        DecimalFormat formatter = new DecimalFormat("#,###", new DecimalFormatSymbols(Locale.ROOT));
        Component tierComp = format("rei.cat.massfab.tier.hv");
        Component packetsComp = format("rei.cat.massfab.packets");
        Component infiniteComp = format("rei.cat.massfab.packets.infinite");
        Component energyLvlComp = format("rei.cat.massfab.energy");
        Component ampComp = format("rei.cat.massfab.amp");
        Component energyComp = literal((display.getRecipe().getAmplifier() > 0 ? "~" + formatter.format(energy / 7) : formatter.format(energy)) + " EU");
        Component amLvlComp = literal("+" + display.getRecipe().getAmplifier());
        Component packetComp = literal("→" + 512 + " EU/p");
        Point tierPoint = point(minX + offset, minY + offset);
        Point packetsPoint = point(maxX - offset, minY + offset);
        Point infinitePoint = point(packetsPoint.getX(), packetsPoint.getY() + lineHeight);
        Point energyLvlPoint = point(minX + offset, maxY - offset - 2 * lineHeight);
        Point energyPoint = point(energyLvlPoint.getX(), energyLvlPoint.getY() + lineHeight);
        Point ampPoint = point(maxX - offset, centerY - lineHeight);
        Point ampLvlPoint = point(ampPoint.getX(), ampPoint.getY() + lineHeight);
        Point packetPoint = point(maxX - offset, maxY - offset - lineHeight);
        Point errorPoint = point(tierPoint.getX(), centerY - lineHeight);
        GuiHelper.createRecipeBase(widgets, bounds);
        // info
        if (shouldAddEnergyInfo()) {
            GuiHelper.addLabelLeft(widgets, tierPoint, tierComp);
            GuiHelper.addLabelRight(widgets, packetsPoint, packetsComp);
            GuiHelper.addLabelRight(widgets, infinitePoint, infiniteComp);
            GuiHelper.addLabelLeft(widgets, energyLvlPoint, energyLvlComp);
            GuiHelper.addLabelLeft(widgets, energyPoint, energyComp);
            if (display.getRecipe().getAmplifier() > 0) {
                GuiHelper.addLabelRight(widgets, ampPoint, ampComp);
                GuiHelper.addLabelRight(widgets, ampLvlPoint, amLvlComp);
            }
            GuiHelper.addLabelRight(widgets, packetPoint, packetComp);
        } else {
            addEUReaderErrorMessage(widgets, errorPoint, true, false);
        }
        // base
        widgets.add(Widgets.createTexturedWidget(GuiHelper.ProgressType.RECYCLER.texture, centerX, centerY - 7, 176, 0, 14, 14));
        // input
        Point inputPoint = point(centerX, maxY - slotSize - lineHeight - offset - innerOffset);
        GuiHelper.addInputSlot(widgets, inputPoint, EntryIngredients.of(display.getRecipe().getInput()));
        // output
        Point outputPoint = point(centerX, minY + lineHeight + offset + innerOffset);
        GuiHelper.addLargeOutputSlot(widgets, outputPoint, EntryIngredients.of(display.getRecipe().getOutput()));
        return widgets;
    }

    @Override
    public int getDisplayWidth(MassFabricatorDisplay display) {
        return 160;
    }

    @Override
    public int getDisplayHeight() {
        return 96;
    }
}
