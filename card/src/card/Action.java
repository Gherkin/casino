package card;

public class Action {
    public short type;
    public Deck cards;
    
    public Action(short type, Deck cards) {
        this.type = type;
        this.cards = cards;
    }
}
