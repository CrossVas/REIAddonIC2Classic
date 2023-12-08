package dev.crossvas.ic2rei.displays;

import dev.crossvas.ic2rei.recipes.RefineryRecipe;
import dev.crossvas.ic2rei.utils.CategoryIDs;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.type.VanillaEntryTypes;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import java.util.Arrays;
import java.util.List;

public class RefineryDisplay extends BasicDisplay {

    private RefineryRecipe RECIPE;

    public RefineryDisplay(RefineryRecipe recipe) {
        this(Arrays.asList(
                        EntryIngredients.ofItemStacks(recipe.getInputs()),
                        EntryIngredients.of(recipe.getFluidInputA().getFluid()),
                        EntryIngredients.of(recipe.getFluidInputB().getFluid())),
                        Arrays.asList(
                                EntryIngredients.ofItemStacks(recipe.getOutputs()),
                                EntryIngredients.of(VanillaEntryTypes.FLUID, recipe.getFluidOutputs()))
        );
        this.RECIPE = recipe;
    }

    public RefineryDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return CategoryIDs.REFINERY;
    }

    public RefineryRecipe getRecipe() {
        return this.RECIPE;
    }
}
