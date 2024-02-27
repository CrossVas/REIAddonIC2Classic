package dev.crossvas.ic2rei.recipes;

import ic2.api.recipes.registries.IMachineRecipeList;
import ic2.core.block.machines.recipes.misc.ScrapOutput;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public class RecyclerRecipe {

    private final Ingredient INPUT;
    private final List<ItemStack> OUTPUTS;

    private float CHANCE;

    public RecyclerRecipe(IMachineRecipeList.RecipeEntry recipeEntry) {
        this.INPUT = recipeEntry.getInputs()[0].asIngredient();
        this.OUTPUTS = recipeEntry.getOutput().getAllOutputs();
        if (recipeEntry.getOutput() instanceof ScrapOutput scrapOutput) {
            this.CHANCE = scrapOutput.getChance();
        }
    }

    public float getChance() {
        return this.CHANCE;
    }

    public Ingredient getInput() {
        return this.INPUT;
    }

    public List<ItemStack> getOutputs() {
        return this.OUTPUTS;
    }
}
