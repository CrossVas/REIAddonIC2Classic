package dev.crossvas.ic2rei.categories;

import dev.crossvas.ic2rei.displays.NuclearReactorDisplay;
import dev.crossvas.ic2rei.utils.CategoryIDs;
import dev.crossvas.ic2rei.utils.GuiHelper;
import dev.crossvas.ic2rei.utils.IGuiHelper;
import ic2.core.platform.registries.IC2Blocks;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.Iterator;
import java.util.List;

public class NuclearReactorCategory implements DisplayCategory<NuclearReactorDisplay>, IGuiHelper {

    public static final NuclearReactorCategory INSTANCE = new NuclearReactorCategory();

    private static final ResourceLocation TEXTURE = new ResourceLocation("ic2", "textures/gui_sprites/misc/jei_reactor.png");

    @Override
    public CategoryIdentifier<? extends NuclearReactorDisplay> getCategoryIdentifier() {
        return CategoryIDs.NUCLEAR_REACTOR;
    }

    @Override
    public Component getTitle() {
        return IC2Blocks.NUCLEAR_REACTOR.getName();
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(IC2Blocks.NUCLEAR_REACTOR);
    }

    @Override
    public List<Widget> setupDisplay(NuclearReactorDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ObjectArrayList<>();
        GuiHelper.createRecipeBase(widgets, bounds);
        int reactorSize = display.getScheme().setup.chambersRequired + 3;
        int startX = bounds.getCenterX() - 9 * reactorSize;
        int startY = bounds.getMinY() + offset;

        int y;
        for(int i = 0; i < 54; ++i) {
            y = i % 9;
            if (y < reactorSize) {
                slot(widgets, startX + 18 * y, startY + 18 * (i / 9), EntryIngredients.of(display.getScheme().setup.items.getStackInSlot(i)));
            }
        }
        Component copyComp = Component.translatable("jei.ic2.reacotr.copy").withStyle(ChatFormatting.UNDERLINE);
        widgets.add(Widgets.createLabel(point(bounds.getMaxX() - offset, bounds.getMinY() + 108 + lineHeight), copyComp).rightAligned().clickable().onClick(label -> {
            Minecraft.getInstance().keyboardHandler.setClipboard(display.getScheme().data);
            Minecraft.getInstance().player.displayClientMessage(Component.translatable("jei.ic2.reactor.copied"), false);
        }));

        y = bounds.getMinY() + 108 + lineHeight;
        for(Iterator<Component> components = display.getScheme().text.iterator(); components.hasNext(); y += 10) {
            Component process = components.next();
            GuiHelper.addLabelLeft(widgets, point(bounds.getMinX() + offset, y), process.copy().withStyle(ChatFormatting.BLACK));
        }

        return widgets;
    }

    @Override
    public int getDisplayWidth(NuclearReactorDisplay display) {
        return 176;
    }

    @Override
    public int getDisplayHeight() {
        return 190;
    }

    private void slot(List<Widget> list, int x, int y, EntryIngredient entry) {
        list.add(Widgets.createTexturedWidget(TEXTURE, x, y, 165, 0, 18, 18));
        list.add(Widgets.createSlot(point(x + 1, y + 1)).entries(entry).markInput().disableBackground());
    }
}
