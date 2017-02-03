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

		// try {
		// 	Console.printb("Get method");
		// 	Method method = ServerReplies.class.getMethod(action,ClientHandler.class,Object.class);
		// 	try {
		// 		Console.printb("calling...");
		// 		method.invoke(client,object);
		// 	} catch (IllegalArgumentException e) {
		// 		Console.printb("illegal argument");
		// 		//
		// 	} catch (IllegalAccessException e) {
		// 		Console.printb("illegal access");
		// 		//
		// 	} catch (InvocationTargetException e) {
		// 		Console.printb("illegal target");
		// 		//
		// 	}
		// } catch (SecurityException e) {
		// 	Console.printb("security");
		// 	//
		// }	catch (NoSuchMethodException e) {
		// 	Console.printb("no such method");
		// 	//
		// }

		switch(action) {
			case "capitalize":
				ServerReplies.capitalize(client,(String) object);
				break;
		}
	}

	//---------------------------------------------------------------------------
	// * Log
	// Affiche un message sur la sortie standard du serveur.
	//---------------------------------------------------------------------------
	// public static void returnCapitalized(ClientHandler client,Object object)
	// {
	// 	Console.printb("return capitalized");
	// 	returnCapitalized(client,(String) object);
	// }

	//---------------------------------------------------------------------------
	// * Capitalize
	// Retourne la chaîne de caractères reçu en majuscules.
	//---------------------------------------------------------------------------
	public static void capitalize(ClientHandler client, String message)
	{
		client.sendObject(message.toUpperCase());
	}
}
