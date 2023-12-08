package dev.crossvas.ic2rei.recipes;

import ic2.api.recipes.registries.IRefiningRecipeList;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class RefineryRecipe {

    private final IRefiningRecipeList.FluidRecipe MAIN_RECIPE;

    private final List<ItemStack> INPUTS;
    private final FluidStack FLUID_INPUT_A;
    private final FluidStack FLUID_INPUT_B;
    private final List<dev.architectury.fluid.FluidStack> OUTPUT_MULTI_FLUID;
    private final List<ItemStack> OUTPUTS;

    public RefineryRecipe(IRefiningRecipeList.FluidRecipe recipe) {
        this.INPUTS = recipe.getItemInput().getComponents();
        this.OUTPUTS = recipe.getOutput().getAllOutputs();
        this.FLUID_INPUT_A = recipe.getFirstTank();
        this.FLUID_INPUT_B = recipe.getSecondTank();
        // wat?
        this.OUTPUT_MULTI_FLUID = recipe.getOutput().getAllFluidOutputs().stream().map(fluidStack -> dev.architectury.fluid.FluidStack.create(fluidStack.getFluid(), fluidStack.getAmount())).toList();
        this.MAIN_RECIPE = recipe;
    }

    public IRefiningRecipeList.FluidRecipe getMainRecipe() {
        return this.MAIN_RECIPE;
    }

    public List<ItemStack> getInputs() {
        return this.INPUTS;
    }

    public List<ItemStack> getOutputs() {
        return this.OUTPUTS;
    }

    public FluidStack getFluidInputA() {
        return this.FLUID_INPUT_A;
    }

    public FluidStack getFluidInputB() {
        return this.FLUID_INPUT_B;
    }

    public List<dev.architectury.fluid.FluidStack> getFluidOutputs() {
        return this.OUTPUT_MULTI_FLUID;
    }
}
