package event;

import card.Deck;
import games.Player;

public interface DeckListener {
    public void eventOccured(DeckEvent e);
}
