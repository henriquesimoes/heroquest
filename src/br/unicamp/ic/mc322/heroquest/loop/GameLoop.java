package br.unicamp.ic.mc322.heroquest.loop;

import br.unicamp.ic.mc322.heroquest.map.core.AbstractMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.objects.FixedObject;
import br.unicamp.ic.mc322.heroquest.map.objects.StructuralObject;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.WalkerManager;

import java.util.HashSet;

public class GameLoop implements GameListener, AbstractMapObjectVisitor {
    private HashSet<WalkerManager> heroes, monsters, aliveHeroes, aliveMonsters;

    public GameLoop(Map map) {
        this.heroes = new HashSet<>();
        this.aliveHeroes = new HashSet<>();
        this.monsters = new HashSet<>();
        this.aliveMonsters = new HashSet<>();

        map.accept(this);

        GameMonitor gameMonitor = GameMonitor.getInstance();
        gameMonitor.addListener(this);
        gameMonitor.addListener(map);
    }

    public void run() {
        boolean running = true;
        while (running) {
            playTurn();
            if (isEndGame())
                running = false;
        }
        notifyEndGameToHeroes();
    }

    private void notifyEndGameToHeroes() {
        if (aliveHeroes.size() == 0 && aliveMonsters.size() == 0) {
            for (WalkerManager manager : heroes)
                manager.showMessage("Game Draw");
        } else if (aliveHeroes.size() == 0) {
            for (WalkerManager manager : heroes)
                manager.showMessage("Your Team Lost");
        } else {
            for (WalkerManager manager : heroes)
                manager.showMessage("Your Team Wins");
        }
    }

    private boolean isEndGame() {
        return aliveHeroes.size() == 0 || aliveMonsters.size() == 0;
    }

    private void playTurn() {
        HashSet<WalkerManager> aliveHeroesClone = (HashSet<WalkerManager>) aliveHeroes.clone();
        HashSet<WalkerManager> aliveMonstersClone = (HashSet<WalkerManager>) aliveMonsters.clone();

        for (WalkerManager manager : aliveHeroesClone)
            if (manager.isAlive())
                manager.playTurn();

        for (WalkerManager manager : aliveMonstersClone)
            if (manager.isAlive())
                manager.playTurn();
    }

    @Override
    public void notifyWalkerDeath(Walker deadWalker) {
        WalkerManager deadWalkerManager = deadWalker.getManager();
        aliveHeroes.remove(deadWalkerManager);
        aliveMonsters.remove(deadWalkerManager);

        String message = String.format("%s is dead", deadWalkerManager.getWalkerName());
        for (WalkerManager manager : heroes)
            manager.showMessage(message);
    }

    @Override
    public void notifyWalkerDamage(Walker targetWalker, int damage) {
        WalkerManager targetWalkerManager = targetWalker.getManager();
        String message;
        if (damage == 0)
            message = String.format("%s defended with success", targetWalkerManager.getWalkerName());
        else
            message = String.format("%s suffered %d of damage", targetWalkerManager.getWalkerName(), damage);

        for (WalkerManager manager : heroes)
            manager.showMessage(message);
    }

    @Override
    public void visit(Walker walker) {
        WalkerManager manager = walker.getManager();
        switch (walker.getTeam()) {
            case HEROES:
                heroes.add(manager);
                aliveHeroes.add(manager);
                break;
            case MORCAR:
                monsters.add(manager);
                aliveMonsters.add(manager);
                break;
            default:
                throw new UnsupportedClassVersionError();
        }
    }

    @Override
    public void visit(StructuralObject structuralObject) {}

    @Override
    public void visit(FixedObject fixedObject) {}
}
