package lib.net;

import java.net.*;
import java.io.IOException;

import lib.Ansi;
import lib.Console;
// import lib.net.Clients;
// import lib.net.ClientHandler;
// import server.lib.DB;

//=============================================================================
// ▼ Server
// ----------------------------------------------------------------------------
// Démarre une nouvelle session (ClientSession) pour chaque client connecté.
// Fonctionne actuellement en local pour les tests.
//=============================================================================
public class Server implements Runnable
{
	private ServerSocket listener;
	private Boolean listening;

	//---------------------------------------------------------------------------
	// * Constructeur
	//---------------------------------------------------------------------------
	public Server(Integer port)
	{
		setListener(port);
	}

	//---------------------------------------------------------------------------
	// * Set listener
	//---------------------------------------------------------------------------
	private void setListener(Integer port)
	{
		try {
			listener = new ServerSocket(port, 0, InetAddress.getByName(null));
		} catch (IOException e) {
			System.err.println("Could not listen on port: " + port.toString());
			System.exit(1);
		}
	}

	//---------------------------------------------------------------------------
	// * Listen
	// Créer un nouveau socket et un nouveau thread à chaque fois qu'un client
	// essaie de se connecter.
	//---------------------------------------------------------------------------
	public void run()
	{
		Console.printb(Ansi.GREEN + "Serveur démarré!" + Ansi.RESET);

		listening = true;

		Socket socket;
		ClientHandler clientHandler;
		Thread clientThread;

		while(listening) {
			try {
				socket        = listener.accept();
				clientHandler = new ClientHandler(socket);
				clientThread  = new Thread(clientHandler);
				clientThread.start();
			} catch (IOException e) {
				// e.getMessage();
				// e.printStackTrace();
			}
		}

		try {
			listener.close();
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

	//---------------------------------------------------------------------------
	// * Log
	// Affiche un message sur la sortie standard.
	//---------------------------------------------------------------------------
	public void stop()
	{
		listening = false;
		Console.print(Ansi.RED + "Close server" + Ansi.RESET);
		Clients.closeAll();
		try {
			listener.close();
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
}
