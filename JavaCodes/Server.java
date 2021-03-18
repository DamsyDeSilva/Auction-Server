import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private int port;
    private static ServerSocket serverSocket;
    
    Server(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(port); 
    }

    public void serverLoop() throws IOException {
        while(true){
            Socket socket = serverSocket.accept();
            Connection newConnection = new Connection(socket);
            newConnection.startThread();     
        }
    }

}