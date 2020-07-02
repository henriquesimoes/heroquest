package br.unicamp.ic.mc322.heroquest.loop;

import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

import java.util.ArrayList;
import java.util.HashSet;

public class GameLoop implements GameListener {
    HashSet<WalkerManager> heroes, monsters, heroesAlive, monstersAlive;
    boolean running;
    Map map;

    public GameLoop(Map map, ArrayList<WalkerManager> heroes, ArrayList<WalkerManager> monsters) {
        this.map = map;
        this.heroes = new HashSet<>();
        this.heroesAlive = new HashSet<>();
        this.monsters = new HashSet<>();
        this.monstersAlive = new HashSet<>();

        this.heroes.addAll(heroes);
        this.heroesAlive.addAll(heroes);
        this.monsters.addAll(monsters);
        this.monstersAlive.addAll(monsters);

        GameMonitor gameMonitor = GameMonitor.getInstance();
        gameMonitor.addListener(this);
        gameMonitor.addListener(map);
    }

    public void run() {
        running = true;
        while (running) {
            playTurn();
            if (isEndGame())
                running = false;
        }
        notifyEndGameToHeroes();
    }

    private void notifyEndGameToHeroes() {
        if (heroesAlive.size() == 0 && monstersAlive.size() == 0) {
            for (WalkerManager manager : heroes)
                manager.showMessage("Game Draw");
        } else if (heroesAlive.size() == 0) {
            for (WalkerManager manager : heroes)
                manager.showMessage("Your Team Lost");
        } else {
            for (WalkerManager manager : heroes)
                manager.showMessage("Your Team Wins");
        }
    }

    private boolean isEndGame() {
        return heroesAlive.size() == 0 || monstersAlive.size() == 0;
    }

    private void playTurn() {
        HashSet<WalkerManager> cloneHeroesAlive = (HashSet<WalkerManager>) heroesAlive.clone();
        HashSet<WalkerManager> cloneMonstersAlive = (HashSet<WalkerManager>) monstersAlive.clone();

        for (WalkerManager manager : cloneHeroesAlive)
            if (manager.isAlive())
                manager.playTurn();

        /*for (WalkerManager manager : cloneMonstersAlive)
            if (manager.isAlive())
                manager.playTurn();*/
    }

    @Override
    public void notifyWalkerDeath(Walker deadWalker) {
        WalkerManager deadWalkerManager = deadWalker.getManager();
        heroesAlive.remove(deadWalkerManager);
        monstersAlive.remove(deadWalkerManager);

        String message = String.format("%s is dead", deadWalkerManager.getWalkerName());
        for (WalkerManager manager : heroes)
            manager.showMessage(message);
    }

    @Override
    public void notifyWalkerDamage(Walker targetWalker, int damage) {
        WalkerManager targetWalkerManager = targetWalker.getManager();
        String message;
        if(damage == 0)
            message = String.format("The %s defended with success", targetWalkerManager.getWalkerName());
        else
            message = String.format("The %s suffered %d of damage", targetWalkerManager.getWalkerName(), damage);

        for (WalkerManager manager : heroes)
            manager.showMessage(message);
    }
}
