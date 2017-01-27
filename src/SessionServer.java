public class SessionServer extends Session
{
	public SessionServer()
	{
		super("lel",0,0,false);
	}

	// Creator creator;
	// Vector<Voter> voters;


	/**
	 * Start session
	 */
	public void startSession()
	{
		start();
		update();
		terminate();
	}

	/**
	 * Start
	 */
	private void start()
	{

		// Ajouts de participants (objet Voter)
		// Attente du démarrage de la session par le créateur de la session...
		// Fermer session: retire la session de la liste des sessions ouvertes
	}

	/**
	 * Update
	 *
	 * Gère de la réception de la question/de l'id de la question à l'attente de la prochaine question.
	 */
	private void update()
	{
		// while(!isFinished()) {
			// Envoi l'id de la question suivante aux participants et au créateur de la session (aléatoire ou défini)
			// Réception des réponses des votants... (Attente)
				// Vérification de la réponse (réponse + stats (temps de réponse))
			// Mise à jour des statistiques
			// Envoi des statistiques à Session_Creator
			// Envoi des résultats aux Session_Voter
		// }
	}

	/**
	 * Terminate
	 */
	private void terminate()
	{
		// Envoi des statistiques générales de la session à Session_Creator
		// Envoi des statistiques pour la session aux Session_Voter
		// Ecriture des résultats dans la bdd
		// Fermeture session
	}
}
