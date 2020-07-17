package br.unicamp.ic.mc322.heroquest.walker.managers.player;

import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.map.objects.HiddenObject;
import br.unicamp.ic.mc322.heroquest.walker.managers.Action;

import java.util.Set;

public class SearchPlayerAction implements Action {
    private WalkerPlayer walkerPlayer;

    SearchPlayerAction(WalkerPlayer walkerPlayer) {
        this.walkerPlayer = walkerPlayer;
    }

    @Override
    public String getDescription() {
        return "Search hidden objects";
    }

    @Override
    public boolean execute() {
        Set<HiddenObject> hiddenObjectsDetected = walkerPlayer.getHiddenObjectsDetected();
        hiddenObjectsDetected.clear();

        Region region = walkerPlayer.getRegionSelector().getLimitedRegion(3, true);
        walkerPlayer.accept(walkerPlayer, region);

        for (HiddenObject object : hiddenObjectsDetected) {
            walkerPlayer.showMessage(String.format("Detected the object: %s", object.getRepresentationOnMenu()));
            object.discover();
        }

        return true;
    }
}
