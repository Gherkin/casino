    

    package card;
     
    import java.util.Random;
     
    public class Card {
        private int rank, suit = 0;
        private String[] suits = {"Hearts", "Clubs", "Spades", "Diamonds"};
        private String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
       
        Card(int rank, int suit) {
            this.rank = rank;
            this.suit = suit;
        }
       
        Card() {
            Random random = new Random();
            rank = random.nextInt(13);
            suit = random.nextInt(4);
        }
       
        public int rank() {
            return rank;
        }
       
        public int suit() {
            return suit;
        }
       
        @Override
        public String toString() {
            return ranks[rank] + " of " + suits[suit];
        }
    }

