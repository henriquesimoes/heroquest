package br.unicamp.ic.mc322.heroquest.walker.managers.player;

import br.unicamp.ic.mc322.heroquest.engine.IOInterface;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.items.Item;
import br.unicamp.ic.mc322.heroquest.walker.managers.Action;
import br.unicamp.ic.mc322.heroquest.walker.skills.Skill;

public class SeeStatusPlayerAction implements Action {
    private Walker walker;
    private IOInterface ioInterface;

    SeeStatusPlayerAction(WalkerPlayer walkerPlayer) {
        this.walker = walkerPlayer.getWalker();
        this.ioInterface = walkerPlayer.getIOInterface();
    }

    @Override
    public String getDescription() {
        return "See status";
    }

    @Override
    public boolean execute() {
        while (true) {
            String[] options = {
                    "See attributes",
                    "See inventory",
                    "See skills"
            };

            switch (ioInterface.showOptionsAndGetAnswer(options, true)) {
                case 0:
                    return false;
                case 1:
                    seeAttributes();
                    break;
                case 2:
                    seeInventory();
                    break;
                case 3:
                    seeSkills();
                    break;
            }
        }
    }

    private void seeAttributes() {
        ioInterface.showMessage(walker.getAttributeList());
    }

    private void seeInventory() {
        java.util.Map<Item, Integer> items = walker.getInventory();
        Describable[] describable = items.keySet().toArray(new Describable[0]);
        Integer[] amounts = items.values().toArray(new Integer[0]);
        seeDescription("Select a item to see its description", describable, amounts);
    }

    private void seeSkills() {
        java.util.Map<Skill, Integer> skills = walker.getSkills();
        Describable[] describable = skills.keySet().toArray(new Describable[0]);
        Integer[] amounts = skills.values().toArray(new Integer[0]);
        seeDescription("Select a skill to see its description", describable, amounts);
    }


    private void seeDescription(String message, Describable[] describable, Integer[] amounts) {
        String[] options = new String[describable.length];
        for (int i = 0; i < options.length; i++)
            options[i] = describable[i].getName() + " - amount: " + amounts[i];

        while (true) {
            ioInterface.showMessage(message);
            int choose = ioInterface.showOptionsAndGetAnswer(options, true) - 1;

            if (choose == -1)
                return;

            ioInterface.showMessage(describable[choose].getDescription() + "\n");
        }
    }
}
