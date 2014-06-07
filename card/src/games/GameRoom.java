package games;

import java.util.HashMap;

public class GameRoom {
    HashMap<String, Player> players = new HashMap<String, Player>();
    Game currentGame;
    String name;
    
    public GameRoom(String name, Game game) {
        this.name = name;
        currentGame = game;
    }
    
    public GameRoom(String name, Game game, HashMap<String, Player> players) {
        this.name = name;
        this.currentGame = game;
        this.players = players;
    }
    
    public Boolean addPlayer(Player player) {
        if((players.size() + 1) <= Game.maxPlayers) {
            players.put(player.getNick(), player);
            return true;
        }
        else {
            return false;
        }
    }
    
    public void removePlayer(Player player) {
        players.remove(player.getNick());
    }
    
    public void changeGame(Game game) {
        currentGame = game;
    }
}
