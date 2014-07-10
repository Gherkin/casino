/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package event;

import card.Deck;
import games.GameRoom;
import games.Player;

/**
 *
 * @author niyohn
 */
public class DeckEvent {
    public Player[] players;
    public Deck deck;
    String deckType;
    
    public DeckEvent(Deck deck, Player[] player, String deckType) {
        this.deck = deck;
        players = player;
        this.deckType = deckType;
    }    
}
