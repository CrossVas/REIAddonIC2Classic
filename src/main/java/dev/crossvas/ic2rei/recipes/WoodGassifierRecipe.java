package dev.crossvas.ic2rei.recipes;

import dev.architectury.fluid.FluidStack;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class WoodGassifierRecipe {

    private final List<ItemStack> INPUTS;
    private final ItemStack OUTPUT;
    private final FluidStack FLUID_OUTPUT;

    public WoodGassifierRecipe(List<ItemStack> inputs, ItemStack output, FluidStack fluid) {
        this.INPUTS = inputs;
        this.OUTPUT = output;
        this.FLUID_OUTPUT = fluid;
    }

    public List<ItemStack> getInputs() {
        return this.INPUTS;
    }

    public ItemStack getOutput() {
        return this.OUTPUT;
    }

    public FluidStack getFluidOutput() {
        return this.FLUID_OUTPUT;
    }
}
