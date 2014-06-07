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
    
    public DeckEvent(Deck deck, Player[] player) {
        this.deck = deck;
        players = player;
    }    
}
