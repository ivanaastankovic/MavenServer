
package rs.bg.ac.student.ivana.MavenServer.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.bg.ac.student.ivana.MavenServer.thread.ServerCommunication;

public class Server extends Thread{
    private ServerSocket ss;
    public Server() {
    }

    @Override
    public void run() {
        try{
        ss = new ServerSocket(9000);
        while(!ss.isClosed()){
            Socket socket = ss.accept();
            handleClient(socket);
            
        }
        }catch(SocketException se){
            System.out.println(se.getMessage());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void handleClient(Socket socket) {
        ServerCommunication sc = new ServerCommunication(socket);
        sc.start();
    }

    public void close() {
        try{
            ss.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    
}
