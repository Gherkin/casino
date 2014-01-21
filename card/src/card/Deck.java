package card;

import java.util.Random;

public class Deck {
    private Card[] deck;
    private int size = 52;
    
    Deck(boolean fill) {
        int c = 0;
        deck = new Card[52];
        if(fill == true) {
                for(int i = 0; i < 4; ++i) {
                    for(int e = 0; e < 13; ++e) {
                        deck[c] = new Card(e, i); 
                        c++;
                    }
                }
            size = 52;
        }
        if(fill == false)
            size = 0;
    }
    
    Deck() {
        size = 0;
        deck = new Card[52];
    }
    
    Deck(boolean fill, int maxSize) {
        deck = new Card[maxSize];
        if(fill == true) {
                for(int i = 0; i < maxSize; ++i) {
                    deck[i] = new Card();
                }
            size = maxSize;
        }
        if(fill == false)
            size = 0;
    }
    
    Deck(int maxSize) {
        size = 0;
        deck = new Card[maxSize];
    }
    
    public Card returnCard(boolean remove) {
        if(size <= 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        Random random = new Random();
        int index = random.nextInt(size);
        Card card;
        
        card = deck[index];
        if(remove == true)
            remove(index);
        return card;             
    }
    
    public Card get(int index) {
        return deck[index];
    }
    
    public int size() {
        return size;
    }
    
    public Deck suit(int suit, boolean remove) {
        Deck suitDeck = new Deck(size);
        
        for(int i = 0; i < this.deck.length; ++i)
            if(this.deck[i].suit() == suit) {
                suitDeck.addCard(this.deck[i]);
                if(remove)
                    remove(i);
            }
        
        return suitDeck;
    }
    
        public Deck rank(int rank, boolean remove) {
        Deck rankDeck = new Deck(size);
        
        for(int i = 0; i < this.deck.length; ++i)
            if(this.deck[i].rank() == rank) {
                rankDeck.addCard(this.deck[i]);
                if(remove)
                    remove(i);
            }
        
        return rankDeck;
    }
    
    public void remove(int index) {
        if(size <= 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
                
        deck[index] = deck[size - 1];
        deck[size - 1] = null;
        size--;
    }
    
    @Override
    public String toString() {
        String string = "";
        if(size == 0)
            throw new ArrayIndexOutOfBoundsException();
        for(int i = 0; i < size; i++) {
            string = string + "[" + i + "] " + deck[i].toString();
            if(i != size - 1)
                string = string + ", ";
        }
        return string;
    }
    
    public boolean addCard(Card card) {
        if(size >= deck.length)
            return false;
        ++size;
        deck[size - 1] = card;
        return true;
    }
    
    public boolean contains(Card card) {
        for(Card deckCard : deck) {
            if((deckCard.rank() == card.rank()) && (deckCard.suit() == card.suit()))
                return true;
        }
        return false;
    }
}