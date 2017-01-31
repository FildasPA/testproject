package votingsession.server;

import java.io.*;

import server.Clients;
import server.ConnexionBDD;

//=============================================================================
// ▼ SessionServer
// ----------------------------------------------------------------------------
// Pour chaque session de vote en cours, une instance de cette classe
// s'exécute sur le serveur de manière asynchrone.
// Elle gère les intéractions pendant une session de vote entre:
// - le maître de session
// - les votants
// - la base de données
//=============================================================================
public class SessionServer implements Runnable
{
	private Integer idClientMaster; // id du client du maître de la session
	private Vector<Integer> clients; // liste des id des clients des votants

	//===========================================================================
	// * Run
	// Gère les intéractions entre les clients participant à la session de vote.
	//---------------------------------------------------------------------------
	// Note: Cette fonction est définie par la classe Runnable. Elle est appelée
	// lorsque l'on démarre le Thread du client (cad clientThread.start() dans
	// Server.java).
	//===========================================================================
	public void run()
	{
		// ... TODO TVS-Déroulement SessionServer
	}

	//---------------------------------------------------------------------------
	// * Log
	// Affiche un message sur la sortie standard du serveur.
	//---------------------------------------------------------------------------
	public void log(String message)
	{
		System.out.println(message);
	}
}
