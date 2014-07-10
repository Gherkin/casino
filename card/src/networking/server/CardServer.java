package networking.server;

import card.*;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import event.DeckEvent;
import event.DeckListener;
import event.Initiator;
import games.*;

import games.Player;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import networking.User;

public class CardServer {
    private static HashMap<InetSocketAddress, Player> clients = new HashMap<InetSocketAddress, Player>();
    private static HashMap<String, GameRoom> rooms = new HashMap<String, GameRoom>();    
    public static Initiator initiator;
    public static void main(String[] args) {
        com.esotericsoftware.minlog.Log.set(com.esotericsoftware.minlog.Log.LEVEL_DEBUG); 
        Server server = new Server();
        server.getKryo().register(Player.class);
        server.getKryo().register(Card.class);
        server.getKryo().register(String.class);
        server.getKryo().register(Integer.class);
        server.getKryo().register(DeckEvent.class);
        server.getKryo().register(GameRoom.class);
        server.start();
        initiator = new Initiator();
        
        try {
            server.bind(54555, 54777);
        } catch (IOException e) {
            System.out.println("Can't bind to port");
        }
        initiator.addListener(new DeckListener() {
            @Override
            public void eventOccured(DeckEvent e) {
                for(Player player : e.players) {
                    server.getConnections()[clients.get(player.getIP()).getID()].sendTCP(e);
                }
            }
        });
        
        server.addListener(new Listener() {
            @Override
            public void received (Connection connection, Object object) {
                if(object instanceof Player) {
                    clients.put(connection.getRemoteAddressTCP(), (Player) object);
                }
                if(object instanceof String) {
                    System.out.println((String) object);
                    String[] split = ((String) object).split("\\s+");
                    switch(split[0]) {
                        case "NICK":
                            if(split.length < 2) {
                                connection.sendTCP(-1);
                                break;
                            }
                            //What if there is no player for that specific IP?
                            clients.put(connection.getRemoteAddressTCP(), clients.get(connection.getRemoteAddressTCP()).setNick(split[1]));
                            connection.sendTCP(1);
                            break;
                        case "ROOM":
                            switch(split[1]) {
                                case "CREATE":
                                    if(split.length < 4) {
                                        connection.sendTCP(-1);
                                        break;
                                    }
                                    if(rooms.containsKey(split[2])) {
                                        connection.sendTCP(-3);
                                        break;
                                    }
                                    try {
                                        //this seems very unsafe
                                        rooms.put(split[2], new GameRoom(split[2], (Game) (Class.forName(split[3]).newInstance())));
                                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                                        Logger.getLogger(CardServer.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    connection.sendTCP(2);
                                    break;
                                case "JOIN":
                                    if(split.length <3) {
                                        connection.sendTCP(-1);
                                        break;
                                    }
                                    if(!(rooms.containsKey(split[3]))) {
                                        connection.sendTCP(-2);
                                        break;
                                    }
                                    GameRoom newRoom = rooms.get(split[3]);
                                    newRoom.addPlayer(clients.get(connection.getRemoteAddressTCP()));
                                    rooms.replace(split[3], newRoom);
                                    connection.sendTCP(newRoom);
                                    break;
                            }
                    }
                }
                
            }
        });
    }

}
