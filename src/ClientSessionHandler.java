import java.io.*;
import java.net.*;
import java.util.Vector;

//=============================================================================
// ▼ Session
// ----------------------------------------------------------------------------
// Créée après l'établissement d'une connexion avec un utilisateur.
// Gère les actions de l'utilisateur.
//=============================================================================
public class ClientSessionHandler extends ClientSession implements Runnable
{
	private int clientNumber;

	private Vector<SessionServer> sessions; // liste des sessions ouvertes/en cours
	private SessionServer session; // session à laquelle l'utilisateur participe

	/*
	Il faut bien comprendre que cette SessionServer n'est pas dédiée à un seul
	client mais partagée avec tous les participants d'une même session de vote.
	-----------------------------------------------------------------------------
	Cet objet (ClientSessionHandler) gère la connexion entre le serveur et le
	programme	client (Client). Il joue le rôle d'intermédiaire entre
	SessionServer et les sessions des utilisateurs (SessionMaster ou
	SessionUser).
	Le vecteur SessionServer permet simplement d'avoir accès à la liste des
	sessions ouvertes ou en cours.
	-----------------------------------------------------------------------------
	Ouvrir une nouvelle session de vote revient à créer un nouvel objet
	SessionServer et à l'insérer dans le vecteur 'sessions'.
	L'utilisateur courant (de ce thread) est défini comme chef de la session, et
	le programme client (Client) de celui-ci créera en réponse un objet
	SessionMaster.
	Tout autre programme client qui essaiera de rejoindre cette session de vote
	créera un nouvel objet SessionVoter. Son gestionnaire de connexion
	(ClientSessionHandler) se	chargera de lier cet utilisateur au SessionServer
	correspondant à la session voulue.
	*/

	//---------------------------------------------------------------------------
	// * Constructeur
	// Définit le socket et initialise les flots (I/O).
	//---------------------------------------------------------------------------
	public ClientSessionHandler(Socket socket, int clientNumber,
	                            Vector<SessionServer> sessions)
	{
		super(socket);
		this.clientNumber = clientNumber;
		this.sessions     = sessions;
		this.session      = null;

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
				if (userInput == null)break;
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
	protected void log(String message)
	{
		super.log("#" + clientNumber + " " + message);
	}
}
