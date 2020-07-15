package br.unicamp.ic.mc322.heroquest.util.playerInterface;

import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.terminal.TerminalMapViewer;
import br.unicamp.ic.mc322.heroquest.view.MapViewer;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayerInterface {
    private MapViewer mapViewer;
    private Scanner scanner;

    public PlayerInterface(Map map) {
        scanner = new Scanner(System.in);
        mapViewer = new TerminalMapViewer(map);
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

    public void showMap(Coordinate reference) {
        mapViewer.display(reference);
    }
}
