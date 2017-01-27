import java.io.*;
import java.net.*;

import java.util.Scanner;

//=============================================================================
// ▼ Client
// ----------------------------------------------------------------------------
// Etablit une connexion avec le serveur.
//=============================================================================
class Client
{
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	//---------------------------------------------------------------------------
	// * Constructeur
	//---------------------------------------------------------------------------
	public Client()
	{
		connectToServer();
		input();
	}

	//---------------------------------------------------------------------------
	// * Etablit la connexion et initialise les flots (I/O)
	//---------------------------------------------------------------------------
	public void connectToServer()
	{
	  try {
	  	socket = new Socket("localhost", 9090);
	  	in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	  	out = new PrintWriter(socket.getOutputStream(),true);
	  	System.out.println(in.readLine()); // affiche le message de bienvenue du serveur
		} catch (IOException ex) {
			System.out.println("Can't connect to server");
		}
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
		String line;
		String[] input;

		while(true) {
			// Invite de commande
			System.out.print("> ");
			line = keyboard.nextLine().trim();
			input = line.split(" ");

			// Interprète la commande
			switch(input[0]) {

				// Quitter
				case "q":
					System.out.println("Bye!");
					return;

				// Actions sur les sessions
				case "open-session":
					System.out.println("Open session #" + input[1] + "!");
					break;
				case "close-session":
					System.out.println("Close session #" + input[1] + "!");
					break;
				case "join-session":
					System.out.println("Join session #" + input[1] + "!");
					break;
				case "end-session":
					System.out.println("End session #" + input[1] + "!");
					break;
				case "leave-session":
					System.out.println("Leave session #" + input[1] + "!");
					break;

				// List
				case "list":
					switch(input[1]) {
						case "open-sessions":
							System.out.println("Open sessions:");
							break;
						default:
							System.out.println("Liste non reconnue!");
					}
					break;

				// Défaut
				default:
					System.out.println("Commande non reconnue!");
			}

		}
	}

	//---------------------------------------------------------------------------
	// * Main
	//---------------------------------------------------------------------------
	public static void main(String[] args) throws Exception {
	  Client client = new Client();
	}
}
