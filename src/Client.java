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

	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;

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

			outputStream = new ObjectOutputStream(socket.getOutputStream());
			inputStream  = new ObjectInputStream(socket.getInputStream());

			log((String) getObject()); // affiche le message de bienvenue du serveur
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
	// * Send object
	//---------------------------------------------------------------------------
	private void sendObject(Object object)
	{
		try {
			outputStream.writeObject(object);
			outputStream.flush();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	//---------------------------------------------------------------------------
	// * Get object
	//---------------------------------------------------------------------------
	private Object getObject()
	{
		try {
			Object object = inputStream.readObject();
			return object;
		} catch(IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	//---------------------------------------------------------------------------
	// * Log
	// Affiche un message sur la sortie standard du serveur.
	//---------------------------------------------------------------------------
	private void log(String message)
	{
		System.out.println(message);
	}

	//---------------------------------------------------------------------------
	// * Main
	//---------------------------------------------------------------------------
	public static void main(String[] args) throws Exception {
		Client client = new Client();
	}
}
