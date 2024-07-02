package gay.asoji.innerpastels.events;

/*
 * Taken from Deftu's DeftuLib with permission, and have explicit permission from Deftu himself to be excluded from
 * DeftuLib's LGPLv3 license.
 *
 * src: https://github.com/Deftu/DeftuLib/blob/main/src/main/java/dev/deftu/lib/events/InputAction.java
 */

public enum InputAction {
    PRESS,
    RELEASE;

    public static InputAction from(int action) {
        return action == 1 ? PRESS : RELEASE;
    }
}
