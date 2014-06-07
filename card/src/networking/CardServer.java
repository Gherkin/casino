package networking;



import card.*;
import games.*;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import games.Player;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;

public class CardServer {
    private static HashMap<InetSocketAddress, Player> clients = new HashMap<InetSocketAddress, Player>();
    private static ArrayList<GameRoom> rooms = new ArrayList<GameRoom>();    
    public static void main(String[] args) {

        Server server = new Server();
        server.getKryo().register(Card.class);
        server.getKryo().reference(Card.class);
        server.start();
        
        try {
            server.bind(54555, 54777);
        } catch (IOException e) {
            System.out.println("Can't bind to port");
            e.printStackTrace();
        }
        
        server.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if(object instanceof Player) {
                    clients.put(connection.getRemoteAddressTCP(), (Player) object);
                }
                if(object instanceof String[]) {
                    switch(((String[]) object)[0]) {
                        case "NICK":
                            //What if there is no player for that specific IP?
                            clients.put(connection.getRemoteAddressTCP(), clients.get(connection.getRemoteAddressTCP()).setNick(((String[]) object)[1]));
                            break;
                        case "ROOM":
                            switch(((String[]) object)[1]) {
                                case "CREATE":
                                    break;
                                case "JOIN":
                                    break;
                            }
                    }
                }
                
            }
        });
    }

}
