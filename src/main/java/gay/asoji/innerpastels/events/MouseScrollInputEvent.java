package gay.asoji.innerpastels.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface MouseScrollInputEvent {
    Event<MouseScrollInputEvent> EVENT = EventFactory.createArrayBacked(MouseScrollInputEvent.class, (listeners) -> (xOffset, yOffset) -> {
        for (MouseScrollInputEvent listener : listeners) {
            listener.onScroll(xOffset, yOffset);
        }
    });

    void onScroll(double xOffset, double yOffset);
}
