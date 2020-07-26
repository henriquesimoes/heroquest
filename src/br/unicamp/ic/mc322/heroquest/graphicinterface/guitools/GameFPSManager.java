package br.unicamp.ic.mc322.heroquest.graphicinterface.guitools;

public class GameFPSManager {
    private final double ONE_SECOND_IN_NANOSECONDS = 1000000000;
    private final double FRAMES_PER_SECOND = 7.0;
    private final double TIME_PER_FRAME = ONE_SECOND_IN_NANOSECONDS / FRAMES_PER_SECOND;

    private double lastUpdateTime = System.nanoTime();
    private double now = System.nanoTime();

    public boolean shouldUpdate() {
        now = System.nanoTime();

        if (now - lastUpdateTime >= TIME_PER_FRAME) {
            lastUpdateTime = now;
            return true;
        }

        return false;
    }
}
