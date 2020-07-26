package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.gamerunning;

public class CentralizeMatrix {
    static char[][] getCentralizeMatrix(char[][] m, int radius, int referenceX, int referenceY) {
        char[][] ret = new char[2 * radius + 1][2 * radius + 1];

        for (int i = 0; i < ret.length; i++) {
            for (int j = 0; j < ret.length; j++) {
                int x = referenceX - radius + j;
                int y = referenceY - radius + i;
                if (x >= 0 && y >= 0 && x < m[0].length && y < m.length)
                    ret[i][j] = m[y][x];
                else
                    ret[i][j] = '?';
            }
        }
        return ret;
    }
}
