package br.unicamp.ic.mc322.heroquest.graphicinterface;

import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.ScreenStates;

public class GameFPSManager {
    final double ONE_SECOND_IN_NANOSECONDS = 1000000000;
    final double FRAMES_PER_SECOND = 65.0;
    final double TIME_PER_FRAME = ONE_SECOND_IN_NANOSECONDS / FRAMES_PER_SECOND;

    double lastUpdateTime = System.nanoTime();
    double now = System.nanoTime();
    double firstUp = lastUpdateTime;
    int cnt = 0;

    public boolean shouldUpdate() {
        now = System.nanoTime();

        if (now - lastUpdateTime >= TIME_PER_FRAME) {
            lastUpdateTime = now;
            cnt++;

            if (lastUpdateTime - firstUp >= ONE_SECOND_IN_NANOSECONDS) {
                System.out.println(cnt + ScreenStates.LIST_OF_MAPS.toString());
                cnt = 0;
                firstUp = lastUpdateTime;
            }
            return true;
        }

        return false;
    }
}
