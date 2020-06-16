package br.unicamp.ic.mc322.heroquest;

import br.unicamp.ic.mc322.heroquest.loop.GameLoop;
import br.unicamp.ic.mc322.heroquest.map.MapManager;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.generator.GridContainer;
import br.unicamp.ic.mc322.heroquest.map.generator.MapGenerator;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.loader.CorruptedConfigurationFileException;
import br.unicamp.ic.mc322.heroquest.map.view.TerminalViewer;
import br.unicamp.ic.mc322.heroquest.util.tree.BSPTree;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.hero.Barbarian;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;
import br.unicamp.ic.mc322.heroquest.walker.manager.ai.WalkerAI;
import br.unicamp.ic.mc322.heroquest.walker.manager.ai.attack.Bloodthirsty;
import br.unicamp.ic.mc322.heroquest.walker.manager.ai.movement.Follower;
import br.unicamp.ic.mc322.heroquest.walker.manager.player.WalkerPlayer;
import br.unicamp.ic.mc322.heroquest.walker.monster.SkeletonWizard;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        /*BSPTree teste = new BSPTree(new GridContainer(100, 30, 0, 0));

        for (int i = 0; i < 4; i++)
            teste.splitGrid();

        ArrayList<GridContainer> t = teste.getPartitionedGrid();

        for (GridContainer container:
             t) {
            System.out.println(container.toString());
        }*/



        MapManager manager = new MapManager();
        TerminalViewer viewer = new TerminalViewer();

        try {
            Map map = manager.load("default.map");

            ArrayList<WalkerManager> heroes = new ArrayList<>();
            ArrayList<WalkerManager> monster = new ArrayList<>();
            heroes.add(new WalkerPlayer(map));
            Barbarian barbarian = new Barbarian(heroes.get(0));
            monster.add(new WalkerAI(map, new Follower(), new Bloodthirsty()));
            SkeletonWizard skeletonWizard = new SkeletonWizard(monster.get(0));
            map.add(barbarian, new Coordinate(1, 1));
            map.add(skeletonWizard, new Coordinate(9,9));
            viewer.display(map, null);
            GameLoop gameLoop = new GameLoop(heroes, monster);
            gameLoop.run();

            viewer.display(map, null);
        } catch (FileNotFoundException ex) {
            System.err.println("Configuration file not found...");
        } catch (CorruptedConfigurationFileException ex) {
            System.err.println("Configuration file is corrupted...");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }
}
