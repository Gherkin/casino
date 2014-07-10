package games;

import card.Deck;
import java.net.InetSocketAddress;

public class Player {
    private InetSocketAddress ip;
    private Deck hand;
    private String nick;
    private int id;
    
    public Player(String nick, InetSocketAddress ip, int id) {
        this.nick = nick;
        this.ip = ip;
    }
    
    public Player() {
        
    }
    public InetSocketAddress getIP() {
        return ip;
    }
    
    public int getID() {
        return id;
    }
    
    public Player(String nick) {
        this.nick = nick;
    }
    
    public void setDeck(Deck deck) {
        hand = deck;
    }
    
    public Player setNick(String nick) {
        this.nick = nick;
        return this;
    }
    
    public Player setIP(InetSocketAddress ip) {
        this.ip = ip;
        return this;
    } 
    
    public String getNick() {
        return nick;
    }
}
