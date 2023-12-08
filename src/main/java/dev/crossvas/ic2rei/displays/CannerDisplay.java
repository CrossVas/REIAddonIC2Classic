package dev.crossvas.ic2rei.displays;

import dev.crossvas.ic2rei.recipes.CannerRecipe;
import dev.crossvas.ic2rei.utils.CategoryIDs;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CannerDisplay {

    public static class CannerRepairDisplay extends BasicDisplay {

        private CannerRecipe RECIPE;

        private ItemStack INPUT_A;
        private ItemStack INPUT_B;
        private ItemStack OUTPUT;

        public CannerRepairDisplay(CannerRecipe recipe) {
            this(Arrays.asList(EntryIngredients.of(recipe.getContainer()),
                            EntryIngredients.ofIngredient(recipe.getRepair().getA().asIngredient())),
                            Collections.singletonList(EntryIngredients.of(recipe.getContainer()))
            );
            this.INPUT_A = recipe.getContainer();
            this.INPUT_B = recipe.getRepair().getA().getComponents().get(0);
            this.OUTPUT = recipe.getContainer();
            this.RECIPE = recipe;
        }

        public CannerRepairDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
            super(inputs, outputs);
        }

        @Override
        public CategoryIdentifier<?> getCategoryIdentifier() {
            return CategoryIDs.CANNER_REPAIR;
        }

        public ItemStack getInputA() {
            return this.INPUT_A;
        }

        public ItemStack getInputB() {
            return this.INPUT_B;
        }

        public ItemStack getOutput() {
            return this.OUTPUT;
        }

        public CannerRecipe getRecipe() {
            return this.RECIPE;
        }
    }

    public static class CannerRefuelDisplay extends BasicDisplay {

        private CannerRecipe RECIPE;
        private ItemStack INPUT_A;
        private ItemStack INPUT_B;
        private ItemStack OUTPUT;

        public CannerRefuelDisplay(CannerRecipe recipe) {
            this(Arrays.asList(EntryIngredients.of(recipe.getContainer()),
                            EntryIngredients.of(recipe.getFuels().getStack())),
                    Collections.singletonList(EntryIngredients.of(recipe.getContainer()))
            );
            this.INPUT_A = recipe.getContainer();
            this.INPUT_B = recipe.getFuels().getStack();
            this.OUTPUT = recipe.getContainer();
            this.RECIPE = recipe;
        }

        public CannerRefuelDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
            super(inputs, outputs);
        }

        @Override
        public CategoryIdentifier<?> getCategoryIdentifier() {
            return CategoryIDs.CANNER_REFUEL;
        }

        public ItemStack getInputA() {
            return this.INPUT_A;
        }

        public ItemStack getInputB() {
            return this.INPUT_B;
        }

        public ItemStack getOutput() {
            return this.OUTPUT;
        }

        public CannerRecipe getRecipe() {
            return this.RECIPE;
        }
    }

    public static class CannerRefillDisplay extends BasicDisplay {

        private ItemStack INPUT_A;
        private ItemStack INPUT_B;
        private ItemStack OUTPUT;

        public CannerRefillDisplay(CannerRecipe recipe) {
            this(Arrays.asList(EntryIngredients.of(recipe.getContainer()),
                            EntryIngredients.of(recipe.getFilling().getA().getComponents().get(0))),
                    Collections.singletonList(EntryIngredients.of(recipe.getFilling().getB()))
            );
            this.INPUT_A = recipe.getContainer();
            this.INPUT_B = recipe.getFilling().getA().getComponents().get(0);
            this.OUTPUT = recipe.getFilling().getB();
        }

        public CannerRefillDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
            super(inputs, outputs);
        }

        @Override
        public CategoryIdentifier<?> getCategoryIdentifier() {
            return CategoryIDs.CANNER_REFILL;
        }

        public ItemStack getInputA() {
            return this.INPUT_A;
        }

        public ItemStack getInputB() {
            return this.INPUT_B;
        }

        public ItemStack getOutput() {
            return this.OUTPUT;
        }
    }
 }
