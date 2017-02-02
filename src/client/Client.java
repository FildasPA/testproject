import java.io.*;
import java.net.*;

import lib.Ansi;
import lib.net.SocketStreams;

import client.controller.MainController;

//=============================================================================
// ▼ Client
// ----------------------------------------------------------------------------
// Etablit une connexion avec le serveur.
// Appelle la vue appropriée.
// Appelle l'action par défaut.
//=============================================================================
class Client extends MainController
{
	private SocketStreams server;

	//---------------------------------------------------------------------------
	// * Constructeur
	//---------------------------------------------------------------------------
	public Client(SocketStreams server)
	{
		this.server = server;
		log(Ansi.BLUE + (String) server.getObject() + Ansi.RESET);
	}

	//---------------------------------------------------------------------------
	// * Main
	//---------------------------------------------------------------------------
	public void main()
	{
		MainController.input(server);
	}

	//---------------------------------------------------------------------------
	// * Get server socket
	// Tente d'établir une connexion avec le serveur.
	//---------------------------------------------------------------------------
	public static Socket getServerSocket() throws IOException
	{
		Socket socket = new Socket("localhost", 9090);
		return socket;
	}

	//---------------------------------------------------------------------------
	// * Main
	// Etablit une connexion avec le serveur et prend en charge les actions de
	// l'utilisateur.
	//---------------------------------------------------------------------------
	public static void main(String[] args) throws Exception
	{
		Socket socketServer = getServerSocket();
		SocketStreams server = new SocketStreams(socketServer);
		Client client = new Client(server);
		client.main();
	}
}
