package lib.net;

import java.io.*;
import java.net.*;

//=============================================================================
// ▼ SocketStreams
// ----------------------------------------------------------------------------
// Permet:
// - l'initialisation des flots (I/O) sur un socket
// - l'envoi et la réception de requêtes (de type Request) par ce socket
//=============================================================================
public class SocketStreams
{
	protected Socket socket;

	protected ObjectInputStream inputStream;
	protected ObjectOutputStream outputStream;

	//---------------------------------------------------------------------------
	// * Constructeur
	// Initialise les flots (I/O) à partir d'un socket passé en paramètre.
	//---------------------------------------------------------------------------
	public SocketStreams(Socket socket)
	{
		this.socket = socket;
		try {
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			inputStream  = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			log(e.getMessage());
		}
	}

	//---------------------------------------------------------------------------
	// * Send object
	//---------------------------------------------------------------------------
	public void sendObject(Object object)
	{
		try {
			outputStream.writeObject(object);
			outputStream.flush();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	//---------------------------------------------------------------------------
	// * Get object
	// Récupère et retourne un objet.
	//---------------------------------------------------------------------------
	public Object getObject()
	{
		try {
			Object object = inputStream.readObject();
			return object;
		} catch(IOException e) {
			// e.getMessage();
			// e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// e.getMessage();
			e.printStackTrace();
		}
		return null;
	}

	//---------------------------------------------------------------------------
	// * Send request
	// Crée et envoie un objet Request.
	//---------------------------------------------------------------------------
	public void sendRequest(String action, Object object)
	{
		try {
			// System.out.println("action: " + action);
			// System.out.println("object: " + object.toString());
			// Request request = new Request(action,object);
			outputStream.writeObject(new Request(action,object));
			outputStream.flush();
		} catch(SocketException e) {
			e.getMessage();
			e.printStackTrace();
		} catch(IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

	//---------------------------------------------------------------------------
	// * Get object
	// Récupère et retourne un objet Request.
	//---------------------------------------------------------------------------
	public Request getRequest()
	{
		try {
			Request request = (Request) inputStream.readObject();
			return request;
		} catch(IOException e) {
			// e.getMessage();
			// e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.getMessage();
			e.printStackTrace();
		}
		return null;
	}

	//---------------------------------------------------------------------------
	// * Close socket
	//---------------------------------------------------------------------------
	public void closeSocket() throws IOException
	{
		socket.close();
	}

	//---------------------------------------------------------------------------
	// * Log
	// Affiche un message sur la sortie standard.
	//---------------------------------------------------------------------------
	public void log(String message)
	{
		System.out.println(message);
	}
}
