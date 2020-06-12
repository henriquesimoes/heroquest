package br.unicamp.ic.mc322.heroquest.loop;

import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

import java.util.ArrayList;
import java.util.TreeSet;

public class GameLoop implements DeathListener {
    TreeSet<WalkerManager> heroes, monsters, heroesAlive, monstersAlive;
    boolean running;

    public GameLoop(ArrayList<WalkerManager> heroes, ArrayList<WalkerManager> monsters) {
        this.heroes = new TreeSet<>();
        this.heroesAlive = new TreeSet<>();
        this.monsters = new TreeSet<>();
        this.monstersAlive = new TreeSet<>();

        this.heroes.addAll(heroes);
        this.heroesAlive.addAll(heroes);
        this.monsters.addAll(monsters);
        this.monstersAlive.addAll(monsters);
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
                manager.showMessage("Your Team Won");
        }
    }

    private boolean isEndGame() {
        return heroesAlive.size() == 0 || monstersAlive.size() == 0;
    }

    private void playTurn() {
        TreeSet<WalkerManager> cloneHeroesAlive = (TreeSet<WalkerManager>) heroesAlive.clone();
        TreeSet<WalkerManager> cloneMonstersAlive = (TreeSet<WalkerManager>) monstersAlive.clone();

        for (WalkerManager manager : cloneHeroesAlive)
            if (manager.isAlive())
                manager.playTurn();

        for (WalkerManager manager : cloneMonstersAlive)
            if (manager.isAlive())
                manager.playTurn();
    }


    @Override
    public void notifyWalkerDeath(WalkerManager walkerManager) {
        heroesAlive.remove(walkerManager);
        monstersAlive.remove(walkerManager);
    }
}
