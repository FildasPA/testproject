import java.net.*;
import java.io.IOException;

import server.controller.MainController;
import server.lib.ClientHandler;
import server.lib.context.*;
// import server.lib.DB;

//=============================================================================
// ▼ Server
// ----------------------------------------------------------------------------
// Démarre une nouvelle session (ClientSession) pour chaque client connecté.
// Fonctionne actuellement en local pour les tests.
//=============================================================================
public class Server
{
	private ServerSocket listener;
	private boolean listening;

	//---------------------------------------------------------------------------
	// * Constructeur
	//---------------------------------------------------------------------------
	public Server()
	{
		setListener();

		System.out.println("\nServeur démarré!");
		System.out.println("================\n");
	}

	//---------------------------------------------------------------------------
	// * Set listener
	//---------------------------------------------------------------------------
	private void setListener()
	{
		try {
			listener = new ServerSocket(9090, 0, InetAddress.getByName(null));
		} catch (IOException e) {
			System.err.println("Could not listen on port: 9090");
			System.exit(1);
		}
	}

	//---------------------------------------------------------------------------
	// * Listen
	// Créer un nouveau socket et un nouveau thread à chaque fois qu'un client
	// essaie de se connecter.
	//---------------------------------------------------------------------------
	public void listen() throws IOException
	{
		listening = true;

		Socket socket;
		ClientHandler clientHandler;
		Thread clientThread;

		while(listening) {
			socket        = listener.accept();
			clientHandler = new ClientHandler(socket);
			clientThread  = new Thread(clientHandler);
			clientThread.start();
		}

		listener.close();
	}

	//---------------------------------------------------------------------------
	// * Main
	// Initialise le serveur et démarre l'écoute.
	//---------------------------------------------------------------------------
	public static void main(String[] args) throws IOException
	{
		Server server = new Server();
		// ConnexionBDD.connect(); // TODO RES-BDD : voir ConnexionBDD.java
		server.listen();
	}
}
