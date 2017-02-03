import java.io.*;
import java.net.*;

// import lib.Ansi;
// import lib.Console;

// import lib.net.Server;
// import lib.net.SocketStreams;

import controller.MainController;

//=============================================================================
// ▼ Client
// ----------------------------------------------------------------------------
// Etablit une connexion avec le serveur.
// Appelle la vue appropriée.
// Appelle l'action par défaut.
//=============================================================================
class Client
{
	//---------------------------------------------------------------------------
	// * Constructeur
	//---------------------------------------------------------------------------
	// public Client()
	// {
		// log(Ansi.BLUE + "Bonjour!" + Ansi.RESET);
		// this.server = server;
		// log(Ansi.BLUE + (String) server.getObject() + Ansi.RESET);
		// MainController.initialize();
	// }

	//---------------------------------------------------------------------------
	// * Get server socket
	// Tente d'établir une connexion avec le serveur.
	//---------------------------------------------------------------------------
	// public static Socket getServerSocket() throws IOException
	// {
	// 	Socket socket = new Socket("localhost", 9090);
	// 	return socket;
	// }

	//---------------------------------------------------------------------------
	// * Log
	// Affiche un message sur la sortie standard.
	//---------------------------------------------------------------------------
	// public static void log(String message)
	// {
	// 	System.out.println(message);
	// }


	//---------------------------------------------------------------------------
	// * Main
	// Etablit une connexion avec le serveur et prend en charge les actions de
	// l'utilisateur.
	//---------------------------------------------------------------------------
	public static void main(String[] args) throws Exception
	{
		// Socket socketServer = getServerSocket();
		// SocketStreams server = new SocketStreams(socketServer);
		// Client client = new Client();
		MainController mainController = new MainController();
		mainController.main();
	}
}
