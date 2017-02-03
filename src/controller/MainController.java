package controller;

import java.util.Scanner;
import java.lang.reflect.Array;

import java.io.*;
import java.net.*;

import lib.Ansi;
import lib.Console;

import lib.net.Server;
import lib.net.SocketStreams;

// Déterminer et appeler l'action appropriée correspondant à l'action effectuée par l'utilisateur sur la vue

//=============================================================================
// * Contrôleur principal
// ----------------------------------------------------------------------------
//
//=============================================================================
public class MainController
{
	private Server localServer;
	private Thread localServerThread;
	private SocketStreams remoteServer;

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
		} while(interpreter(userInput));
	}

	//---------------------------------------------------------------------------
	// * Interprète la commande de l'utilisateur
	// Si 'q', efface tous les objets créés et quitte le programme.
	//---------------------------------------------------------------------------
	public Boolean interpreter(String input)
	{
		String[] inputs = input.split(" ");

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

			// case "capitalize":
				// capitalize(inputs[1]);
				// break;

			default:
				serverInterpreter(input);
				// String line = StringUtils.join(inputs[2]," ");
		}
		return true;
	}

	//---------------------------------------------------------------------------
	// * Server interpreter
	//---------------------------------------------------------------------------
	public void serverInterpreter(String input)
	{
		String[] inputs = input.split(" ");

		// Interprète la commande
		switch(inputs[0]) {

			// Capitalize
			case "capitalize":
				capitalize(input.replaceFirst("capitalize ",""));
				break;

			default:
		}
	}

	//---------------------------------------------------------------------------
	// * Check remote server
	//---------------------------------------------------------------------------
	private Boolean checkRemoteServer()
	{
		if(remoteServer == null) {
			Console.printerr("Vous devez être connecté à un serveur");
			return false;
		}
		return true;
	}

	//---------------------------------------------------------------------------
	// * Capitalize
	// Renvoie un mot en majuscules
	//---------------------------------------------------------------------------
	public void capitalize(String line)
	{
		if(!checkRemoteServer()) return;
		remoteServer.sendRequest("capitalize",(Object) line);
		String s = (String) remoteServer.getObject();
		if(s == null) System.exit(0);
		printReceived(s);
	}

	//---------------------------------------------------------------------------
	// * Terminate
	//---------------------------------------------------------------------------
	public void terminate()
	{
		Console.print("A la prochaine!\n");
		if(localServer != null) localServer.stop();
	}

	//---------------------------------------------------------------------------
	// * Open server
	// Créer un nouveau server dans un nouveau fil d'exécution.
	//---------------------------------------------------------------------------
	public void openServer(Integer port)
	{
		if(localServer != null) {
			Console.print("Un serveur est déjà ouvert!");
			return;
		}
		Console.print("Demandé à démarrer un serveur sur le port " + port + "...");
		localServer = new Server(port);
		localServerThread = new Thread(localServer);
		localServerThread.start();
	}

	//---------------------------------------------------------------------------
	// * Join server
	//---------------------------------------------------------------------------
	public void joinServer(Integer port)
	{
		if(remoteServer != null) {
			Console.print("Déjà connecté à un serveur!");
			return;
		}
		Console.print("Demandé à rejoindre le serveur sur le port " + port + "...");
		try {
			remoteServer = new SocketStreams(new Socket("localhost", port));
			String s = (String) remoteServer.getObject();
			printReceived(s);
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

	//---------------------------------------------------------------------------
	// * Join server
	//---------------------------------------------------------------------------
	public void printReceived(String s)
	{
		Console.print(Ansi.GREEN + "Reçu: " + Ansi.RESET + s);
	}
}
