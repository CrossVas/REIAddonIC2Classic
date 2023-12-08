package dev.crossvas.ic2rei.categories;

import com.google.common.collect.Lists;
import dev.crossvas.ic2rei.displays.CannerDisplay;
import dev.crossvas.ic2rei.utils.CategoryIDs;
import dev.crossvas.ic2rei.utils.GuiHelper;
import dev.crossvas.ic2rei.utils.IGuiHelper;
import ic2.api.recipes.registries.ICannerRecipeRegistry;
import ic2.core.platform.registries.IC2Blocks;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class CannerCategory implements IGuiHelper {

    public static final CannerCategory INSTANCE = new CannerCategory();
    public static final CannerCommonCategory<CannerDisplay.CannerRepairDisplay> REPAIR = new CannerCommonCategory<>(CategoryIDs.CANNER_REPAIR, Component.translatable("rei.cat.canner.repair"));
    public static final CannerCommonCategory<CannerDisplay.CannerRefuelDisplay> REFUEL = new CannerCommonCategory<>(CategoryIDs.CANNER_REFUEL, Component.translatable("rei.cat.canner.refuel"));
    public static final CannerCommonCategory<CannerDisplay.CannerRefillDisplay> REFILL = new CannerCommonCategory<>(CategoryIDs.CANNER_REFILL, Component.translatable("rei.cat.canner.refill"));

    public static final int HEIGHT = 72;
    public static final int WIDTH = 96;
    public static final Renderer ICON = EntryStacks.of(IC2Blocks.CANNER);

    public List<Widget> getDisplay(BasicDisplay display, Rectangle bounds) {
        List<Widget> widgets = Lists.newArrayList();
        GuiHelper.createRecipeBase(widgets, bounds);
        Component text = Component.empty();
        ItemStack inputA;
        ItemStack inputB;
        ItemStack output;
        DecimalFormat decimalFormat = new DecimalFormat("#,###", new DecimalFormatSymbols(Locale.ROOT));
        if (display instanceof CannerDisplay.CannerRepairDisplay repairDisplay) {
            text = literal("+" + decimalFormat.format(repairDisplay.getRecipe().getRepair().getB()));
            inputA = repairDisplay.getInputA();
            inputB = repairDisplay.getInputB();
            output = repairDisplay.getOutput();
        } else if (display instanceof CannerDisplay.CannerRefuelDisplay refuelDisplay) {
            ICannerRecipeRegistry.FuelValue refuel = refuelDisplay.getRecipe().getFuels();
            int fuelValue;
            if (refuel.isMultiplier()) {
                DecimalFormat formatter = ItemStack.ATTRIBUTE_MODIFIER_FORMAT;
                fuelValue = Integer.parseInt(formatter.format(refuel.getFuelMultiplier() * 100.0F));
                text = literal("+" + fuelValue + "%");
            } else {
                fuelValue = refuel.getFuelValue();
                text = literal("+" + decimalFormat.format(fuelValue));
            }
            inputA = refuelDisplay.getInputA();
            inputB = refuelDisplay.getInputB();
            output = refuelDisplay.getOutput();
        } else {
            inputA = ((CannerDisplay.CannerRefillDisplay) display).getInputA();
            inputB = ((CannerDisplay.CannerRefillDisplay) display).getInputB();
            output = ((CannerDisplay.CannerRefillDisplay) display).getOutput();
        }

        // info
        GuiHelper.addLabelRight(widgets, point(bounds.getMaxX() - offset, bounds.getMaxY() - offset - lineHeight), text);
        GuiHelper.addProgressBar(widgets, point(getProgressBarX(bounds) - 3 * offset, getProgressBarY(bounds) + 2), 3000, GuiHelper.ProgressType.CANNER);
        // input
        Point inputSlotPoint = point(bounds.getMinX() + 2 * offset + innerOffset, bounds.getMinY() + 2 * offset);
        GuiHelper.addInputSlot(widgets, inputSlotPoint, EntryIngredients.of(inputA));
        GuiHelper.addInputSlot(widgets, point(inputSlotPoint.getX(), inputSlotPoint.getY() + 2 * slotSize + innerOffset - 1), EntryIngredients.of(inputB));
        // output
        GuiHelper.addLargeOutputSlot(widgets, adjustedOutputPoint(bounds), EntryIngredients.of(output));
        return widgets;
    }

    public static class CannerCommonCategory<T extends BasicDisplay> implements DisplayCategory<T> {

        private final CategoryIdentifier<? extends T> ID;
        private final Component TITLE;

        public CannerCommonCategory(CategoryIdentifier<? extends T> id, Component title) {
            this.ID = id;
            this.TITLE = title;
        }


        @Override
        public CategoryIdentifier<? extends T> getCategoryIdentifier() {
            return ID;
        }

        @Override
        public Component getTitle() {
            return this.TITLE;
        }

        @Override
        public Renderer getIcon() {
            return ICON;
        }

        @Override
        public int getDisplayWidth(BasicDisplay display) {
            return WIDTH;
        }

        @Override
        public int getDisplayHeight() {
            return HEIGHT;
        }

        @Override
        public List<Widget> setupDisplay(T display, Rectangle bounds) {
            return CannerCategory.INSTANCE.getDisplay(display, bounds);
        }
    }
}
