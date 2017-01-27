import java.io.*;
import java.net.*;

import java.util.Scanner;

//=============================================================================
// ▼ Client
// ----------------------------------------------------------------------------
// Etablit une connexion avec le serveur.
//=============================================================================
class Client extends ClientSession
{
	//---------------------------------------------------------------------------
	// * Constructeur
	//---------------------------------------------------------------------------
	public Client(Socket socket)
	{
		super(socket);
		log((String) getObject());
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
		String[] userInput;

		while(true) {
			// Invite de commande
			System.out.print("> ");
			userInput = keyboard.nextLine().trim().split(" ");

			// Interprète la commande
			switch(userInput[0]) {

				// Quitter
				case "q":
					System.out.println("Bye!");
					return;

				// Actions sur les sessions
				case "open-session":
					System.out.println("Open session #" + userInput[1] + "!");
					break;
				case "close-session":
					System.out.println("Close session #" + userInput[1] + "!");
					break;
				case "join-session":
					System.out.println("Join session #" + userInput[1] + "!");
					break;
				case "end-session":
					System.out.println("End session #" + userInput[1] + "!");
					break;
				case "leave-session":
					System.out.println("Leave session #" + userInput[1] + "!");
					break;

				// List
				case "list":
					switch(userInput[1]) {
						case "open-sessions":
							System.out.println("Open sessions:");
							break;
						default:
							System.out.println("Liste non reconnue!");
					}
					break;

				// Défaut
				default:
					// Renvoie la chaîne de caractères en majuscules
					sendObject(userInput[0]);
					String s = (String) getObject();
					if(s == null) System.exit(0);
					System.out.println("Read object: " + s);
			}
		}
	}

	//---------------------------------------------------------------------------
	// * Get socket
	// Tente d'établir une connexion avec le serveur.
	//---------------------------------------------------------------------------
	public static Socket getSocket() throws IOException
	{
		Socket socket = new Socket("localhost", 9090);
		return socket;
	}

	//---------------------------------------------------------------------------
	// * Main
	//---------------------------------------------------------------------------
	public static void main(String[] args) throws Exception
	{
		Socket socket = getSocket();
		Client client = new Client(socket);
	}
}
