package dev.crossvas.ic2rei.utils;

import com.mojang.blaze3d.systems.RenderSystem;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.widgets.Slot;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.impl.client.gui.widget.EntryWidget;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class GuiHelper {

    public static void createRecipeBase(List<Widget> list, Rectangle bounds) {
        list.add(Widgets.createRecipeBase(bounds));
    }

    public static void addProgressBar(List<Widget> list, Point point, double animationTime, ProgressType type) {
        list.add(createProgressBar(point.getX(), point.getY(), animationTime, type));
    }

    private static Widget createProgressBar(int x, int y, double animationTime, ProgressType type) {
        return Widgets.createDrawableWidget((helper, matrices, mouseX, mouseY, delta) -> {
            RenderSystem.setShaderTexture(0, type.texture);
            helper.blit(matrices, x, y, type.x, type.y, type.width, type.height);
            int i = (int) ((System.currentTimeMillis() / animationTime) % 1.0 * type.width);
            if (i < 0) {
                i = 0;
            }
            helper.blit(matrices, x, y, type.xActive, type.yActive, i, type.height);
        });
    }

    public static void addInputSlot(List<Widget> list, Point point, EntryIngredient entry, boolean background) {
        Slot slot = Widgets.createSlot(point).entries(entry).markInput();
        slot.setBackgroundEnabled(background);
        list.add(slot);
    }

    public static void addInputSlot(List<Widget> list, Point point, EntryIngredient entry) {
        list.add(Widgets.createSlot(point).entries(entry).markInput());
    }

    public static void addBaseOutputSlot(List<Widget> list, Point point, EntryIngredient entry) {
        list.add(Widgets.createSlot(point).entries(entry).markOutput());
    }

    public static void addLargeOutputSlot(List<Widget> list, Point point, EntryIngredient entry) {
        list.add(Widgets.createResultSlotBackground(new Point(point.getX(), point.getY())));
        list.add(Widgets.createSlot(point).entries(entry).markOutput().disableBackground());
    }

    public static void addTank(List<Widget> list, int x, int y, EntryIngredient entry) {
        // tank base
        list.add(Widgets.createTexturedWidget(ProgressType.REFINERY.texture, x, y, ProgressType.REFINERY.x, ProgressType.REFINERY.y, ProgressType.REFINERY.width, ProgressType.REFINERY.height));
        list.add(new EntryWidget(new Rectangle(x, y, ProgressType.REFINERY.width, ProgressType.REFINERY.height)).entries(entry).disableBackground().disableHighlight());
        // tank overlay
        list.add(Widgets.createTexturedWidget(ProgressType.REFINERY.texture, x + 1, y + 1, 176, 31, 16, 58));
    }

    public static void addLabel(List<Widget> widgetList, Point point, Component text) {
        widgetList.add(Widgets.createLabel(point, text).shadow(false));
    }

    public static void addLabelRight(List<Widget> widgetList, Point point, Component text) {
        widgetList.add(Widgets.createLabel(point, text).shadow(false).rightAligned());
    }

    public static void addLabelLeft(List<Widget> widgetList, Point point, Component text) {
        widgetList.add(Widgets.createLabel(point, text).shadow(false).leftAligned());
    }

    public enum ProgressType {
        MACERATOR("machines/lv/gui_macerator", 79, 34, 176, 14, 24, 17),
        EXTRACTOR("machines/lv/gui_extractor", 79, 34, 176, 14, 24, 17),
        COMPRESSOR("machines/lv/gui_compressor", 79, 34, 176, 14, 24, 17),
        SAWMILL("machines/lv/gui_sawmill", 79, 34, 176, 14, 24, 17),
        RECYCLER("machines/lv/gui_recycler", 79, 34, 176, 14, 24, 17),
        EARTH_EXTRACTOR("machines/lv/gui_rare_earth_extractor", 79, 34, 176, 14, 24, 17),
        SMELTER("machines/lv/gui_electric_furnace", 79, 34, 176, 14, 24, 17),
        ELECTROLYZER("machines/lv/gui_electrolyzer", 79, 34, 176, 14, 24, 17),
        CANNER("machines/lv/gui_canner", 74, 36, 176, 15, 34, 13),
        REFINERY("machines/mv/gui_refinery", 35, 17, 0, 0, 18, 60),
        ENRICHER("machines/hv/gui_uranium_enricher", 97, 35, 176, 36, 52, 34),
        GENERATOR("generators/gui_generator", 94, 35, 176, 14, 24, 17);

        public final int x;
        public final int y;
        public final int xActive;
        public final int yActive;
        public final int width;
        public final int height;
        public final ResourceLocation texture;

        ProgressType(String texture, int x, int y, int xActive, int yActive, int width, int height) {
            this.x = x;
            this.y = y;
            this.xActive = xActive;
            this.yActive = yActive;
            this.width = width;
            this.height = height;
            this.texture = new ResourceLocation("ic2", "textures/gui_sprites/blocks/" + texture + ".png");

        }
    }
}
