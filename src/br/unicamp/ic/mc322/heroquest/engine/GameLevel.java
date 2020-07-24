package br.unicamp.ic.mc322.heroquest.engine;

public enum GameLevel implements LevelConfiguration {
    EASY {
        @Override
        public int getNumberOfMonsters(int area) {
            return (int) Math.max(1, Math.floor(0.003283 * area + 2.17f));
        }

        @Override
        public String toString() {
            return "Easy";
        }
    },
    MEDIUM {
        @Override
        public int getNumberOfMonsters(int area) {
            return (int) Math.max(1, Math.floor(0.004363f * area + 4.90f));
        }

        @Override
        public String toString() {
            return "Medium";
        }
    },
    HARD {
        @Override
        public int getNumberOfMonsters(int area) {
            return (int) Math.max(1, Math.floor(0.008725f * area + 9.81f));
        }

        @Override
        public String toString() {
            return "Hard";
        }
    }
}
