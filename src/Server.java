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
	private Vector<Thread> clients;
	private Vector<SessionServer> sessions;

	//---------------------------------------------------------------------------
	// * Constructor
	// Initialise les vecteurs des clients et des sessions de vote.
	//---------------------------------------------------------------------------
	public Server()
	{
		setListener();
		clients  = new Vector<Thread>();
		sessions = new Vector<SessionServer>();

		System.out.println("\nServeur démarré!");
		System.out.println("================\n");
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
	// essaie de se connecter. Attribue à ce client un numéro, qui est la taille
	// du vecteur clients.
	//---------------------------------------------------------------------------
	public void listen() throws IOException
	{
		listening = true;
		Socket socket;
		ClientSessionHandler clientSession;
		Thread clientThread;

		while(listening) {
			socket        = listener.accept();
			clientSession = new ClientSessionHandler(socket,clients.size(),sessions);
			clientThread  = new Thread(clientSession);

			clients.add(clientThread);
			clientThread.start();
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
