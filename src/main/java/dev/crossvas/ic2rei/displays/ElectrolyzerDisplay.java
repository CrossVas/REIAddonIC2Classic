package dev.crossvas.ic2rei.displays;

import dev.crossvas.ic2rei.utils.CategoryIDs;
import ic2.api.recipes.registries.IElectrolyzerRecipeList;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import java.util.Collections;
import java.util.List;

public class ElectrolyzerDisplay extends BasicDisplay {

    private IElectrolyzerRecipeList.ElectrolyzerRecipe RECIPE;

    public ElectrolyzerDisplay(IElectrolyzerRecipeList.ElectrolyzerRecipe recipe) {
        this(Collections.singletonList(EntryIngredients.of(recipe.getInput())),
                Collections.singletonList(EntryIngredients.of(recipe.getOutput())));
        this.RECIPE = recipe;
    }

    public ElectrolyzerDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return CategoryIDs.ELECTROLYZER;
    }

    public IElectrolyzerRecipeList.ElectrolyzerRecipe getRecipe() {
        return this.RECIPE;
    }

    public String getRecipeModeString() {
        boolean dual = this.RECIPE.isChargeRecipe() && this.RECIPE.isDischargeRecipe();
        return "gui.ic2.electrolyzer." + (dual ? "dual" : this.RECIPE.isChargeRecipe() ? "charging" : "discharging");
    }
}
