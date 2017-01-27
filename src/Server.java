import java.net.*;
import java.io.IOException;
import java.util.Vector;

// Tutoriels/sources :
// http://www.oracle.com/technetwork/java/socket-140484.html#
// http://cs.lmu.edu/~ray/notes/javanetexamples/
// http://stackoverflow.com/questions/5419328/multiple-client-to-server-communication-program-in-java

//=============================================================================
// ▼ Server
// ----------------------------------------------------------------------------
// Démarre une nouvelle session (ClientSession) pour chaque client connecté.
// Fonctionne en local pour les tests.
//=============================================================================
public class Server
{
	private ServerSocket listener;
	private boolean listening;
	private Vector<ClientSessionHandler> clients;
	private Vector<SessionServer> sessions;

	//---------------------------------------------------------------------------
	// * Constructor
	// Initialise les vecteurs des clients et des sessions de vote.
	//---------------------------------------------------------------------------
	public Server()
	{
		setListener();
		clients  = new Vector<ClientSessionHandler>();
		sessions = new Vector<SessionServer>();

		System.out.println("Serveur démarré!");
		System.out.println("================\n");
		// System.out.println();
	}

	//---------------------------------------------------------------------------
	// * Set listner
	//---------------------------------------------------------------------------
	private void setListener()
	{
		try {
			listener = new ServerSocket(9090, 0, InetAddress.getByName(null));
		} catch (IOException e) {
			System.err.println("Could not listen on port: 9090");
			System.exit(1);
		}
	}

	//---------------------------------------------------------------------------
	// * Listen
	// Créer un nouveau thread et un nouveau socket à chaque fois qu'un client
	// essaie de se connecter. Attribue à ce client un numéro, qui est la taille du
	//---------------------------------------------------------------------------
	public void listen() throws IOException
	{
		listening = true;
		while(listening) {
			Socket clientSocket = listener.accept();
			ClientSessionHandler client = new ClientSessionHandler(clientSocket,
			                                                       clients.size(),
			                                                       sessions);
			clients.add(client);
			client.start();
		}
		listener.close();
	}

	//---------------------------------------------------------------------------
	// * Main
	//---------------------------------------------------------------------------
	public static void main(String[] args) throws IOException
	{
		Server server = new Server();

		server.listen();
	}
}
