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
	//---------------------------------------------------------------------------
	// * Command prompt
	//---------------------------------------------------------------------------
	public static void commandPrompt()
	{
		System.out.print(Ansi.GREEN + "λ " + Ansi.RESET);
	}

	//---------------------------------------------------------------------------
	// * Demande à l'utilisateur une chaîne de caractères, l'envoie au serveur
	// et affiche sa réponse (la chaîne passée en majuscules).
	// Le programme s'arrête lorsque l'utilisateur entre le caractère 'q'.
	//---------------------------------------------------------------------------
	public static void input(SocketStreams server)
	{
		Scanner keyboard = new Scanner(System.in);
		String response;
		String userInput;
		String[] userInputArray;

		while(true) {
			commandPrompt();

			userInput      = keyboard.nextLine().trim();
			userInputArray = userInput.split(" ");

			// Interprète la commande
			switch(userInputArray[0]) {

				// Quitter
				case "q":
					log("Bye!");
					return;

				// Actions sur les sessions
				case "open-session":
					log("Open session #" + userInputArray[1] + "!");
					break;
				case "close-session":
					log("Close session #" + userInputArray[1] + "!");
					break;
				case "join-session":
					log("Join session #" + userInputArray[1] + "!");
					break;
				case "end-session":
					log("End session #" + userInputArray[1] + "!");
					break;
				case "leave-session":
					log("Leave session #" + userInputArray[1] + "!");
					break;

				// List
				case "list":
					switch(userInputArray[1]) {
						case "open-sessions":
							log("Open sessions:");
							break;
						default:
							log("Liste non reconnue!");
					}
					break;

				default:
					capitalize(server,userInput);
			}
		}
	}

	//---------------------------------------------------------------------------
	// * Capitalize
	// Renvoie la chaîne de caractères en majuscules
	//---------------------------------------------------------------------------
	public static void capitalize(SocketStreams server, String userInput)
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
