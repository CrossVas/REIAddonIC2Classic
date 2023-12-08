package dev.crossvas.ic2rei.displays.brewing;

import dev.crossvas.ic2rei.recipes.brewing.BeerBrewRecipe;
import dev.crossvas.ic2rei.utils.CategoryIDs;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import java.util.Collections;
import java.util.List;

public class BeerBrewDisplay extends BasicDisplay {

    private BeerBrewRecipe RECIPE;

    public BeerBrewDisplay(BeerBrewRecipe recipe) {
        this(Collections.singletonList(EntryIngredients.ofItemStacks(recipe.getInputs())),
                Collections.singletonList(EntryIngredients.ofItemStacks(recipe.getOutputs()))
        );
        this.RECIPE = recipe;
    }

    public BeerBrewDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return CategoryIDs.BEER_BREWING;
    }

    public BeerBrewRecipe getRecipe() {
        return this.RECIPE;
    }
}
