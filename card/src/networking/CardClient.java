package networking;

import card.*;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import event.DeckEvent;
import games.GameRoom;
import games.Player;
import java.io.IOException;
import java.util.Scanner;

public class CardClient {

    public static void main(String[] args) {
        com.esotericsoftware.minlog.Log.set(com.esotericsoftware.minlog.Log.LEVEL_DEBUG); 
        Scanner sc = new Scanner(System.in);
        Player player = new Player(sc.next());
        Client client = new Client();
        client.getKryo().register(Player.class);
        client.getKryo().register(Card.class);
        client.getKryo().register(String.class);
        client.getKryo().register(Integer.class);
        client.getKryo().register(DeckEvent.class);
        client.start();
        try {
            client.connect(5000, "127.0.0.1", 54555, 54777);
        } catch (IOException e) {
            System.out.println("Can't connect, is the server running?");
        }
        client.sendTCP(player);
        client.sendTCP(sc.next());
        client.addListener(new Listener() {
            @Override
            public void received (Connection connection, Object object) {
                if(object instanceof GameRoom) {
                    
                }
                else if(object instanceof DeckEvent) {
                    
                }
                else if(object instanceof Integer) {
                    switch((Integer) object) {
                        case 1: System.out.println("Sucessfully changed nick");
                            break;
                        case 2: System.out.println("Sucessfully created new group");
                            break;
                        case 3: System.out.println("Sucessfully joined group");
                            break;
                        case -1: System.out.println("Not enough arguments");
                            break;
                        case -2: System.out.println("There is no such room");
                            break;
                        case -3: System.out.println("There is already a room with that name");
                            break;
                    }
                }
            }
        });
        while(client != null) {
        if(sc.hasNext())
            client.sendTCP("ROOM CREATE yoghurt Casino");
        }
    }

}
