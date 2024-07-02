package dev.crossvas.ic2rei.categories;

import com.google.common.collect.Lists;
import dev.crossvas.ic2rei.displays.BaseMachineDisplay;
import dev.crossvas.ic2rei.utils.GuiHelper;
import dev.crossvas.ic2rei.utils.IGuiHelper;
import ic2.core.utils.helpers.Formatters;
import me.shedaniel.math.Dimension;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class BaseMachineCategory implements DisplayCategory<BaseMachineDisplay>, IGuiHelper {

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
        if (display.getRecipe().getChance() > 0) {
            Component text = Component.literal(Formatters.XP_FORMAT.format(display.getRecipe().getChance() * 100.0F) + "%");
            GuiHelper.addLabel(widgets, point(bounds.getCenterX(), bounds.getMaxY() - 5 - lineHeight), text);
            int width = font.width(text.getString());
            widgets.add(Widgets.createTooltip(new Rectangle(point(bounds.getCenterX() - width / 2, bounds.getMaxY() - 5 - lineHeight), new Dimension(width, lineHeight)), format("jei.ic2.recycler.chance")));
        }
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
}
