package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.character;

import br.unicamp.ic.mc322.heroquest.graphicinterface.GameWindow;
import br.unicamp.ic.mc322.heroquest.graphicinterface.Settings;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.Renderable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.ScreenStateManager;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.definedbuttons.BackButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.cards.Card;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.cards.characters.BarbarianChar;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.cards.characters.DwarfChar;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.cards.characters.ElfChar;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.cards.characters.WizardChar;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.gametitle.GameTitle;

import java.awt.*;
import java.util.ArrayList;

public class CharacterSelection implements Renderable {
    Graphics2D graphics;
    ArrayList<Card> options;
    private final GameTitle GAME_TITLE;
    private final BackButton BACK_BUTTON;

    public CharacterSelection(Graphics2D graphics, Settings settings, ScreenStateManager screenStateManager) {
        this.graphics = graphics;
        this.options = new ArrayList<>();
        this.GAME_TITLE = new GameTitle(graphics, 200);
        this.BACK_BUTTON = new BackButton(graphics,screenStateManager);
        options.add(new BarbarianChar(settings, graphics, screenStateManager));
        options.add(new DwarfChar(settings, graphics, screenStateManager));
        options.add(new ElfChar(settings, graphics, screenStateManager));
        options.add(new WizardChar(settings, graphics, screenStateManager));
    }

    @Override
    public void render() {
        GAME_TITLE.render();
        BACK_BUTTON.render(700);
        renderCards();
    }

    @Override
    public ArrayList<Clickable> getClickableZones() {
        ArrayList<Clickable> clickables = new ArrayList<>(options);
        clickables.add(BACK_BUTTON);

        return clickables;
    }

    private void renderCards() {
        /* We can choose any index in box options, so we can calculate the x coord that will put
        *   all cards aligned in center */
        int boxWidth = (int) options.get(0).getBounds().getWidth();
        int nextXCoord = (GameWindow.WINDOW_WIDTH - (boxWidth + 10) * options.size() ) / 2;

        for (Card card : options) {
            card.render(nextXCoord, 350);

            nextXCoord += card.getBounds().getWidth() + 30;
        }
    }
}
