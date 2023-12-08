package dev.crossvas.ic2rei.recipes;

import ic2.api.recipes.registries.IMachineRecipeList;
import net.minecraft.world.item.crafting.Ingredient;

public class DoubleInputMachineRecipe extends BaseMachineRecipe {

    private final Ingredient SECOND_INPUT;

    public DoubleInputMachineRecipe(IMachineRecipeList.RecipeEntry recipeEntry) {
        super(recipeEntry);
        if (recipeEntry.getInputs()[1] == null) {
            this.SECOND_INPUT = Ingredient.of();
        } else {
            this.SECOND_INPUT = recipeEntry.getInputs()[1].asIngredient();
        }
    }

    public Ingredient getSecondInput() {
        return this.SECOND_INPUT;
    }
}
