package br.unicamp.ic.mc322.heroquest.map.generator.pathgenerator;

import br.unicamp.ic.mc322.heroquest.map.core.RoomStructure;

public class PathGenerator {
    private char[][] grid;
    private java.util.Map<String, RoomStructure> rooms;

    public PathGenerator(char[][] grid, java.util.Map<String, RoomStructure> rooms) {
        this.grid = grid;
        this.rooms = rooms;
    }


}