package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.gamemenu.buttons;

public enum ButtonType {
    NEW_GAME("New Game"),
    INSTRUCTIONS("Instructions"),
    QUIT("Quit");

    private String value;

    ButtonType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
