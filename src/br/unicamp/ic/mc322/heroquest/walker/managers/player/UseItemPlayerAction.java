package br.unicamp.ic.mc322.heroquest.walker.managers.player;

import br.unicamp.ic.mc322.heroquest.item.CollectableItem;
import br.unicamp.ic.mc322.heroquest.view.IOInterface;
import br.unicamp.ic.mc322.heroquest.walker.managers.UseItemAction;

public class UseItemPlayerAction extends UseItemAction {
    private WalkerPlayer walkerPlayer;
    private IOInterface ioInterface;

    UseItemPlayerAction(WalkerPlayer walkerPlayer) {
        super(walkerPlayer);
        this.walkerPlayer = walkerPlayer;
        this.ioInterface = walkerPlayer.getIOInterface();
    }

    @Override
    protected CollectableItem chooseItem(CollectableItem[] items) {
        String[] nameList = new String[items.length];

        for (int i = 0; i < items.length; i++)
            nameList[i] = items[i].getItemName();

        walkerPlayer.updateScreen();
        ioInterface.showMessage("Choose an item to use:");
        int choice = ioInterface.showOptionsAndGetAnswer(nameList, true) - 1;

        return choice == -1 ? null : items[choice];
    }
}
