package gay.asoji.innerpastels.events;

/*
 * Taken from Deftu's DeftuLib with permission, and have explicit permission from Deftu himself to be excluded from
 * DeftuLib's LGPLv3 license.
 *
 * src: https://github.com/Deftu/DeftuLib/blob/main/src/main/java/dev/deftu/lib/events/KeyInputEvent.java
 */

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

@FunctionalInterface
public interface KeyInputEvent {
    Event<KeyInputEvent> EVENT = EventFactory.createArrayBacked(KeyInputEvent.class, (listeners) -> (key, action, mods) -> {
        for (KeyInputEvent listener : listeners) {
            listener.onKeyInput(key, action, mods);
        }
    });

    void onKeyInput(int key, InputAction action, int mods);
}
