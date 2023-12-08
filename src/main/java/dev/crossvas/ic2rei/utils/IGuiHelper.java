package dev.crossvas.ic2rei.utils;

import ic2.core.IC2;
import ic2.core.inventory.filter.SpecialFilters;
import ic2.core.platform.registries.IC2Items;
import ic2.core.utils.helpers.StackUtil;
import me.shedaniel.math.Dimension;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;

import java.util.List;

public interface IGuiHelper {

    Font font = Minecraft.getInstance().font;
    int lineHeight = font.lineHeight;
    int offset = 5;
    int innerOffset = 3;
    int slotSize = 18;

    Player PLAYER = IC2.PLATFORM.getClientPlayerInstance();

    default void addEUReaderErrorMessage(List<Widget> list, Point point, boolean addMessage, boolean inline) {
        Component error = Component.literal("[X] EU-RAWR!");
        list.add(Widgets.createSlot(point).entries(EntryIngredients.of(IC2Items.EU_READER.getDefaultInstance())).disableBackground());
        list.add(Widgets.createSlot(point).entries(EntryIngredients.of(Items.BARRIER)).disableBackground().notInteractable());
        list.add(Widgets.createTooltip(new Rectangle(point, new Dimension(16, 16)), Component.translatable("rei.info.reader.error")));
        if (addMessage) {
            Point errorPoint = point(inline ? point.getX() - font.width(error.getString()) - innerOffset : point.getX(),
                    inline ? point.getY() : point.getY() + slotSize);
            GuiHelper.addLabelLeft(list, errorPoint, error.copy().withStyle(ChatFormatting.DARK_RED));
        }
    }


    default boolean shouldAddEnergyInfo() {
        return StackUtil.hasHotbarItems(PLAYER, SpecialFilters.EU_READER);
    }

    default int getProgressBarX(Rectangle bounds) {
        return getCenterX(bounds) - 6;
    }

    default int getProgressBarY(Rectangle bounds) {
        return getCenterY(bounds);
    }

    default Point adjustedInputPoint(Rectangle bounds) {
        return point(bounds.getMinX() + offset + lineHeight, getCenterY(bounds));
    }

    default Point adjustedOutputPoint(Rectangle bounds) {
        return point(bounds.getMaxX() - innerOffset - slotSize - lineHeight, getCenterY(bounds));
    }

    default Point adjustedOutputBackPoint(Rectangle bounds) {
        return point(bounds.getMaxX() - slotSize - lineHeight - innerOffset, getCenterY(bounds));
    }

    default int getCenterX(Rectangle bounds) {
        return bounds.getCenterX() - slotSize / 2 + 1;
    }

    default int getCenterY(Rectangle bounds) {
        return bounds.getCenterY() - slotSize / 2 + 1;
    }

    default Point point(int x, int y) {
        return new Point(x, y);
    }

    default MutableComponent format(String text) {
        return Component.translatable(text).withStyle(ChatFormatting.BLACK);
    }

    default MutableComponent format(String text, Object... pArgs) {
        return Component.translatable(text, pArgs).withStyle(ChatFormatting.BLACK);
    }

    default MutableComponent literal(String text) {
        return Component.literal(text).withStyle(ChatFormatting.BLACK);
    }

    default int getWidth() {
        return 96;
    }

    default int getHeight() {
        return 48;
    }
}
