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
    public PlayerInterface() {
        scanner = new Scanner(System.in);
        viewer = new TerminalViewer();
    }

    public int showOptionsAndGetAnswer(ArrayList<String> optionList) {
        boolean invalidAnswer = true;
        int answer = 0;

        while (invalidAnswer) {
            for (int i = 0; i < optionList.size(); i++) {
                System.out.printf("%2d - %s\n", i + 1, optionList.get(i));
            }
            System.out.printf("%2d - Return\n", 0);
            System.out.print("Selected option: ");
            answer = scanner.nextInt();

            if (answer >= 0 && answer <= optionList.size())
                invalidAnswer = false;
            System.out.println("");
        }

        return answer;
    }

    public void showMessage(String s) {
        System.out.println(s + "\n");
    }

    public void showMap(Map map, MapObject reference){
        viewer.display(map, reference);
    }
}
