package dev.crossvas.ic2rei.handlers;

import ic2.core.block.generators.components.ReactorComponent;
import ic2.core.block.machines.containers.hv.VillagerOMatContainer;
import ic2.core.block.machines.containers.mv.ReactorPlannerContainer;
import ic2.core.block.personal.container.PersonalChestContainer;
import ic2.core.block.personal.container.PersonalTankContainer;
import ic2.core.inventory.gui.ComponentContainerScreen;
import ic2.core.inventory.gui.IC2Screen;
import ic2.core.inventory.gui.components.GuiWidget;
import ic2.core.utils.math.geometry.Box2i;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.registry.screen.ExclusionZonesProvider;

import java.util.Collection;
import java.util.List;

public class ExclusionZoneHandler implements ExclusionZonesProvider<IC2Screen> {

    @Override
    public Collection<Rectangle> provide(IC2Screen screen) {
        List<Rectangle> areas = new ObjectArrayList<>();
        if (screen instanceof ComponentContainerScreen componentScreen) {
            List<GuiWidget> widgets = componentScreen.getComponents();
            if (!widgets.isEmpty()) {
                for (GuiWidget widget : widgets) {
                    Box2i widgetBox = widget.getBox();
                    if (widgetBox != Box2i.EMPTY_BOX && widget.isVisible()) {
                        areas.add(new Rectangle(screen.getGuiLeft() + widgetBox.getX(), screen.getGuiTop() + widgetBox.getY(), widgetBox.getWidth(), widgetBox.getHeight()));
                    }
                    if (widget instanceof ReactorComponent) {
                        areas.add(new Rectangle(screen.getGuiLeft() - 16, screen.getGuiTop(), 16, 16));
                    }
                }
            }
        }
        if (screen.getMenu() instanceof ReactorPlannerContainer) {
            areas.add(new Rectangle(screen.getGuiLeft() - 32, screen.getGuiTop(), 325, 212));
        }
        if (screen.getMenu() instanceof VillagerOMatContainer) {
            areas.add(new Rectangle(screen.getGuiLeft() - 118, screen.getGuiTop(), 201, 201));
        }
        if (screen.getMenu() instanceof PersonalChestContainer || screen.getMenu() instanceof PersonalTankContainer) {
            areas.add(new Rectangle(screen.getGuiLeft(), screen.getGuiTop(), 190, 32));
        }
        return areas;
    }
}
