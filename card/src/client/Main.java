package client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import java.util.Scanner;

public class Main {
    private static int port;
    private static String ip;
    private static Socket socket;
    private static BufferedOutputStream outStream;
    private static BufferedInputStream inStream;
    private static OutputStreamWriter outWriter;
    private static InputStreamReader inReader;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        StringBuffer line = new StringBuffer("");
        int c;
        if(args.length != 2) {
            System.out.println("Argument syntax error");
            System.exit(-1);
        }
        ip = args[0];
        try {
            port = Integer.parseInt(args[1]);
        } catch(NumberFormatException e) {
            System.out.println("Argument syntax error");
            System.exit(-1);
        }
        try {
            socket = new Socket(ip, port);
            outStream = new BufferedOutputStream(socket.getOutputStream());
            outWriter = new OutputStreamWriter(outStream, "US-ASCII");
            inStream = new BufferedInputStream(socket.getInputStream());
            inReader = new InputStreamReader(inStream, "US-ASCII");
            scanner = new Scanner(System.in);
            while(!"EXIT".equals(line.toString())) {
                if(inReader.ready()) {
                    while((c = inReader.read()) != 13) {
                        line.append((char) c);
                    }
                    System.out.println(line);
                }
                if(scanner.hasNextLine()) {
                    outWriter.write(scanner.nextLine());
                    outWriter.flush();
                }
            }
            scanner.close();
            inReader.close();
            inStream.close();
            outStream.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0); 
    }    
}
