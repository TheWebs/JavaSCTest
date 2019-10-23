import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnection extends Thread {
	private Socket connection;
	private Server server;

	public ClientConnection(Socket socket, Server server) {
		this.connection = socket;
		this.server = server;
	}

	@Override
	public void run() {
		this.handleClient();
	}

	private void handleClient() {
		while (true) {
			try {
				DataInputStream inFromClient = null;
				inFromClient = new DataInputStream(connection.getInputStream());
				String data = "";
				System.out.println("TESTE");
				data = inFromClient.readLine();
				System.out.println("2222");
				System.out.println(data);
				this.sendToAll(data);
			} catch (Exception ex) {
				this.stop();
				System.out.println("ERRO: " + ex.getMessage());
			}
		}

	}

	public Socket getConnection() {
		return connection;
	}

	public void sendToAll(String msg) {
		for (ClientConnection client : server.getAllClients()) {
			DataOutputStream outToClient = null;
			try {
				outToClient = new DataOutputStream(client.getConnection().getOutputStream());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			PrintWriter p = new PrintWriter(outToClient, true);
			p.println(msg);
			try {
				outToClient.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}