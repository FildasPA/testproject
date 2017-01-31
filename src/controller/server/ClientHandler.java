package server;

import java.io.*;
import java.net.*;

import net.SocketStreams;
import server.Clients;
// import server.ConnexionBDD; // TODO RES-BDD: à renommer
import sessionvoting.server.*;

//=============================================================================
// ▼ ClientHandler
// ----------------------------------------------------------------------------
// Créée après l'établissement d'une connexion avec un utilisateur.
// Gère les actions de l'utilisateur.
//=============================================================================
public class ClientHandler implements Runnable
{
	private Integer clientId; // numéro du client attribué
	private Integer userId; // id du client
	SocketStreams client; // connexion avec le client
	// private SessionServer session; // session à laquelle le client participe (actuellement)

	//---------------------------------------------------------------------------
	// * Constructeur
	// Définit le numéro client et créer un objet SocketStreams qui établit des
	// flots (I/O) avec le client.
	//---------------------------------------------------------------------------
	public ClientHandler(Socket socket)
	{
		client = new SocketStreams(socket);

		this.clientNumber = clientsNumber++;
		clients.put(clientNumber,this);

		// this.session = null;

		log("nouvelle session établie");
	}

	//===========================================================================
	// * Run
	// Définit l'enchaînement des intéractions avec le client.
	//---------------------------------------------------------------------------
	// Note: Cette fonction est définie par la classe Runnable. Elle est appelée
	// lorsque l'on démarre le Thread du client (cad clientThread.start() dans
	// Server.java).
	//---------------------------------------------------------------------------
	// Scénario actuel: reçoit une chaîne de caractères envoyée par le client et
	// la renvoie en majuscules.
	//===========================================================================
	public void run()
	{
		try {
			client.sendObject("Bienvenue #" + clientNumber + "!"); // message de bienvenue
			String userInput;

			while (true) {
				userInput = (String) client.getObject();
				log("Reçu: " + userInput);
				if (userInput == null) break;
				client.sendObject(userInput.toUpperCase());
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
		System.out.println("#" + clientNumber + " " + message);
	}
}
