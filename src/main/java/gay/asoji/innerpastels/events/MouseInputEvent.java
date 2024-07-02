package gay.asoji.innerpastels.events;

/*
 * Taken from Deftu's DeftuLib with permission, and have explicit permission from Deftu himself to be excluded from
 * DeftuLib's LGPLv3 license.
 *
 * src: https://github.com/Deftu/DeftuLib/blob/main/src/main/java/dev/deftu/lib/events/MouseInputEvent.java
 */

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

@FunctionalInterface
public interface MouseInputEvent {
    Event<MouseInputEvent> EVENT = EventFactory.createArrayBacked(MouseInputEvent.class, (listeners) -> (button, action, mods) -> {
        for (MouseInputEvent listener : listeners) {
            listener.onMouseInput(button, action, mods);
        }
    });

    void onMouseInput(int button, InputAction action, int mods);
}
