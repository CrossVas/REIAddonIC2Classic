package dev.crossvas.ic2rei.categories;

import com.google.common.collect.Lists;
import dev.crossvas.ic2rei.displays.BaseMachineDisplay;
import dev.crossvas.ic2rei.utils.GuiHelper;
import dev.crossvas.ic2rei.utils.IGuiHelper;
import ic2.core.inventory.gui.IC2Screen;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.client.registry.screen.ExclusionZonesProvider;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;

import java.util.Collection;
import java.util.List;

public class BaseMachineCategory implements DisplayCategory<BaseMachineDisplay>, IGuiHelper, ExclusionZonesProvider<IC2Screen> {

    protected Block BLOCK;
    protected CategoryIdentifier<? extends BaseMachineDisplay> ID;
    protected GuiHelper.ProgressType TYPE;

    public BaseMachineCategory(Block block, CategoryIdentifier<? extends BaseMachineDisplay> id, GuiHelper.ProgressType type) {
        this.BLOCK = block;
        this.ID = id;
        this.TYPE = type;
    }

    @Override
    public CategoryIdentifier<? extends BaseMachineDisplay> getCategoryIdentifier() {
        return this.ID;
    }

    @Override
    public Component getTitle() {
        return this.BLOCK.getName();
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(this.BLOCK);
    }

    @Override
    public List<Widget> setupDisplay(BaseMachineDisplay display, Rectangle bounds) {
        List<Widget> widgets = Lists.newArrayList();
        EntryIngredient input = EntryIngredients.ofIngredient(display.getRecipe().getInput());
        GuiHelper.createRecipeBase(widgets, bounds);
        GuiHelper.addProgressBar(widgets, point(getProgressBarX(bounds), getProgressBarY(bounds)), 3000, this.TYPE);
        GuiHelper.addInputSlot(widgets, adjustedInputPoint(bounds), input);
        GuiHelper.addLargeOutputSlot(widgets, adjustedOutputPoint(bounds), EntryIngredients.ofItemStacks(display.getRecipe().getOutputs()));
        return widgets;
    }

    @Override
    public int getDisplayWidth(BaseMachineDisplay display) {
        return getWidth();
    }

    @Override
    public int getDisplayHeight() {
        return getHeight();
    }

    @Override
    public Collection<Rectangle> provide(IC2Screen screen) {
        return null;
    }
}
