package controller;

import java.lang.reflect.*;

import lib.Console;
import lib.Ansi;

import lib.net.ClientHandler;
import lib.net.Request;

public abstract class ServerReplies
{
	//---------------------------------------------------------------------------
	// * Log
	// Affiche un message sur la sortie standard du serveur.
	//---------------------------------------------------------------------------
	public static void interpreter(ClientHandler client, Request request)
	{
		String action = request.getAction();
		Object object = request.getObject();

		switch(action) {
			case "capitalize":
				ServerReplies.capitalize(client,(String) object);
				break;
		}
	}

	//---------------------------------------------------------------------------
	// * Capitalize
	// Retourne la chaîne de caractères reçu en majuscules.
	//---------------------------------------------------------------------------
	public static void capitalize(ClientHandler client, String message)
	{
		client.sendObject(message.toUpperCase());
	}
}
