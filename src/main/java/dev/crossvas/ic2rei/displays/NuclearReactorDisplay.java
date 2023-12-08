package dev.crossvas.ic2rei.displays;

import dev.crossvas.ic2rei.recipes.NuclearReactorScheme;
import dev.crossvas.ic2rei.utils.CategoryIDs;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import java.util.Collections;
import java.util.List;

public class NuclearReactorDisplay extends BasicDisplay {

    private NuclearReactorScheme SCHEME;

    public NuclearReactorDisplay(NuclearReactorScheme scheme) {
        this(Collections.singletonList(EntryIngredients.ofItemStacks(scheme.getRectorComponents())), List.of());
        this.SCHEME = scheme;
    }

    public NuclearReactorDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return CategoryIDs.NUCLEAR_REACTOR;
    }

    public NuclearReactorScheme getScheme() {
        return this.SCHEME;
    }
}
