package games;


import card.*;
import event.Initiator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Casino extends Game {
    static int maxPlayers = 4;
    static int minPlayers = 2;
    static String[] deckTypes = {"hand", "table"};
    
    private static Deck[] won;
    private static Deck deck = new Deck(true);
    private static Deck[] hands;
    private static Deck table = new Deck();

    private GameRoom room;
    private Player[] players;
    static ArrayList<Integer> takenCards = new ArrayList<Integer>();
    Initiator initiator;

    static Scanner sc = new Scanner(System.in);

    static boolean cardsLeft = true;

    public Casino(GameRoom room) {
        initiator = new Initiator();
        this.room = room;
        players = (Player[]) room.players.values().toArray();
    }
    
    private boolean deal() {
        if(deck.size() < players.length * 2)
            return false;
        for(int c = 0; c < players.length; c++) {
            for(int e = 0; e < 2; e++) {
                hands[c].addCard(deck.returnCard(true));
            }
        }
        return true;
    }
    
    @Override
    public void initialize() {
    	hands = new Deck[players.length];
            
        for(int i = 0; i <= players.length; i++) {
            hands[i] = new Deck(4);
            networking.server.CardServer.initiator.deckEventOccured(deck, new Player[]{players[i]}, deckTypes[0]);
        }

        for(int i = 0; i < 2; i++) {
            deal();
            for(int e = 0; e < 2; e++) {
                table.addCard(deck.returnCard(true));
            }
        }
        networking.server.CardServer.initiator.deckEventOccured(table, players, deckTypes[1]);
    }
    
    public void runGame() throws IOException {

        System.out.println("Dealing to " + players + " players");


        System.out.println("Table: " + table.toString());
        for(int i = 0; i < hands.length; i++) {
            System.out.println("Player " + i + "'s hand: " + hands[i].toString());
        }

        while (cardsLeft == true) {
            int cardPicked;
            for(int i = 0; i < 4; i++) {
                for(int c = 0; c < players.length; c++) {
                    System.out.println("It's player " + (c + 1) + "'s turn, pick a card to use");
                    cardPicked = sc.nextInt();
                    System.out.println("Do you want to use " + hands[c].deck()[cardPicked].toString() + " to take cards? true/false");
                    if(sc.nextBoolean() == false) {
                        table.addCard(hands[c].returnCard(true));
                        continue;
                    }
                    System.out.println("Choose the cards you want to take");
                    while (sc.hasNextInt())
                        takenCards.add(sc.nextInt());
/*                    takenCards.sort(takenCards, new Comparator<Integer>() {
                    	@Override
                    	public int compare(int o1, int o2) {
                    		return -o1.compareTo(o2);
                    	}
                    });*/
                }
            }
        }
    }
}
