package dev.crossvas.ic2rei.recipes;

import ic2.api.recipes.registries.IFluidFuelRegistry;
import ic2.core.platform.registries.IC2Fluids;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class FluidGeneratorRecipe {

    private final FluidStack FLUID_FUEL;
    private final int BURNING_TIME;
    private final int PRODUCTION;

    public FluidGeneratorRecipe(IFluidFuelRegistry.FuelEntry fuel) {
        this.FLUID_FUEL = new FluidStack(fuel.getFluid(), 1000);
        this.BURNING_TIME = fuel.getTicksPerBucket();
        this.PRODUCTION = fuel.getEuPerTick();
    }

    public FluidGeneratorRecipe(FluidStack fuel, int burningTime, int production) {
        this.FLUID_FUEL = fuel;
        this.BURNING_TIME = burningTime;
        this.PRODUCTION = production;
    }

    public FluidStack getFluidFuel() {
        return this.FLUID_FUEL;
    }

    public int getBurningTime() {
        return this.BURNING_TIME;
    }

    public int getProduction() {
        return this.PRODUCTION;
    }

    public static List<FluidGeneratorRecipe> getGeothermalRecipes() {
        List<FluidGeneratorRecipe> recipes = new ArrayList<>();
        recipes.add(new FluidGeneratorRecipe(new FluidStack(Fluids.LAVA, 1000), 1000, 20));
        recipes.add(new FluidGeneratorRecipe(new FluidStack(IC2Fluids.BLAZING_LAVA, 1000), 5000, 20));
        return recipes;
    }
}
