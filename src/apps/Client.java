import java.io.*;
import java.net.*;

import java.util.Scanner;

import net.SocketStreams;

//=============================================================================
// ▼ Client
// ----------------------------------------------------------------------------
// Etablit une connexion avec le serveur.
//=============================================================================
class Client
{
	private SocketStreams server;

	//---------------------------------------------------------------------------
	// * Constructeur
	//---------------------------------------------------------------------------
	public Client(SocketStreams server)
	{
		this.server = server;
		log((String) server.getObject());
		input();
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
		String[] userInputs;

		while(true) {
			// Invite de commande
			System.out.print("> ");
			userInput = keyboard.nextLine().trim();
			userInputs = userInput.split(" ");

			// Interprète la commande
			switch(userInputs[0]) {

				// Quitter
				case "q":
					log("Bye!");
					return;

				// Actions sur les sessions
				case "open-session":
					log("Open session #" + userInputs[1] + "!");
					break;
				case "close-session":
					log("Close session #" + userInputs[1] + "!");
					break;
				case "join-session":
					log("Join session #" + userInputs[1] + "!");
					break;
				case "end-session":
					log("End session #" + userInputs[1] + "!");
					break;
				case "leave-session":
					log("Leave session #" + userInputs[1] + "!");
					break;

				// List
				case "list":
					switch(userInputs[1]) {
						case "open-sessions":
							log("Open sessions:");
							break;
						default:
							log("Liste non reconnue!");
					}
					break;

				// Défaut
				default:
					// Renvoie la chaîne de caractères en majuscules
					server.sendObject(userInput);
					log("Envoyé: " + userInput);
					String s = (String) server.getObject();
					if(s == null) System.exit(0);
					log("Reçu:   " + s);
			}
		}
	}

	//---------------------------------------------------------------------------
	// * Log
	// Affiche un message sur la sortie standard.
	//---------------------------------------------------------------------------
	public void log(String message)
	{
		System.out.println(message);
	}

	//---------------------------------------------------------------------------
	// * Get server socket
	// Tente d'établir une connexion avec le serveur.
	//---------------------------------------------------------------------------
	public static Socket getServerSocket() throws IOException
	{
		Socket socket = new Socket("localhost", 9090);
		return socket;
	}

	//---------------------------------------------------------------------------
	// * Main
	// Etablit une connexion avec le serveur et prend en charge les actions de
	// l'utilisateur.
	//---------------------------------------------------------------------------
	public static void main(String[] args) throws Exception
	{
		Socket socketServer = getServerSocket();
		SocketStreams server = new SocketStreams(socketServer);
		Client client = new Client(server);
	}
}
