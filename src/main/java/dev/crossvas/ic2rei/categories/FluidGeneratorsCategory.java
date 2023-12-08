package dev.crossvas.ic2rei.categories;

import com.google.common.collect.Lists;
import dev.crossvas.ic2rei.displays.FluidGeneratorsDisplay;
import dev.crossvas.ic2rei.recipes.FluidGeneratorRecipe;
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
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class FluidGeneratorsCategory implements IGuiHelper {

    public static final int WIDTH = 96;
    public static final int HEIGHT = 4 * 5 + 2 * 18 + 1;

    public static final FluidGeneratorsCategory INSTANCE = new FluidGeneratorsCategory();

    public static final CommonFluidGeneratorCategory<FluidGeneratorsDisplay.FluidGeneratorDisplay> FLUID_HENERATOR = new CommonFluidGeneratorCategory<>(CategoryIDs.FLUID_GENERATOR, IC2Blocks.LIQUID_FUEL_GENERATOR);
    public static final CommonFluidGeneratorCategory<FluidGeneratorsDisplay.FluidGeneratorDisplay> GEOTHERMAL = new CommonFluidGeneratorCategory<>(CategoryIDs.GEOTHERMAL_GENERATOR, IC2Blocks.GEOTHERMAL_GENERATOR);

    public List<Widget> getDisplay(FluidGeneratorsDisplay.FluidGeneratorDisplay display, Rectangle bounds) {
        List<Widget> widgets = Lists.newArrayList();
        GuiHelper.createRecipeBase(widgets, bounds);
        Point inputPoint = point(bounds.getMinX() + 2 * offset + 1, bounds.getMaxY() - 2 * offset + 1 - slotSize);
        Point progressBar = point(inputPoint.getX() + slotSize + 3 * offset, inputPoint.getY() - 1);
        Point progressBack = point(inputPoint.getX() + slotSize + 2 * offset + 1, inputPoint.getY() - 1);

        FluidGeneratorRecipe recipe = display.getRecipe();
        FluidStack fuel = recipe.getFluidFuel();
        // info
        if (shouldAddEnergyInfo()) {
            GuiHelper.addLabelLeft(widgets, point(progressBack.getX() + innerOffset, progressBack.getY() - 2 * lineHeight), literal(recipe.getBurningTime() + "t"));
            GuiHelper.addLabelLeft(widgets, point(progressBack.getX() - offset, progressBack.getY() - lineHeight), literal("→" + recipe.getProduction() + " EU/t"));
        } else {
            Point errorPoint = point(bounds.getMaxX() - offset - slotSize + 1, bounds.getMinY() + offset);
            addEUReaderErrorMessage(widgets, errorPoint, false, true);
        }
        // input
        GuiHelper.addInputSlot(widgets, inputPoint, EntryIngredients.of(fuel.getFluid()));
        widgets.add(Widgets.createBurningFire(point(inputPoint.getX() + 1, inputPoint.getY() - slotSize)).animationDurationTicks(recipe.getBurningTime()));
        widgets.add(Widgets.createTexturedWidget(GuiHelper.ProgressType.GENERATOR.texture, progressBack.getX(), progressBack.getY(), 90, 35, 32, 17));
        GuiHelper.addProgressBar(widgets, point(progressBar.getX(), progressBar.getY()), 3000, GuiHelper.ProgressType.GENERATOR);
        return widgets;
    }

    public static class CommonFluidGeneratorCategory<T extends BasicDisplay> implements DisplayCategory<FluidGeneratorsDisplay.FluidGeneratorDisplay> {

        private final CategoryIdentifier<FluidGeneratorsDisplay.FluidGeneratorDisplay> ID;
        private final Block STATION;

        public CommonFluidGeneratorCategory(CategoryIdentifier<FluidGeneratorsDisplay.FluidGeneratorDisplay> id, Block station) {
            this.ID = id;
            this.STATION = station;
        }

        @Override
        public CategoryIdentifier<FluidGeneratorsDisplay.FluidGeneratorDisplay> getCategoryIdentifier() {
            return this.ID;
        }

        @Override
        public Component getTitle() {
            return this.STATION.getName();
        }

        @Override
        public Renderer getIcon() {
            return EntryStacks.of(this.STATION);
        }

        @Override
        public int getDisplayWidth(FluidGeneratorsDisplay.FluidGeneratorDisplay display) {
            return WIDTH;
        }

        @Override
        public int getDisplayHeight() {
            return HEIGHT;
        }

        @Override
        public List<Widget> setupDisplay(FluidGeneratorsDisplay.FluidGeneratorDisplay display, Rectangle bounds) {
            return FluidGeneratorsCategory.INSTANCE.getDisplay(display, bounds);
        }
    }
}
