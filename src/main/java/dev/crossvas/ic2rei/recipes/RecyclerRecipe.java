package dev.crossvas.ic2rei.recipes;

import ic2.api.recipes.registries.IMachineRecipeList;
import ic2.core.platform.registries.IC2Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public class RecyclerRecipe {

    private List<ItemStack> SCRAP_INPUTS;
    private ItemStack SCRAP_OUTPUT;

    private Ingredient INPUT;
    private List<ItemStack> OUTPUTS;

    public RecyclerRecipe(List<ItemStack> inputs) {
        this.SCRAP_INPUTS = inputs;
        this.SCRAP_OUTPUT = new ItemStack(IC2Items.SCRAP);
    }

    public RecyclerRecipe(IMachineRecipeList.RecipeEntry recipeEntry) {
        this.INPUT = recipeEntry.getInputs()[0].asIngredient();
        this.OUTPUTS = recipeEntry.getOutput().getAllOutputs();
    }

    // scrap

    public ItemStack getScrapOutput() {
        return this.SCRAP_OUTPUT;
    }

    public List<ItemStack> getScrapInputs() {
        return this.SCRAP_INPUTS;
    }

    // regular recycling

    public Ingredient getInput() {
        return this.INPUT;
    }

    public List<ItemStack> getOutputs() {
        return this.OUTPUTS;
    }
}
