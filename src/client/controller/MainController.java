package client.controller;

import java.util.Scanner;

import lib.net.SocketStreams;

// Déterminer et appeler l'action appropriée correspondant à l'action effectuée par l'utilisateur sur la vue

//=============================================================================
// * Contrôleur principal
// ----------------------------------------------------------------------------
//
//=============================================================================
public abstract class MainController
{
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
		String[] userInputs;

		while(true) {
			// Invite de commande
			System.out.print("> ");
			userInput = keyboard.nextLine().trim();
			userInputs = userInput.split(" ");

			// Interprète la commande
			switch(userInputs[0]) {

				// Quitter
				case "q":
					log("Bye!");
					return;

				// Actions sur les sessions
				case "open-session":
					log("Open session #" + userInputs[1] + "!");
					break;
				case "close-session":
					log("Close session #" + userInputs[1] + "!");
					break;
				case "join-session":
					log("Join session #" + userInputs[1] + "!");
					break;
				case "end-session":
					log("End session #" + userInputs[1] + "!");
					break;
				case "leave-session":
					log("Leave session #" + userInputs[1] + "!");
					break;

				// List
				case "list":
					switch(userInputs[1]) {
						case "open-sessions":
							log("Open sessions:");
							break;
						default:
							log("Liste non reconnue!");
					}
					break;

				// Défaut
				default:
					// Renvoie la chaîne de caractères en majuscules
					server.sendObject(userInput);
					log("Envoyé: " + userInput);
					String s = (String) server.getObject();
					if(s == null) System.exit(0);
					log("Reçu:   " + s);
			}
		}
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
