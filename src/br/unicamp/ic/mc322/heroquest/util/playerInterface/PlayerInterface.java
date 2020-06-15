package br.unicamp.ic.mc322.heroquest.util.playerInterface;

import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.view.TerminalViewer;
import br.unicamp.ic.mc322.heroquest.map.view.Viewer;

import java.util.ArrayList;

public class PlayerInterface {
    Viewer viewer;
    public PlayerInterface(){
        viewer = new TerminalViewer();
    }

    public int showOptionsAndGetAnswer(ArrayList<String> optionsList) {
        System.out.println("0 - to return");
        for (int i = 0; i < optionsList.size(); i++){
            System.out.printf("%d -  %s\n", i + 1, optionsList.get(i));
        }
        return 0;
    }

    public void showMessage(String s) {
        System.out.println(s);
    }

    public void showMap(Map map, MapObject reference){
        viewer.display(map, reference);
    }
}
