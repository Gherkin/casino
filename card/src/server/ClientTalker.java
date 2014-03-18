package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientTalker implements Runnable {
	private Socket socket;
	private InputStream inputStream;
	private OutputStream outputStream;
	
	@Override	
	public void run() {
		
	}
	public ClientTalker(Socket socket) throws IOException {
		this.socket = socket;
		inputStream = this.socket.getInputStream();
		outputStream = this.socket.getOutputStream();
	}

}
