import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	 private ServerSocket socket;
	 private ArrayList<ClientConnection> clients;
	 
	 public Server(int port)
	 {
		 clients = new ArrayList<>();
		  try {
			socket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  this.listen();
	 }
	 
	 private void listen()
	 {
		 while(true)
		 {
			 Socket temp = null;
			 try {
				temp = socket.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 ClientConnection con = new ClientConnection(temp, this);
			 clients.add(con);
			 con.start();
			 System.out.println("Client connected");
			 
		 }
	 }
	 
	 public ArrayList<ClientConnection> getAllClients()
	 {
		 return clients;
	 }

}
