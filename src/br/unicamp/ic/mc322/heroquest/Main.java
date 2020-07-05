package br.unicamp.ic.mc322.heroquest;

import br.unicamp.ic.mc322.heroquest.loop.GameLoop;
import br.unicamp.ic.mc322.heroquest.map.MapManager;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.loader.CorruptedConfigurationFileException;
import br.unicamp.ic.mc322.heroquest.map.view.TerminalViewer;
import br.unicamp.ic.mc322.heroquest.walker.hero.Wizard;
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
        MapManager manager = new MapManager();

        try {
            Map map = manager.load("default.map");
            TerminalViewer viewer = new TerminalViewer(map);

            ArrayList<WalkerManager> heroes = new ArrayList<>();
            ArrayList<WalkerManager> monster = new ArrayList<>();
            heroes.add(new WalkerPlayer(map));
            Wizard wizard = new Wizard(heroes.get(0), "Gandalf");

            monster.add(new WalkerAI(map, new Follower(), new Bloodthirsty()));
            SkeletonWizard skeletonWizard = new SkeletonWizard(monster.get(0));

            monster.add(new WalkerAI(map, new Follower(), new Bloodthirsty()));
            SkeletonWizard skeletonWizard2 = new SkeletonWizard(monster.get(1));

            map.add(wizard, new Coordinate(1, 1));
            map.add(skeletonWizard, new Coordinate(5,3));
            map.add(skeletonWizard2, new Coordinate(9,9));

            viewer.display(null);
            GameLoop gameLoop = new GameLoop(map, heroes, monster);
            gameLoop.run();

        } catch (FileNotFoundException ex) {
            System.err.println("Configuration file not found...");
        } catch (CorruptedConfigurationFileException ex) {
            System.err.println("Configuration file is corrupted...");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }
}
