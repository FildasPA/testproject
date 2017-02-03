package client.controller;

import java.util.Scanner;

import lib.Ansi;
import lib.net.SocketStreams;
import lib.net.Request;

// Déterminer et appeler l'action appropriée correspondant à l'action effectuée par l'utilisateur sur la vue

//=============================================================================
// * Contrôleur principal
// ----------------------------------------------------------------------------
//
//=============================================================================
public abstract class MainController
{
	private Server server;
	private SocketStreams server;

	//---------------------------------------------------------------------------
	// * Capitalize
	// Renvoie la chaîne de caractères en majuscules
	//---------------------------------------------------------------------------
	public static void capitalize(String userInput)
	{
		server.sendRequest("capitalize",userInput);
		String s = (String) server.getObject();
		if(s == null) System.exit(0);
		log("Reçu:   " + s);
	}

	//---------------------------------------------------------------------------
	// * Log
	// Affiche un message sur la sortie standard.
	//---------------------------------------------------------------------------
	public static void log(String message)
	{
		System.out.println(message);
	}
}
