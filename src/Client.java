import java.io.*;
import java.net.*;

import java.util.Scanner;

import lib.Ansi;
import lib.Console;
import lib.net.Server;
import lib.net.SocketStreams;

// import controller.MainController;

//=============================================================================
// ▼ Client
// ----------------------------------------------------------------------------
// Etablit une connexion avec le serveur.
// Appelle la vue appropriée.
// Appelle l'action par défaut.
//=============================================================================
class Client
{
	// private SocketStreams server;
	private Server server;
	private Thread serverThread;

	//---------------------------------------------------------------------------
	// * Constructeur
	//---------------------------------------------------------------------------
	public Client()
	{
		log(Ansi.BLUE + "Bonjour!" + Ansi.RESET);
		// this.server = server;
		// log(Ansi.BLUE + (String) server.getObject() + Ansi.RESET);
		// MainController.initialize();
	}

	//---------------------------------------------------------------------------
	// * Demande à l'utilisateur une chaîne de caractères, l'envoie au serveur
	// et affiche sa réponse (la chaîne passée en majuscules).
	// Le programme s'arrête lorsque l'utilisateur entre le caractère 'q'.
	//---------------------------------------------------------------------------
	public void input()
	{
		Scanner keyboard = new Scanner(System.in);
		String response;
		String userInput;
		String[] userInputArray;

		while(true) {
			Console.commandPrompt();

			userInput      = keyboard.nextLine().trim();
			userInputArray = userInput.split(" ");

			// Interprète la commande
			switch(userInputArray[0]) {

				// Quitter
				case "q":
					terminate();
					return;

				// Actions sur le server
				case "ops":
					openServer(9090);
					break;

				// Actions sur le server
				case "open-server":
					openServer(Integer.parseInt(userInputArray[1]));
					break;

				// Actions sur les sessions
				case "open-session":
					log("Open session #" + userInputArray[1] + "!");
					break;
				case "close-session":
					log("Close session #" + userInputArray[1] + "!");
					break;
				case "join-session":
					log("Join session #" + userInputArray[1] + "!");
					break;
				case "end-session":
					log("End session #" + userInputArray[1] + "!");
					break;
				case "leave-session":
					log("Leave session #" + userInputArray[1] + "!");
					break;

				// List
				case "list":
					switch(userInputArray[1]) {
						case "open-sessions":
							log("Open sessions:");
							break;
						default:
							log("Liste non reconnue!");
					}
					break;

				default:
					// capitalize(server,userInput);
			}
		}
	}

	//---------------------------------------------------------------------------
	// * Terminate
	// Affiche un message sur la sortie standard.
	//---------------------------------------------------------------------------
	public void openServer(Integer port)
	{
		if(server != null) {
			Console.print("Un serveur est déjà ouvert!");
			return;
		}
		Console.print("Ask open server on port " + port + "!");
		server = new Server(port);
		serverThread = new Thread(server);
		serverThread.start();
	}

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
	public static void log(String message)
	{
		System.out.println(message);
	}

	//---------------------------------------------------------------------------
	// * Terminate
	// Affiche un message sur la sortie standard.
	//---------------------------------------------------------------------------
	public void terminate()
	{
		if(server != null) server.stop();
		log("A la prochaine!");
	}

	//---------------------------------------------------------------------------
	// * Main
	// Etablit une connexion avec le serveur et prend en charge les actions de
	// l'utilisateur.
	//---------------------------------------------------------------------------
	public static void main(String[] args) throws Exception
	{
		// Socket socketServer = getServerSocket();
		// SocketStreams server = new SocketStreams(socketServer);
		Client client = new Client();
		client.input();
	}
}
