package br.unicamp.ic.mc322.heroquest.graphicinterface;

import br.unicamp.ic.mc322.heroquest.engine.IOInterface;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.heroes.HeroesKind;

public class Settings {
    /**TODO: teste de ioInterface*/
    private IOInterface teste;
    private Map map;
    private String nickname;
    private Walker walker;

    public Settings() {}

    public void setMap(Map map) {
        this.map = map;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setWalker(HeroesKind heroKind) {
        walker = heroKind.getHero(nickname, teste);
    }
}
