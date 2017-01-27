import java.io.*;
import java.net.*;

//=============================================================================
// ▼ ClientSession
// ----------------------------------------------------------------------------
// Définie les propriétés et méthodes nécessaires dans les classes
// Client et ClientSessionHandler, notamment l'envoie et la réception d'objets.
//=============================================================================
public abstract class ClientSession
{
	protected Socket socket;

	protected ObjectInputStream inputStream;
	protected ObjectOutputStream outputStream;

	//---------------------------------------------------------------------------
	// * Constructeur
	// Définit le socket et initialise les flots (I/O).
	//---------------------------------------------------------------------------
	public ClientSession(Socket socket)
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
	protected void sendObject(Object object)
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
	//---------------------------------------------------------------------------
	protected Object getObject()
	{
		try {
			Object object = inputStream.readObject();
			return object;
		} catch(IOException e) {
			// e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	//---------------------------------------------------------------------------
	// * Log
	// Affiche un message sur la sortie standard du serveur.
	//---------------------------------------------------------------------------
	protected void log(String message)
	{
		System.out.println(message);
	}
}
