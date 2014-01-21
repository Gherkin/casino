package card;

import java.util.ArrayList;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Casino {

    static Deck[] won;
    static Deck deck = new Deck(true);
    static Deck[] hands;
    static Deck table = new Deck();
    static int[] points;
    
    static int players;
    
    static ArrayList<Integer> takenCards = new ArrayList<>();
    
    static Scanner sc = new Scanner(System.in); 
    
    public static boolean deal() {
        if(deck.size() < players * 4)
            return false;
        for(int i = 0; i < 2; i++) {
            for(int c = 0; c < players; c++) {
                for(int e = 0; e < 2; e++) {
                    hands[c].addCard(deck.returnCard(true));
                }      
            }
            if((deck.size() == 52 - players * 2) || (deck.size() == 52 - players * 4 - 2))
                for(int e = 0; e < 2; e++) 
                    table.addCard(deck.returnCard(true));
        }
        return true;
    }
    
    static int[] countPoints(Deck[] won) {
        int[] points = new int[won.length];
        ArrayList<Integer> best = new ArrayList<>();
        best.add(0);
        
        for(int i = 0; i < won.length; ++i) {
            if(i == 0)
                continue;
            if(won[i].size() > won[i - 1].size()) {
                best.clear();
                best.add(i);
            }
            else if(won[i].size() == won[i - 1].size())
                best.add(i);            
        }
        if(best.size() == 1)
            points[best.get(0)] += 2;
        else if(best.size() > 1)
            for(int i = 0; i < best.size(); ++i) 
                points[best.get(i)]++;
        best.clear();
        
        for(int i = 0; i < won.length; ++i) {
            Deck spades = won[i].suit(2, false);
            if(i == 0)
                continue;
            if(spades.size() > won[i - 1].size()) {
                best.clear();
                best.add(i);
            }
            else if(spades.size() == won[i - 1].size())
                best.add(i);             
        }
        if(best.size() == 1)
            points[best.get(0)] ++;
        best.clear();
        
        for(int i = 0; i < won.length; ++i) {
            points[i] += won[i].rank(0, false).size();
        }
        
        for(int i = 0; i < won.length; ++i) {
            if(won[i].contains(new Card(1, 2))) {             
                points[i]++;
                break;
            }
        }
        
        for(int i = 0; i < won.length; ++i) {
            if(won[i].contains(new Card(9, 3))) {             
                points[i] += 2;
                break;
            }
        }
        return points;
    }
    
  /*  static int[] moveChecker(int cardUsed, int[] takenCards) {
        List<Integer> values = new ArrayList<Integer>();
        for(int value : takenCards)
            values.add(value);
        while (!values.isEmpty()) {
            
        }
    }*/
    
    public static void main(String[] args) {
        Pattern delimiters = Pattern.compile(System.getProperty("line.separator")+"|\\s");
        sc.useDelimiter(delimiters); 
        
        boolean cardsLeft = true;
        int lastWinner = -1;
        int cardPicked;
        
        System.out.println("How many players are there?");
        players = sc.nextInt();
        hands = new Deck[players];
        won = new Deck[players];
        points = new int[players];
        System.out.println("Dealing to " + players + " players");
        
        for(int i = 0; i < players; i++) {
            hands[i] = new Deck(4);
            won[i] = new Deck();
        }
        
        deal();

      /*  for(int i = 0; i < 5; i++) {
            table.addCard(deck.returnCard(true));
        }*/
   /*     System.out.println("Table: " + table.toString());
        for(int i = 0; i < hands.length; i++) {
            System.out.println("Player " + i + "'s hand: " + hands[i].toString());
        }*/
        
        while (cardsLeft == true) {
            for(int i = 0; i < 4; i++) {               
                for(int c = 0; c < players; c++) {
                    System.out.println("Table: " + table.toString());
                    for(int e = 0; e < hands.length; e++) {
                        System.out.println("Player " + e + "'s hand: " + hands[e].toString());
                    }
                    System.out.println("It's player " + (c + 1) + "'s turn, pick a card to use");
                    cardPicked = sc.nextInt();
                    if((cardPicked >= hands[c].size()) || (cardPicked < 0 )) {
                        System.out.println("Not a valid index, pick again");
                        cardPicked = sc.nextInt();
                    }
                    System.out.println("Do you want to use " + hands[c].get(cardPicked).toString() + " to take cards? true/false");
                    if(sc.nextBoolean() == false) {
                        table.addCard(hands[c].get(cardPicked));
                        hands[c].remove(cardPicked);
                        continue;
                    }
                    lastWinner = c;
                    System.out.println("Choose the cards you want to take");
                    while(sc.hasNextInt())
                        takenCards.add(sc.nextInt());
                    for(int index : takenCards) {
                        won[c].addCard(table.get(index));
                        table.remove(index);
                    }
                    System.out.println("Table: " + table.toString());
                }
            }
            cardsLeft = deal();
        }
        if(lastWinner != -1)
            for(int i = 0; i < table.size(); ++i) {
                    won[lastWinner].addCard(table.get(i));
                    table.remove(i);
            }
        else {
            System.out.println("Nobody won, wierd!");
            System.exit(0);
        }
        points = countPoints(won);
        ArrayList<Integer> best = new ArrayList<>();
        best.add(0);
        for(int i = 0; i < points.length; ++i) {
            System.out.println("Player " + i + " got " + points[i] + " points.");
            if(i == 0)
                continue;
            if(points[i] > points[i - 1]) {
                best.clear();
                best.add(i);
            }
            else if(points[i] == points[i - 1])
                best.add(i);
        }
        if(best.size() == 1)
            System.out.println("Player " + best.get(0) + " is the winner with " + points[best.get(0)] + "points! Congratulations");      
        System.exit(0);
    }
}