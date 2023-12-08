package dev.crossvas.ic2rei.displays;

import dev.crossvas.ic2rei.recipes.MassFabricatorRecipe;
import dev.crossvas.ic2rei.utils.CategoryIDs;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import java.util.Collections;
import java.util.List;

public class MassFabricatorDisplay extends BasicDisplay {

    private MassFabricatorRecipe RECIPE;

    public MassFabricatorDisplay(MassFabricatorRecipe recipe) {
        this(Collections.singletonList(EntryIngredients.of(recipe.getInput())),
                Collections.singletonList(EntryIngredients.of(recipe.getOutput())));
        this.RECIPE = recipe;
    }

    public MassFabricatorDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return CategoryIDs.MASS_FABRICATOR;
    }

    public MassFabricatorRecipe getRecipe() {
        return this.RECIPE;
    }
}
