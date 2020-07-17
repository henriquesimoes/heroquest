package br.unicamp.ic.mc322.heroquest.walker.managers.player;

import br.unicamp.ic.mc322.heroquest.walker.WalkerManager;
import br.unicamp.ic.mc322.heroquest.walker.managers.WalkerPlayer;

public class UseSkillAction implements Action {
    private WalkerManager walkerManager;

    public UseSkillAction(WalkerPlayer walkerManager) {
        this.walkerManager = walkerManager;
    }

    @Override
    public String getDescription() {
        return "Use skill";
    }

    @Override
    public boolean execute() {
        return walkerManager.useSkill();
    }
}
