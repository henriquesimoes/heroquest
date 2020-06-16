package br.unicamp.ic.mc322.heroquest.util.playerInterface;

import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.view.TerminalViewer;
import br.unicamp.ic.mc322.heroquest.map.view.Viewer;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayerInterface {
    Viewer viewer;
    Scanner scanner;
    public PlayerInterface(){
        scanner = new Scanner(System.in);
        viewer = new TerminalViewer();
    }

    public int showOptionsAndGetAnswer(ArrayList<String> optionsList) {
        boolean invalidAnswer = true;
        int answer = 0;

        while(invalidAnswer){
            System.out.println("0 - to return");
            for (int i = 0; i < optionsList.size(); i++){
                System.out.printf("%d -  %s\n", i + 1, optionsList.get(i));
            }
            answer = scanner.nextInt();
            if(answer >= 0 && answer <= optionsList.size())
                invalidAnswer = false;
        }

        return answer;
    }

    public void showMessage(String s) {
        System.out.println(s);
    }

    public void showMap(Map map, MapObject reference){
        viewer.display(map, reference);
    }
}
