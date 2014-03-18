package games;

import java.util.ArrayList;
import java.util.Scanner;

import card.*;

public class Casino {

    static Deck[] won;
    static Deck deck = new Deck(true);
    static Deck[] hands;
    static Deck table = new Deck();

    static int players;
    static ArrayList<Integer> takenCards = new ArrayList<Integer>();

    static Scanner sc = new Scanner(System.in);

    static boolean cardsLeft = true;


    public static boolean deal() {
        if(deck.size() < players * 2)
            return false;
        for(int c = 0; c < players; c++) {
            for(int e = 0; e < 2; e++) {
                hands[c].addCard(deck.returnCard(true));
            }
        }
        return true;
    }

   /* static int[] moveChecker(int cardUsed, int[] takenCards) {
        List<Integer> values = new ArrayList<Integer>();
        for(int value : takenCards)
            values.add(value);
        while (!values.isEmpty()) {

        }
    }*/
    public void initialize(int players) {
    	this.players = players;
    	hands = new Deck[players];
    	
    	
    }
    public static void main(String[] args) {
        System.out.println("How many players are there?");
        players = sc.nextInt();
        hands = new Deck[players];
        System.out.println("Dealing to " + players + " players");

        for(int i = 0; i < players; i++)
            hands[i] = new Deck(4);

        for(int i = 0; i < 2; i++) {
            deal();
            for(int e = 0; e < 2; e++) {
                table.addCard(deck.returnCard(true));
            }
        }
    //    for(int i = 0; i < 5; i++) {
    //        table.addCard(deck.returnCard(true));
    //    }
        System.out.println("Table: " + table.toString());
        for(int i = 0; i < hands.length; i++) {
            System.out.println("Player " + i + "'s hand: " + hands[i].toString());
        }

        while (cardsLeft == true) {
            int cardPicked;
            for(int i = 0; i < 4; i++) {
                for(int c = 0; c < players; c++) {
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
