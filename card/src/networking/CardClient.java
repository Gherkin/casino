package networking;

import games.Player;

import java.io.IOException;
import java.util.Scanner;

import com.esotericsoftware.kryonet.Client;

import card.Card;
import card.Deck;

public class CardClient {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Player player = new Player(sc.next());
        Client client = new Client();
        client.getKryo().register(Player.class);
        client.getKryo().reference(Card.class);
        client.getKryo().reference(Player.class);
        client.getKryo().reference(Deck.class);
        client.start();
        try {
            client.connect(5000, "127.0.0.1", 54555, 54777);
        } catch (IOException e) {
            System.out.println("Can't connect, is the server running?");
            e.printStackTrace();
        }
        client.sendTCP(player);

    }

}
