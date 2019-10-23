import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	public Client() {
		try {
			Socket clientSocket = new Socket("localhost", 25565);
			System.out.println("Conectamos ao server");
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());
			PrintWriter p = new PrintWriter(outToServer, true);
			p.print("Teste 1");
			System.out.println("enviamos");
			System.out.println("esperando pelo server");
			String tata = inFromServer.readLine();
			System.out.println("FROM SERVER: " + tata);
			clientSocket.close();
		} catch (Exception ex) {
			System.out.println("ERRO: " + ex.getMessage());
		}

	}
}
