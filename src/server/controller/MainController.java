package server.controller;

import lib.net.Request;

import server.lib.ClientHandler;

//=============================================================================
// * Contrôleur principal
// ----------------------------------------------------------------------------
//
//=============================================================================
public abstract class MainController
{
	//---------------------------------------------------------------------------
	// * Log
	// Affiche un message sur la sortie standard du serveur.
	//---------------------------------------------------------------------------
	public static void callAppropriateAction(ClientHandler client, Request request)
	{
		String action = request.getAction();
		Object object = request.getObject();

		switch(action) {
			case "capitalize":
				MainController.capitalize(client,(String) object);
				break;
		}
	}

	//---------------------------------------------------------------------------
	// * Log
	// Affiche un message sur la sortie standard du serveur.
	//---------------------------------------------------------------------------
	public static void capitalize(ClientHandler client, String message)
	{
		client.sendObject(message.toUpperCase());
	}
}
