package br.unicamp.ic.mc322.heroquest.graphicinterface;

import br.unicamp.ic.mc322.heroquest.map.core.Map;

public class Settings {
    private Map map;
    private String nickname;

    public Settings() {}

    public void setMap(Map map) {
        this.map = map;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
