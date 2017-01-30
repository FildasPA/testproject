package server;

import java.io.*;
import java.net.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lib.FZSocket;

//=============================================================================
// ▼ ClientHandler
// ----------------------------------------------------------------------------
// Créée après l'établissement d'une connexion avec un utilisateur.
// Gère les actions de l'utilisateur.
//=============================================================================
public class ClientHandler implements Runnable
{
	private static Integer clientsNumber; // nombre de clients qui se sont connectés
	private static Map<Integer,ClientHandler> clients; // liste des clients qui se sont connectés

	// private static Integer sessionsNumber; // nombre de sessions de vote démarrées
	// private static Map<Integer,SessionServer> sessions; // liste des sessions de vote démarrées

	// private static ConnexionBdd bdd; // TODO RES-BDD

	FZSocket client;
	private Integer clientNumber; // numéro du client attribué
	// private SessionServer session; // session à laquelle l'utilisateur participe (actuellement)

	//---------------------------------------------------------------------------
	// * Initialize
	// Initialise les variables statiques de la classe. C'est à dire:
	// - le nombre de clients qui se sont connectés
	// - la liste des clients connectés
	// - le nombre de sessions qui ont été ouvertes
	// - la liste des sessions ouvertes
	// - TODO RES-BDD: la connexion à la base de données
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
	// Définit le numéro client et créer l'objet FZSocket pour initialiser les
	// flots (I/O).
	//---------------------------------------------------------------------------
	public ClientHandler(Socket socket)
	{
		client = new FZSocket(socket);

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
	// * Log
	// Affiche un message sur la sortie standard du serveur. Indique le numéro
	// du client avant le message.
	//---------------------------------------------------------------------------
	public void log(String message)
	{
		System.out.println("#" + clientNumber + " " + message);
	}
}
