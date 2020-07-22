package br.unicamp.ic.mc322.heroquest.engine;

import br.unicamp.ic.mc322.heroquest.map.core.AbstractMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.objects.FixedObject;
import br.unicamp.ic.mc322.heroquest.map.objects.StructuralObject;
import br.unicamp.ic.mc322.heroquest.walker.Team;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.WalkerManager;

import java.util.HashSet;
import java.util.LinkedHashMap;

public class GameLoop implements GameListener, AbstractMapObjectVisitor {
    private LinkedHashMap<Team, HashSet<WalkerManager>> managersByTeam, managersAliveByTeam;

    public GameLoop(Map map) {
        this.managersByTeam = new LinkedHashMap<>();
        this.managersAliveByTeam = new LinkedHashMap<>();

        for (Team team : Team.values()) {
            managersByTeam.put(team, new HashSet<>());
            managersAliveByTeam.put(team, new HashSet<>());
        }

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
        notifyEndGame();
    }

    @Override
    public void notifyWalkerDeath(Walker deadWalker) {
        WalkerManager deadWalkerManager = deadWalker.getManager();
        HashSet<WalkerManager> managersAlive = managersAliveByTeam.get(deadWalker.getTeam());
        managersAlive.remove(deadWalkerManager);
        String message = String.format("%s is dead", deadWalkerManager.getWalkerName());
        notifyAllWalkers(message);
    }

    @Override
    public void notifyWalkerDamage(Walker targetWalker, int damage) {
        WalkerManager targetWalkerManager = targetWalker.getManager();
        String message;

        if (damage == 0)
            message = String.format("%s defended with success", targetWalkerManager.getWalkerName());
        else
            message = String.format("%s suffered %d of damage", targetWalkerManager.getWalkerName(), damage);

        notifyAllWalkers(message);
    }

    @Override
    public void add(Walker walker) {
        WalkerManager manager = walker.getManager();
        Team team = walker.getTeam();
        HashSet<WalkerManager> managers = managersByTeam.get(team);
        HashSet<WalkerManager> managersAlive = managersAliveByTeam.get(team);
        managers.add(manager);
        managersAlive.add(manager);
    }

    @Override
    public void visit(Walker walker) {
        add(walker);
    }

    @Override
    public void visit(StructuralObject structuralObject) {
    }

    @Override
    public void visit(FixedObject fixedObject) {
    }

    private void playTurn() {
        for (HashSet<WalkerManager> managers : managersAliveByTeam.values()) {
            HashSet<WalkerManager> managersClone = (HashSet<WalkerManager>) managers.clone();

            for (WalkerManager manager : managersClone) {
                if (manager.isAlive())
                    manager.playTurn();
            }
        }
    }

    private void notifyAllWalkers(String message) {
        for (HashSet<WalkerManager> managers : managersByTeam.values())
            for (WalkerManager manager : managers)
                manager.showMessage(message);
    }

    private void notifyEndGame() {
        for (Team team : managersByTeam.keySet()) {
            HashSet<WalkerManager> managers = managersByTeam.get(team);
            boolean haveSurvivors = managersAliveByTeam.get(team).size() > 0;

            for (WalkerManager manager : managers) {
                if (haveSurvivors)
                    manager.showMessage("Your Team Wins");
                else
                    manager.showMessage("Your Team Lost");
            }
        }
    }

    private boolean isEndGame() {
        int teamsWithWalkersAlive = 0;

        for (HashSet<WalkerManager> managers : managersAliveByTeam.values())
            if (managers.size() > 0)
                teamsWithWalkersAlive++;

        return teamsWithWalkersAlive <= 1;
    }
}
