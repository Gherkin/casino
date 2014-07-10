package event;

import card.Deck;
import games.GameRoom;
import games.Player;
import java.util.ArrayList;
import java.util.List;

public class Initiator {
    protected List<DeckListener> listeners = new ArrayList<DeckListener>();
    
    public synchronized void addListener(DeckListener toAdd) {
        listeners.add(toAdd);
    }
    public synchronized void removeListener(DeckListener toRemove) {
        listeners.remove(toRemove);
    }
    public synchronized void deckEventOccured(Deck deck, Player[] player, String deckType) {
        DeckEvent deckEvent = new DeckEvent(deck, player, deckType);
        for(DeckListener listener : listeners)
            listener.eventOccured(deckEvent);
    }
}
