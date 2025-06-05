import java.net.InetAddress;
import java.net.Socket;
import java.io.BufferedReader;  
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Client {

    public Runnable getRunnable(){
        return new Runnable() {
            @Override
            public void run() {
                try {
                    int port = 8010;
                    InetAddress address = InetAddress.getByName("localhost");
                    Socket socket = new Socket(address, port);
                    PrintWriter toSocket = new PrintWriter(socket.getOutputStream());
                    BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    toSocket.println("Hello from the client");
                    String line = fromSocket.readLine();
                    System.out.println("Response from the socket is: " + line);

                    toSocket.close();
                    fromSocket.close();
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }
    public static void main(String[] args) {
        Client client = new Client();
        for(int i = 0 ; i < 10; i++){
            try{
                Thread thread = new Thread(client.getRunnable());
                thread.start();
            }catch(Exception ex) {
                return;
            }
        }
    }
}
