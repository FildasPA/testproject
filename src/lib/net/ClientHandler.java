package lib.net;

import java.io.*;
import java.net.*;

// import lib.net.SocketStreams;
// import lib.net.Request;

// import server.ConnexionBDD; // TODO RES-BDD: à renommer
import lib.Console;
import lib.Ansi;

import lib.net.Clients;

//=============================================================================
// ▼ ClientHandler
// ----------------------------------------------------------------------------
// Créée après l'établissement d'une connexion avec un utilisateur.
//=============================================================================
class ClientHandler implements Runnable
{
	private Integer clientId; // numéro du client attribué
	private Integer userId; // id du client
	private SocketStreams client; // connexion avec le client
	// private SessionServer session; // session à laquelle le client participe (actuellement)

	//---------------------------------------------------------------------------
	// * Constructeur
	// Définit le numéro client et créer un objet SocketStreams qui établit des
	// flots (I/O) avec le client.
	//---------------------------------------------------------------------------
	public ClientHandler(Socket socket)
	{
		client = new SocketStreams(socket);

		clientId = Clients.add(this);

		// System.out.println("clientid: " + clientId);

		log("nouvelle connexion établie");
	}

	//===========================================================================
	// * Run
	// Appelle l'action en réponse à l'objet envoyé par le client.
	//---------------------------------------------------------------------------
	// Note: Cette fonction est définie par la classe Runnable. Elle est appelée
	// lorsque l'on démarre le Thread du client (cad clientThread.start() dans
	// Server.java).
	//===========================================================================
	public void run()
	{
		try {
			Request request;
			Object object;

			client.sendObject("Bienvenue #" + clientId + "!"); // message de bienvenue

			while (true) {
				request = client.getRequest();
				if(request == null) break;
				// receivedObject = (String) client.getObject();
				// if(receivedObject == null) break;
				// log("reçu: " + request.getObject().toString());
				// MainController.callAppropriateAction(this,request);
			}

		} finally {
			try {
				client.closeSocket();
			} catch (IOException e) {
				log("couldn't close a socket, what's going on?");
			}
			log("connexion terminée");
		}
	}

	//---------------------------------------------------------------------------
	// * Get client id
	//---------------------------------------------------------------------------
	public Integer getClientId()
	{
		return clientId;
	}

	//---------------------------------------------------------------------------
	// * Send object to client
	//---------------------------------------------------------------------------
	public void sendObject(Object object)
	{
		client.sendObject(object);
	}

	//---------------------------------------------------------------------------
	// * Get user id
	//---------------------------------------------------------------------------
	// public Integer getUserId()
	// {
	// 	return userId;
	// }

	//---------------------------------------------------------------------------
	// * Log
	// Affiche un message sur la sortie standard du serveur. Indique le numéro
	// du client avant le message.
	//---------------------------------------------------------------------------
	public void log(String message)
	{
		Console.printb(Ansi.BLUE + "#" + clientId + Ansi.RESET + " " + message);
	}
}
