package server.lib.context;

import java.io.*;
import java.net.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import server.controller.SessionServer;

//=============================================================================
// ▼ Sessions
// ----------------------------------------------------------------------------
// Gère la liste des sessions en cours.
//=============================================================================
public class Sessions
{
	private static Integer sessionsNumber = 0; // nombre de sessions de vote démarrées
	private static Map<Integer,SessionServer> sessions = new
		ConcurrentHashMap<Integer,SessionServer>(); // liste des sessions en cours

	//---------------------------------------------------------------------------
	// * Add session
	// Initialise et ajoute une session à la liste des sessions en cours.
	// TODO TVS :
	// - récupérer les infos de la session dans la bdd
	// - initialiser un nouvel objet Session/SessionBase
	// - initialiser un nouvel objet SessionServer
	//---------------------------------------------------------------------------
	public static void add(Integer idClientMaster)
	{
		// Session sessionData = ConnexionBDD.getSessionById(sessionId);
		// SessionServer sessionServer = new SessionServer();
		sessions.put(sessionsNumber++,sessionServer);
	}

	//---------------------------------------------------------------------------
	// * Remove session
	//---------------------------------------------------------------------------
	public static void remove(Integer sessionId)
	{
		sessions.remove(sessionId);
	}

	//---------------------------------------------------------------------------
	// * Display sessions
	// Affiche la liste des sessions de votes en cours.
	//---------------------------------------------------------------------------
	public static void display()
	{
		System.out.println("Liste des sessions en cours:");
		System.out.println(sessions.entrySet().toString());
	}
}
