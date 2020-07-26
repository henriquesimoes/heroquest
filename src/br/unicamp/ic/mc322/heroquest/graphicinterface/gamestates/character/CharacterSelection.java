package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.character;

import br.unicamp.ic.mc322.heroquest.graphicinterface.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GameWindow;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GamePanel;
import br.unicamp.ic.mc322.heroquest.graphicinterface.Renderable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons.definedbuttons.BackButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.cards.Card;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.cards.characterscards.BarbarianChar;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.cards.characterscards.DwarfChar;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.cards.characterscards.ElfChar;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.cards.characterscards.WizardChar;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.gametitle.GameTitle;

import java.awt.*;
import java.util.ArrayList;

public class CharacterSelection implements Renderable {
    private final GameTitle GAME_TITLE;
    private final BackButton BACK_BUTTON;
    Graphics2D graphics;
    ArrayList<Card> options;

    public CharacterSelection(Graphics2D graphics, GamePanel gamePanel) {
        this.graphics = graphics;
        this.options = new ArrayList<>();
        this.GAME_TITLE = new GameTitle(graphics, 200);
        this.BACK_BUTTON = new BackButton(graphics, gamePanel);
        options.add(new BarbarianChar(graphics, gamePanel));
        options.add(new DwarfChar(graphics, gamePanel));
        options.add(new ElfChar(graphics, gamePanel));
        options.add(new WizardChar(graphics, gamePanel));
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
        int nextXCoord = (GameWindow.WINDOW_WIDTH - (boxWidth + 10) * options.size()) / 2;

        for (Card card : options) {
            card.render(nextXCoord, 350);

            nextXCoord += card.getBounds().getWidth() + 30;
        }
    }
}
