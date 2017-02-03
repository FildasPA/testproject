package lib.net;

import java.io.*;
import java.net.*;

import lib.Console;
import lib.Ansi;

import lib.net.Clients;
import controller.ServerReplies;
// import lib.net.DB;

//=============================================================================
// ▼ ClientHandler
// ----------------------------------------------------------------------------
// Créé après l'établissement d'une connexion avec un utilisateur.
//=============================================================================
public class ClientHandler implements Runnable
{
	private Integer clientId;     // numéro du client attribué
	private Integer userId;       // id de l'utilisateur correspondant
	private SocketStreams client; // connexion avec le client
	private Boolean listening;

	//---------------------------------------------------------------------------
	// * Constructeur
	// Définit le numéro client et créer un objet SocketStreams qui établit des
	// flots (I/O) avec le client.
	//---------------------------------------------------------------------------
	public ClientHandler(Socket socket)
	{
		client = new SocketStreams(socket);

		clientId = Clients.add(this);

		log(Ansi.GREEN + "nouvelle connexion établie" + Ansi.RESET);
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
			listening = true;
			Request request;
			Object object;

			client.sendObject("Bienvenue " + Ansi.BLUE + "#" + clientId + Ansi.RESET + "!"); // message de bienvenue

			while (listening) {
				request = client.getRequest();
				if(request == null) break;
				// log("reçu: " + (String) request.getObject());
				ServerReplies.interpreter(this,request);
			}

		} finally {
			terminate();
		}
	}

	//---------------------------------------------------------------------------
	// * Terminate
	//---------------------------------------------------------------------------
	private void terminate()
	{
		log(Ansi.RED + "connexion terminée" + Ansi.RESET);
		Clients.remove(clientId);
	}

	//---------------------------------------------------------------------------
	// * Send object to client
	// Note: le serveur ne fait que renvoyer des objets.
	//---------------------------------------------------------------------------
	public void sendObject(Object object)
	{
		client.sendObject(object);
	}

	//---------------------------------------------------------------------------
	// * Close
	//---------------------------------------------------------------------------
	public void close()
	{
		listening = false;
		try {
			client.closeSocket();
		} catch (IOException e) {
			log("couldn't close a socket, what's going on?");
		}
	}


	//---------------------------------------------------------------------------
	// * Get client id - pas forcément nécessaire, je le laisse là au cas
	//---------------------------------------------------------------------------
	// public Integer getClientId()
	// {
	// 	return clientId;
	// }

	//---------------------------------------------------------------------------
	// * Get user id - pas forcément nécessaire, je le laisse là au cas
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
