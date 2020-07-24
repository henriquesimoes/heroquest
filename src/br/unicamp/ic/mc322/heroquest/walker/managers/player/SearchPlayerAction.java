package br.unicamp.ic.mc322.heroquest.walker.managers.player;

import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.map.objects.HiddenObject;
import br.unicamp.ic.mc322.heroquest.walker.managers.Action;

import java.util.Set;

// This class discovers hidden objects that are at a (Manhattan) distance of up to three units
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

        Region region = walkerPlayer.getRegionSelector().getLimitedRegion(3, false);
        walkerPlayer.accept(walkerPlayer, region);

        if (hiddenObjectsDetected.size() > 0) {
            for (HiddenObject object : hiddenObjectsDetected) {
                walkerPlayer.showMessage("Detected the object: " + object.getRepresentationOnMenu());
                object.discover();
            }
        } else {
            walkerPlayer.showMessage("No objects were detected");
        }

        return true;
    }
}
