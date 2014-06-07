package networking;

import java.net.InetSocketAddress;

public class User {
    private InetSocketAddress ip;
    private int connectionID;
    
    public User(InetSocketAddress ip, int id) {
        this.ip = ip;
        connectionID = id;
    }
    
    public void setID(int id) {
        connectionID = id;
    }
    
    public void setIP(InetSocketAddress ip) {
        this.ip = ip;
    }
    
    public int getID() {
        return connectionID;
    }
    
    public InetSocketAddress getIP() {
        return ip;
    }
}
