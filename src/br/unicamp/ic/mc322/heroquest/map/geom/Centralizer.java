package br.unicamp.ic.mc322.heroquest.map.geom;

public class Centralizer {
    /**
     * Returns a map representation centralized on the given reference point.
     *
     * @param m matrix to be centered
     * @param radius resulting matrix radius
     * @param reference center point
     * @param nullChar character to be filled outside the map
     * @return
     */
    public static char[][] getCentralizedMatrix(char[][] m, int radius, Coordinate reference, char nullChar) {
        char[][] ret = new char[2 * radius + 1][2 * radius + 1];

        for (int i = 0; i < ret.length; i++) {
            for (int j = 0; j < ret[i].length; j++) {
                int x = reference.getX() - radius + j;
                int y = reference.getY() - radius + i;
                if (x >= 0 && y >= 0 && x < m[0].length && y < m.length)
                    ret[i][j] = m[y][x];
                else
                    ret[i][j] = nullChar;
            }
        }
        return ret;
    }
}
