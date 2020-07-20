package br.unicamp.ic.mc322.heroquest.walker.managers.player;

import br.unicamp.ic.mc322.heroquest.item.CollectableItem;
import br.unicamp.ic.mc322.heroquest.view.IOInterface;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.managers.Action;

public class SeeStatusPlayerAction implements Action {
    private WalkerPlayer walkerPlayer;
    private Walker walker;
    private IOInterface ioInterface;

    SeeStatusPlayerAction(WalkerPlayer walkerPlayer){
        this.walkerPlayer = walkerPlayer;
        this.walker = walkerPlayer.getWalker();
        this.ioInterface = walkerPlayer.getIOInterface();
    }

    @Override
    public String getDescription() {
        return "See Status";
    }

    @Override
    public boolean execute() {
        while(true){
            String [] options = { "See attributes",
                    "See inventory",
                    "See skills"
            };

            switch (ioInterface.showOptionsAndGetAnswer(options, true)){
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

    private void seeAttributes(){
        ioInterface.showMessage(walker.getAttributesList());
    }

    private void seeInventory() {
        java.util.Map<CollectableItem, Integer> items = walker.getInventory();
        CollectableItem[] itemsList = items.keySet().toArray(new CollectableItem[0]);

        String [] options = new String[items.size()];
        for (int i = 0; i < options.length; i++)
            options[i] = itemsList[i].getItemName() + " - amount : " + items.get(itemsList[i]);

        while(true){
            ioInterface.showMessage("Select a item to see your description");
            int choose = ioInterface.showOptionsAndGetAnswer(options, true) - 1;

            if (choose == -1)
                return;

            ioInterface.showMessage(itemsList[choose].getItemDescription());
        }
    }

    private void seeSkills() {
    }

}
