package server.controller;

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
	public static void callAppropriateAction(ClientHandler client, Object object)
	{
		if(object instanceof String)
			MainController.capitalize(client,(String) object);
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
