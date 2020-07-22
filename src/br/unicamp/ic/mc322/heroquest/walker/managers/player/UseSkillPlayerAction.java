package br.unicamp.ic.mc322.heroquest.walker.managers.player;

import br.unicamp.ic.mc322.heroquest.skills.Skill;
import br.unicamp.ic.mc322.heroquest.view.IOInterface;
import br.unicamp.ic.mc322.heroquest.walker.managers.UseSkillAction;

public class UseSkillPlayerAction extends UseSkillAction {
    private final WalkerPlayer walkerPlayer;

    UseSkillPlayerAction(WalkerPlayer walkerPlayer) {
        super(walkerPlayer);
        this.walkerPlayer = walkerPlayer;
    }

    protected Skill chooseSkill(Skill[] skills) {
        IOInterface ioInterface = walkerPlayer.getIOInterface();

        String[] nameList = new String[skills.length];

        for (int i = 0; i < skills.length; i++)
            nameList[i] = skills[i].getSkillName();

        walkerPlayer.updateScreen();
        ioInterface.showMessage("Choose a skill to use:");
        int choice = ioInterface.showOptionsAndGetAnswer(nameList, true) - 1;

        return choice == -1 ? null : skills[choice];
    }
}
