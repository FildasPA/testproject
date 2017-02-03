package controller;

import java.util.Scanner;

import java.io.*;
import java.net.*;

import lib.Ansi;
import lib.Console;

import lib.net.Server;
import lib.net.SocketStreams;
// import lib.net.Request;

// Déterminer et appeler l'action appropriée correspondant à l'action effectuée par l'utilisateur sur la vue

//=============================================================================
// * Contrôleur principal
// ----------------------------------------------------------------------------
//
//=============================================================================
public class MainController
{
	private Server server;
	private Thread serverThread;
	private SocketStreams remote;

	//---------------------------------------------------------------------------
	// * Main
	//---------------------------------------------------------------------------
	public void main()
	{
		Console.print(Ansi.BLUE + "\nBonjour!" + Ansi.RESET);
		input();
	}

	//---------------------------------------------------------------------------
	// * Interprète les commandes entrées par l'utilisateur
	//---------------------------------------------------------------------------
	public void input()
	{
		Scanner keyboard = new Scanner(System.in);
		String userInput;

		do {
			Console.commandPrompt();
			userInput = keyboard.nextLine().trim();
		} while(interpreter(userInput.split(" ")));
	}

	//---------------------------------------------------------------------------
	// * Interprète la commande de l'utilisateur
	// Si 'q', efface tous les objets créés et quitte le programme.
	//---------------------------------------------------------------------------
	public Boolean interpreter(String[] inputs)
	{
		// Interprète la commande
		switch(inputs[0]) {

			// Quitter
			case "q":
				terminate();
				return false;

			// Actions sur le server
			case "os":
				openServer(9090);
				break;
			case "js":
				joinServer(9090);
				break;
			case "open-server":
				openServer(Integer.parseInt(inputs[1]));
				break;
			case "join-server":
				joinServer(Integer.parseInt(inputs[1]));
				break;

			// Actions sur les sessions
			case "open-session":
				Console.print("Open session #" + inputs[1] + "!");
				break;
			case "close-session":
				Console.print("Close session #" + inputs[1] + "!");
				break;
			case "join-session":
				Console.print("Join session #" + inputs[1] + "!");
				break;
			case "end-session":
				Console.print("End session #" + inputs[1] + "!");
				break;
			case "leave-session":
				Console.print("Leave session #" + inputs[1] + "!");
				break;

			// List
			case "list":
				switch(inputs[1]) {
					case "open-sessions":
						Console.print("Open sessions:");
						break;
					default:
						Console.print("Liste non reconnue!");
				}
				break;

			default:
				// capitalize(server,Arrays.toString(inputs));
		}
		return true;
	}

	//---------------------------------------------------------------------------
	// * Capitalize
	// Renvoie la chaîne de caractères en majuscules
	//---------------------------------------------------------------------------
	// public static void capitalize(String line)
	// {
	// 	server.sendRequest("returnCapitalized",line);
	// 	String s = (String) server.getObject();
	// 	if(s == null) System.exit(0);
	// 	log("Reçu:   " + s);
	// }

	//---------------------------------------------------------------------------
	// * Terminate
	//---------------------------------------------------------------------------
	public void terminate()
	{
		if(server != null) server.stop();
		Console.print("A la prochaine!");
	}

	//---------------------------------------------------------------------------
	// * Open server
	// Créer un nouveau server dans un nouveau fil d'exécution.
	//---------------------------------------------------------------------------
	public void openServer(Integer port)
	{
		if(server != null) {
			Console.print("Un serveur est déjà ouvert!");
			return;
		}
		Console.print("Ask open server on port " + port + "!");
		server = new Server(port);
		serverThread = new Thread(server);
		serverThread.start();
	}

	//---------------------------------------------------------------------------
	// * Join server
	//---------------------------------------------------------------------------
	public void joinServer(Integer port)
	{
		if(remote != null) {
			Console.print("Déjà connecté à un serveur!");
			return;
		}
		Console.print("Ask join server on port " + port + "!");
		try {
			remote = new SocketStreams(new Socket("localhost", port));
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
}
