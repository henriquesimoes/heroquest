package br.unicamp.ic.mc322.heroquest.graphicinterface;

import br.unicamp.ic.mc322.heroquest.engine.IOInterface;
import br.unicamp.ic.mc322.heroquest.graphicinterface.guitools.GraphicIO;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.heroes.HeroesKind;

public class Settings {
    /**
     * TODO: teste de ioInterface
     */
    private IOInterface ioInterface;
    private Map map;
    private String nickname;
    private Walker walker;

    public Settings() {
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setIO(GraphicIO graphicIO) {
        ioInterface = graphicIO;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Walker getWalker() {
        return walker;
    }

    public void setWalker(HeroesKind heroKind) {
        walker = heroKind.getHero(nickname, ioInterface);
    }
}
