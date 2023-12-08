package dev.crossvas.ic2rei.categories;

import dev.crossvas.ic2rei.displays.RefineryDisplay;
import dev.crossvas.ic2rei.utils.CategoryIDs;
import dev.crossvas.ic2rei.utils.GuiHelper;
import dev.crossvas.ic2rei.utils.IGuiHelper;
import ic2.api.recipes.registries.IRefiningRecipeList;
import ic2.core.platform.registries.IC2Blocks;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.type.VanillaEntryTypes;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import org.apache.commons.lang3.mutable.MutableInt;

import java.util.ArrayList;
import java.util.List;

public class RefineryCategory implements DisplayCategory<RefineryDisplay>, IGuiHelper {

    public static final RefineryCategory INSTANCE = new RefineryCategory();

    @Override
    public CategoryIdentifier<? extends RefineryDisplay> getCategoryIdentifier() {
        return CategoryIDs.REFINERY;
    }

    @Override
    public Component getTitle() {
        return IC2Blocks.REFINERY.getName();
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(IC2Blocks.REFINERY);
    }

    @Override
    public List<Widget> setupDisplay(RefineryDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        List<List<ItemStack>> OUTPUTS = getOutputs(display.getRecipe().getMainRecipe());
        GuiHelper.createRecipeBase(widgets, bounds);
        GuiHelper.addProgressBar(widgets, point(getProgressBarX(bounds) + 2 * offset + innerOffset, getCenterY(bounds)), 3000, GuiHelper.ProgressType.SMELTER);
        // input
        FluidStack fluidA = display.getRecipe().getFluidInputA().isEmpty() ? FluidStack.EMPTY : display.getRecipe().getFluidInputA();
        FluidStack fluidB = display.getRecipe().getFluidInputB().isEmpty() ? FluidStack.EMPTY : display.getRecipe().getFluidInputB();
        EntryIngredient entryFuelA = EntryIngredients.of(fluidA.getFluid(), fluidA.getAmount());
        EntryIngredient entryFuelB = EntryIngredients.of(fluidB.getFluid(), fluidB.getAmount());
        // input
        GuiHelper.addTank(widgets, bounds.getMinX() + offset, bounds.getMinY() + offset + 1, entryFuelA);
        GuiHelper.addTank(widgets, bounds.getMinX() + offset + slotSize + innerOffset, bounds.getMinY() + offset + 1, entryFuelB);
        Point inputPoint = point(adjustedInputPoint(bounds).getX() + 2 * slotSize - innerOffset, adjustedInputPoint(bounds).getY());
        GuiHelper.addInputSlot(widgets, inputPoint, EntryIngredients.ofItemStacks(display.getRecipe().getInputs()));
        // out
        Point out1 = point(bounds.getMaxX() - offset - 2 * slotSize - innerOffset, adjustedInputPoint(bounds).getY() - slotSize);
        Point out2 = point(bounds.getMaxX() - offset - 2 * slotSize - innerOffset, adjustedInputPoint(bounds).getY());
        Point out3 = point(bounds.getMaxX() - offset - 2 * slotSize - innerOffset, adjustedInputPoint(bounds).getY() + slotSize);
        GuiHelper.addBaseOutputSlot(widgets, out1, EntryIngredients.ofItemStacks(OUTPUTS.get(0)));
        GuiHelper.addBaseOutputSlot(widgets, out2, EntryIngredients.ofItemStacks(OUTPUTS.get(1)));
        GuiHelper.addBaseOutputSlot(widgets, out3, EntryIngredients.ofItemStacks(OUTPUTS.get(2)));

        EntryIngredient fluidEntry = EntryIngredients.of(VanillaEntryTypes.FLUID, display.getRecipe().getFluidOutputs());
        GuiHelper.addTank(widgets, bounds.getMaxX() - offset - slotSize - 1, bounds.getMinY() + offset + 1, fluidEntry);
        return widgets;
    }

    @Override
    public int getDisplayWidth(RefineryDisplay display) {
        return 145;
    }

    @Override
    public int getDisplayHeight() {
        return 72;
    }

    public List<List<ItemStack>> getOutputs(IRefiningRecipeList.FluidRecipe recipe) {
        List<List<ItemStack>> lists = new ObjectArrayList<>();
        for (int i = 0; i < 3; i++) {
            lists.add(new ObjectArrayList<>());
        }

        MutableInt index = new MutableInt();
        Object2IntMap<Item> itemMap = new Object2IntOpenHashMap<>();
        recipe.getOutput().getAllOutputs().forEach(output -> lists.get(itemMap.computeIfAbsent(output.getItem(), (T) -> index.getAndIncrement() % 3)).add(output));
        return lists;
    }
}
