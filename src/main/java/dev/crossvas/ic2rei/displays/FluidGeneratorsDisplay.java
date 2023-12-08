package dev.crossvas.ic2rei.displays;

import dev.crossvas.ic2rei.recipes.FluidGeneratorRecipe;
import dev.crossvas.ic2rei.utils.CategoryIDs;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import java.util.Collections;
import java.util.List;

public class FluidGeneratorsDisplay {

    public static class FluidGeneratorDisplay extends BasicDisplay {

        private FluidGeneratorRecipe RECIPE;
        private boolean GEOTHERMAL;

        public FluidGeneratorDisplay(FluidGeneratorRecipe recipe, boolean geothermal) {
            this(Collections.singletonList(EntryIngredients.of(recipe.getFluidFuel().getFluid())), List.of());
            this.RECIPE = recipe;
            this.GEOTHERMAL = geothermal;
        }

        public FluidGeneratorDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
            super(inputs, outputs);
        }

        public FluidGeneratorRecipe getRecipe() {
            return this.RECIPE;
        }

        @Override
        public CategoryIdentifier<?> getCategoryIdentifier() {
            return this.GEOTHERMAL ? CategoryIDs.GEOTHERMAL_GENERATOR : CategoryIDs.FLUID_GENERATOR;
        }
    }
}
