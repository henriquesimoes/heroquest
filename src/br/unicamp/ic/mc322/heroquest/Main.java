package br.unicamp.ic.mc322.heroquest;

import br.unicamp.ic.mc322.heroquest.map.MapManager;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.generator.GridContainer;
import br.unicamp.ic.mc322.heroquest.map.generator.MapGenerator;
import br.unicamp.ic.mc322.heroquest.map.loader.CorruptedConfigurationFileException;
import br.unicamp.ic.mc322.heroquest.map.view.TerminalViewer;
import br.unicamp.ic.mc322.heroquest.util.tree.BSPTree;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        BSPTree teste = new BSPTree(new GridContainer(100, 30, 0, 0));

        for (int i = 0; i < 4; i++)
            teste.splitGrid();

        ArrayList<GridContainer> t = teste.getPartitionedGrid();

        for (GridContainer container:
             t) {
            System.out.println(container.toString());
        }



//        MapManager manager = new MapManager();
//        TerminalViewer viewer = new TerminalViewer();
//
//        try {
//            Map map = manager.load("default.map");
//
//            viewer.display(map, null);
//        } catch (FileNotFoundException ex) {
//            System.err.println("Configuration file not found...");
//        } catch (CorruptedConfigurationFileException ex) {
//            System.err.println("Configuration file is corrupted...");
//            System.err.println(ex.getMessage());
//        }
    }
}
