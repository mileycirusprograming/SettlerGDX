package Logic;

/**
 * Created by landfried on 01.03.17.
 */
public class Break {

    public static boolean active = false;

    public static void toggle() {
        active = !active;
    }

    public static void stop() {
        if (active)
            active = false;
    }
}
