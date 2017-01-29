import java.io.*;
import java.net.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//=============================================================================
// ▼ ClientHandler
// ----------------------------------------------------------------------------
// Créée après l'établissement d'une connexion avec un utilisateur.
// Gère les actions de l'utilisateur.
//=============================================================================
public class ClientHandler extends FZSocket implements Runnable
{
	private static Integer clientsNumber; // nombre de clients qui se sont connectés
	private static Map<Integer,ClientHandler> clients; // liste des clients qui se sont connectés

	// private static Integer sessionsNumber; // nombre de sessions de vote démarrées
	// private static Map<Integer,SessionServer> sessions; // liste des sessions de vote démarrées

	private Integer clientNumber; // numéro du client attribué
	// private SessionServer session; // session à laquelle l'utilisateur participe (actuellement)

	//---------------------------------------------------------------------------
	// * Initialize
	//---------------------------------------------------------------------------
	public static void initialize()
	{
		clientsNumber  = 0;
		// sessionsNumber = 0;

		clients  = new ConcurrentHashMap<Integer,ClientHandler>();
		// sessions = new ConcurrentHashMap<Integer,SessionServer>();
	}

	//---------------------------------------------------------------------------
	// * Constructeur
	// Définit le socket et initialise les flots (I/O).
	//---------------------------------------------------------------------------
	public ClientHandler(Socket socket)
	{
		super(socket);

		this.clientNumber = clientsNumber++;
		clients.put(clientNumber,this);

		// this.session = null;

		log("nouvelle session établie");
	}

	//---------------------------------------------------------------------------
	// * Run
	// Reçoit une chaîne de caractères envoyée par le client et la renvoie en
	// majuscules.
	//---------------------------------------------------------------------------
	public void run()
	{
		try {
			sendObject("Bienvenue #" + clientNumber + "!"); // message de bienvenue
			String userInput;

			while (true) {
				userInput = (String) getObject();
				log("Reçu: " + userInput);
				if (userInput == null) break;
				sendObject(userInput.toUpperCase());
			}

		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				log("couldn't close a socket, what's going on?");
			}
			log("connexion terminée");
		}
	}

	//---------------------------------------------------------------------------
	// * Log
	// Affiche un message sur la sortie standard du serveur. Indique le numéro
	// du client avant le message.
	//---------------------------------------------------------------------------
	public void log(String message)
	{
		super.log("#" + clientNumber + " " + message);
	}
}
